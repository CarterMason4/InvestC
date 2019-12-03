package com.example.nicolas.investc.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.nicolas.investc.Utilities.RoiUtilities.CREATE_ROI;
import static com.example.nicolas.investc.Utilities.RoiUtilities.DROP_ROI;
import static com.example.nicolas.investc.Utilities.VanUtilities.CREATE_VAN;
import static com.example.nicolas.investc.Utilities.VanUtilities.DROP_VAN;

/**
 * Created by NICOLAS on 08/06/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "invest.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context,
                DATABASE_NAME,
                null,
                DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_VAN);
        db.execSQL(CREATE_ROI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_VAN);
        db.execSQL(DROP_ROI);

        onCreate(db);
    }
}
