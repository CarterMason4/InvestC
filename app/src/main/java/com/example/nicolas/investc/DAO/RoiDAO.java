package com.example.nicolas.investc.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nicolas.investc.Beans.Indicateur;
import com.example.nicolas.investc.Beans.Roi;
import com.example.nicolas.investc.Helper.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.nicolas.investc.Utilities.RoiUtilities.DATE_SIMULATION_ROI;
import static com.example.nicolas.investc.Utilities.RoiUtilities.GAINPERTE;
import static com.example.nicolas.investc.Utilities.RoiUtilities.ID_ROI;
import static com.example.nicolas.investc.Utilities.RoiUtilities.INDICATEUR_ROI;
import static com.example.nicolas.investc.Utilities.RoiUtilities.INVESTISSEMENT_ROI;
import static com.example.nicolas.investc.Utilities.RoiUtilities.RESULTAT_ROI;
import static com.example.nicolas.investc.Utilities.RoiUtilities.SELECT_ROIS;
import static com.example.nicolas.investc.Utilities.RoiUtilities.SELECT_ROI_BY_ID;
import static com.example.nicolas.investc.Utilities.RoiUtilities.TABLE_ROI;

/**
 * Created by NICOLAS on 08/06/2018.
 */

public class RoiDAO implements IDAO<Roi> {


    private DatabaseHelper helper;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);


    public RoiDAO(Context c) {
        helper = new DatabaseHelper(c);
    }


    @Override
    public long insert(Roi roi) {

        SQLiteDatabase db = helper.getWritableDatabase();

        long id = db.insert(TABLE_ROI,
                null,
                constructObject(roi));

        db.close();

        return id;
    }






    @Override
    public Roi selectById(int id) {

        SQLiteDatabase db = helper.getReadableDatabase();

        Roi roi = null;

        Cursor c = db.rawQuery(SELECT_ROI_BY_ID,
                new String[]{String.valueOf(id)});


        if(c.moveToNext()) {
            roi = new Roi(
                    c.getInt(c.getColumnIndex(ID_ROI)),
                    c.getString(c.getColumnIndex(INDICATEUR_ROI)),
                    c.getString(c.getColumnIndex(DATE_SIMULATION_ROI)),
                    c.getDouble(c.getColumnIndex(GAINPERTE)),
                    c.getDouble(c.getColumnIndex(INVESTISSEMENT_ROI)),
                    c.getDouble(c.getColumnIndex(RESULTAT_ROI)));
        }


        c.close();

        db.close();

        return roi;
    }


    @Override
    public List<Roi> selectAll() {

        List<Roi> rois = new ArrayList<>();

        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.rawQuery(SELECT_ROIS,
                null);

        while(c.moveToNext()) {

            rois.add(new Roi(
                    c.getInt(c.getColumnIndex(ID_ROI)),
                    c.getString(c.getColumnIndex(INDICATEUR_ROI)),
                    c.getString(c.getColumnIndex(DATE_SIMULATION_ROI)),
                    c.getDouble(c.getColumnIndex(GAINPERTE)),
                    c.getDouble(c.getColumnIndex(INVESTISSEMENT_ROI)),
                    c.getDouble(c.getColumnIndex(RESULTAT_ROI))));

        }

        c.close();

        db.close();

        return rois;
    }

    @Override
    public void deleteAll() {

        SQLiteDatabase db = helper.getWritableDatabase();

        db.delete(TABLE_ROI,
                null,
                null);

        db.close();

    }

    @Override
    public void deleteById(int id) {

        SQLiteDatabase db = helper.getWritableDatabase();

        db.delete(TABLE_ROI,
                ID_ROI + " = ?",
                new String[] {String.valueOf(id)});

        db.close();

    }


    @Override
    public void updateById(int id, Roi r) {

        SQLiteDatabase db = helper.getWritableDatabase();

        db.update(TABLE_ROI,
                constructObject(r),
                ID_ROI + " = ?",
                new String[] {String.valueOf(id)});

        db.close();

    }


    public List<Indicateur> selectTous() {

        List<Indicateur> rois = new ArrayList<>();

        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.rawQuery(SELECT_ROIS,
                null);


        while(c.moveToNext()) {


            rois.add(new Roi(
                    c.getInt(c.getColumnIndex(ID_ROI)),
                    c.getString(c.getColumnIndex(INDICATEUR_ROI)),
                    c.getString(c.getColumnIndex(DATE_SIMULATION_ROI)),
                    c.getDouble(c.getColumnIndex(GAINPERTE)),
                    c.getDouble(c.getColumnIndex(INVESTISSEMENT_ROI)),
                    c.getDouble(c.getColumnIndex(RESULTAT_ROI))));

        }

        c.close();

        db.close();

        return rois;
    }


    private ContentValues constructObject(Roi r) {

        ContentValues values = new ContentValues();

        values.put(INDICATEUR_ROI,  r.getNomIndicateur());
        values.put(DATE_SIMULATION_ROI, dateFormat.format(new Date()));
        values.put(GAINPERTE, r.getGainPerte());
        values.put(INVESTISSEMENT_ROI, r.getInvestissement());
        values.put(RESULTAT_ROI, r.getResultat());


        return values;

    }
    
}