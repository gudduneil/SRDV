package com.guidiyam.sexrdv.setget;

/**
 * Created by su on 25/9/17.
 */

public class NewContactList_getset
{

    String name,number,image,Email;
    boolean touch;

    public NewContactList_getset(String name, String number, String image,String Email,boolean touch) {
        this.name = name;
        this.number = number;
        this.image = image;
        this.Email=Email;
        this.touch=touch;
    }

    public boolean isTouch() {
        return touch;
    }

    public void setTouch(boolean touch) {
        this.touch = touch;
    }

    public String getEmail() {
        return Email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
