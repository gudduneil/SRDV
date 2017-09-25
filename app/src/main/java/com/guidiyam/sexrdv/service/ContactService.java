package com.guidiyam.sexrdv.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import com.guidiyam.sexrdv.helper.Utils;
import com.guidiyam.sexrdv.setget.ContactList_getset;
;
import com.guidiyam.sexrdv.setget.ContactList_getset2;
import com.guidiyam.sexrdv.volley.AppData;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by su on 24/8/17.
 */

public class ContactService extends Service {
    Context ctx;
    Cursor phones;
    String number,image_thumb;
    ContentResolver resolver;
    public static final String ACTION_ON = "com.guidiyam.sexrdv.ContactService.ACTION_START";
    public static final String ACTION_KILL = "com.guidiyam.sexrdv.ContactService.ACTION_KILL";


    @SuppressLint("InlinedApi")
    private final static String SELECTION =(Utils.hasHoneycomb() ? ContactsContract.Contacts.DISPLAY_NAME_PRIMARY : ContactsContract.Contacts.DISPLAY_NAME) +
            "<>''" + " AND " + ContactsContract.Contacts.IN_VISIBLE_GROUP + "=1";

    @SuppressLint("InlinedApi")
    private final static String SORT_ORDER =Utils.hasHoneycomb() ? ContactsContract.Contacts.SORT_KEY_PRIMARY : ContactsContract.Contacts.DISPLAY_NAME;

    @SuppressLint("InlinedApi")
    private final static String[] PROJECTION = {
            ContactsContract.Contacts._ID,ContactsContract.Contacts.LOOKUP_KEY,
            Utils.hasHoneycomb() ? ContactsContract.Contacts.DISPLAY_NAME_PRIMARY : ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.Contacts.HAS_PHONE_NUMBER,
            Utils.hasHoneycomb() ? ContactsContract.Contacts.PHOTO_THUMBNAIL_URI : ContactsContract.Contacts._ID,
            SORT_ORDER,
    };
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        ctx = this;
        if (intent.getAction().equals(ACTION_ON)) {


            create_contact();


        } else {

            killMe();

        }
        return START_NOT_STICKY;
    }




    public void killMe() {
        stopForeground(true);
        stopSelf();
    }
    public void create_contact()
    {

        Toast.makeText(ctx,"Contact Service is called",Toast.LENGTH_SHORT).show();
        AppData.contactarraylist = new ArrayList<ContactList_getset>();

        //AppData.contactarraylistforfilter = new ArrayList<ContactList_getset>();

        resolver = ctx.getContentResolver();
        phones = resolver.query(ContactsContract.Contacts.CONTENT_URI, PROJECTION, SELECTION, null, SORT_ORDER);
        AppData.isServiceCompleted=false;
        new fetchcontactAsync().execute();








    }
    private class fetchcontactAsync extends AsyncTask<Void, Void, Void> {
        JSONObject response1;
        JSONObject res;

        String result, user_exitforggogle;
        JSONObject jobj, userinfo;
        boolean statusasyn;
        int statuscode;
        String message;
        boolean status;
        private static final long CONNECT_TIMEOUT_MILLIS = 90000;
        private static final long READ_TIMEOUT_MILLIS = 90000;

        protected void onPreExecute() {

            super.onPreExecute();
            // pbar.setVisibility(View.VISIBLE);

        }


        protected Void doInBackground(Void... bitmaps) {

            try {
               // AppData.contactarraylist=new ArrayList<ContactList_getset>();
//                Cursor phones = ctx.getContentResolver().query(
//                        ContactsContract.Contacts.CONTENT_URI,
//                        PROJECTION,
//                        SELECTION,
//                        null,
//                        SORT_ORDER
//                );

                while (phones.moveToNext())

                {
                    String name="",number="",image="",Email="",numberIndex="";
                    //count++;
                    name = phones.getString(phones.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                    if (Integer.parseInt(phones.getString(phones.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0)
                    {
                        Cursor pCur =ctx.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{phones.getString(phones.getColumnIndex(ContactsContract.Contacts._ID))},
                                null);
                        while (pCur.moveToNext())
                        {
                            number = pCur.getString(pCur.getColumnIndex("data1"));
                            numberIndex = pCur.getString(pCur.getColumnIndex(ContactsContract.Contacts._ID));
                            image=pCur.getString(pCur.getColumnIndex(ContactsContract.Contacts.PHOTO_URI));
                            Cursor pEmail=ctx.getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                                    ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", new String[]{phones.getString(phones.getColumnIndex(ContactsContract.Contacts._ID))},
                                    null);
                            while (pEmail.moveToNext())
                            {
                                //number = pCur.getString(pCur.getColumnIndex("data1"));
                                Email = pEmail.getString(pEmail.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA1));
                                //image=pCur.getString(pCur.getColumnIndex(ContactsContract.Contacts.PHOTO_URI));
                            }pEmail.close();
                        }pCur.close();


                    }
                    ContactList_getset obj=new ContactList_getset(name,number,image,Email,false);
                    AppData.contactarraylist.add(obj);
//
//                    ContactList_getset2 obj2=new ContactList_getset2(name,number,image,Email,false);
//                    AppData.contactarraylistforfilter.add(obj2);



                   // AppData.contactarraylistforfilter.add(obj);

                   // AppData.contactarraylistforfilter.add(obj);

                    Log.d("ArrayList::::","Name: "+obj.getName()+" number: "+obj.getNumber()+" image: "+obj.getImage()+" email: "+obj.getEmail());
                }
                //phones.close();
                //ContactFetchM();



            } catch (Exception e)
            {
                Log.d("OKHTTP_RESULT", "ERROR : " + e.toString());

            }

            return null;
        }

        protected void onPostExecute(Void resultt) {
            super.onPostExecute(resultt);

            //Toast.makeText(ctx,"onpost execute block is called",Toast.LENGTH_SHORT).show();
            AppData.isServiceCompleted=true;


//            else if(status1==false)
//                Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
        }
    }
