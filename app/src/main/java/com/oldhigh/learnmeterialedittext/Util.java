package com.oldhigh.learnmeterialedittext;

import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;

import java.util.Arrays;

public class Util {



    public static int dpToPx(int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP , dp , Resources.getSystem().getDisplayMetrics());
    }


    public static void d(Object... objects){
        Log.d("material--> ", Arrays.toString(objects));
    }
    public static void e(Object... objects){
        Log.e("material--> ", Arrays.toString(objects));
    }
}
