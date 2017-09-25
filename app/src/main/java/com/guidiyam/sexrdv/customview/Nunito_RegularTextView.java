package com.guidiyam.sexrdv.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by su on 4/8/17.
 */

public class Nunito_RegularTextView extends TextView {

    public Nunito_RegularTextView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init();
    }

    public Nunito_RegularTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public Nunito_RegularTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {

        super.setTypeface(Typeface.createFromAsset(getContext().getAssets(),
                "Nunito-Regular.ttf"));
    }


}
