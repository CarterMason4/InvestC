package com.example.nicolas.investc.Utilities;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.widget.Toast;

import com.example.nicolas.investc.Beans.Roi;

import static java.lang.Math.*;

/**
 * Created by NICOLAS on 07/06/2018.
 */

public abstract class Utilities {


    public static void makeToast(Context c, String s) {

        Toast toast = Toast.makeText(c, s, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();

    }
    

    public static void makeToast(Context c, int res) {

        Toast toast = Toast.makeText(c, res, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();

    }
}