//    public class FetchContact extends AsyncTask<Void, Void, Void> {
//        protected void onPreExecute() {
//
//            super.onPreExecute();
//        }
//        protected Void doInBackground(Void... params) {
//            //ContactFetchM();
//
//            if (phones != null) {
//
//                Log.e("count", "" + phones.getCount());
//                if (phones.getCount() == 0) {
//                    Toast.makeText(ctx, "No contacts in your contact list.", Toast.LENGTH_LONG).show();
//                }
//
//                while (phones.moveToNext()) {
//
//
//                    String name="",number="",image="",Email="",numberIndex="";
//
//                     name = phones.getString(phones.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//
//                    if (Integer.parseInt(phones.getString(phones.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
//
//                        Cursor pCur = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
//                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{phones.getString(phones.getColumnIndex(ContactsContract.Contacts._ID))},
//                                null);
//                        while (pCur.moveToNext()) {
//                            number = pCur.getString(pCur.getColumnIndex("data1"));
//                             numberIndex = pCur.getString(pCur.getColumnIndex(ContactsContract.Contacts._ID));
//                            image = pCur.getString(pCur.getColumnIndex(ContactsContract.Contacts.PHOTO_URI));
//
//                            Cursor pEmail=ctx.getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
//                                    ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", new String[]{ pCur.getString(pCur.getColumnIndex(ContactsContract.Contacts._ID))},
//                                    null);
//                            while (pEmail.moveToNext())
//                            {
//                                //number = pCur.getString(pCur.getColumnIndex("data1"));
//                                Email = pEmail.getString(pEmail.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA1));
//                                //image=pCur.getString(pCur.getColumnIndex(ContactsContract.Contacts.PHOTO_URI));
//                            }pEmail.close();
//
//                            Log.d("Numberlist", " Number" + number + " Name " + name);
//
//
//                        }
//                        pCur.close();
//
//
//
//
//                        ContactList_getset obj=new ContactList_getset(name,number,image,Email,false);
////                    selectUser.setThumb(image_thumb);
////                    selectUser.setName(name);
////                    selectUser.setPhone(number);
//                        //selectUser.setEmail(EmailAddress);
//                        //selectUser.setSelect(false);
//                        AppData.contactarraylist.add(obj);
//                       // AppData.selectedContact=AppData.contactarraylist;
//
//
//                        Log.d("ARRRayy:::","Name: "+obj.getName()+"Number: "+obj.getNumber()+"Image: "+obj.getImage()+"Email: "+obj.getEmail());
//                    }
//
//
//
////
//                }
//
//
//            }
//            else {
//                Log.e("Cursor close 1", "----------------");
//            }
//            return null;
//        }
//        protected void onPostExecute(Void resultt) {
//            super.onPostExecute(resultt);
//            AppData.isServiceCompleted=true;
//
//
//
//        }
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       // Toast.makeText(ctx,"Service is destroyed",Toast.LENGTH_SHORT).show();
    }
}
