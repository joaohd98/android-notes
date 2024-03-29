package com.deck.notes.Utils.Helpers;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Helpers {

    public static DatabaseReference getDatabaseReference(String uuid, String node) {

        return FirebaseDatabase.getInstance().getReference().child(uuid).child(node);

    }

    public static String getCurrentDate() {

        SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy 'ás' HH:mm");
        Date date = new Date(System.currentTimeMillis());

        return formatter.format(date);


    }

    public static void checkIsValid(Button btn, Boolean isValid) {

        if(isValid) {


            btn.setAlpha(1);
            btn.setClickable(true);

        }

        else {

            btn.setAlpha(.3f);
            btn.setClickable(false);

        }

    }

    public static void setTouchable(Context context, boolean enabled) {

        Window window = ( (Activity) context ).getWindow();

        if(enabled)
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        else
            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

    }

    public static LayerDrawable getBorders(int backgroundColor, int borderColor, int left, int top, int right, int bottom) {

        ColorDrawable borderColorDrawable = new ColorDrawable(borderColor);
        ColorDrawable backgroundColorDrawable = new ColorDrawable(backgroundColor);

        Drawable[] drawables = new Drawable[]{
                borderColorDrawable,
                backgroundColorDrawable
        };

        LayerDrawable layerDrawable = new LayerDrawable(drawables);

        layerDrawable.setLayerInset(1, left, top, right, bottom);

        return layerDrawable;

    }

    public static void removeFocusClickOutside(Activity activity, MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            View v = activity.getCurrentFocus();

            if (v instanceof EditText) {

                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);

                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);

                    if (imm != null)
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                }

            }

        }

    }

}
