package com.applex.trackcovid_19.util;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class Utility {

    public static void showToast(Context context, String data){
        Toast.makeText(context,data, Toast.LENGTH_LONG).show();
    }

    public static String getFittingString(String s, int length) {
        if (s.length() > length) {
            s = s.substring(0, length) + "\u2026";
        }
        return s;
    }

    public static void hideKeyboard(Context context, ConstraintLayout layout) {
        InputMethodManager imm = (InputMethodManager)context.getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(layout.getWindowToken(), 0);
    }

    public static void vibrate(Context context) {
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            v.vibrate(50);
        }
    }

}
