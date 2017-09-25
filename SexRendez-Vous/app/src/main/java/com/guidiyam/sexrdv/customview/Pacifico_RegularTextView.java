package com.guidiyam.sexrdv.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by su on 7/8/17.
 */

public class Pacifico_RegularTextView extends TextView {

    public Pacifico_RegularTextView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init();
    }

    public Pacifico_RegularTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public Pacifico_RegularTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {

        super.setTypeface(Typeface.createFromAsset(getContext().getAssets(),
                "Pacifico.ttf"));
    }

}
