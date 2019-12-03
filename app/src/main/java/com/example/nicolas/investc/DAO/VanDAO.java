package com.example.nicolas.investc.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nicolas.investc.Beans.Indicateur;
import com.example.nicolas.investc.Beans.Van;
import com.example.nicolas.investc.Helper.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.nicolas.investc.Utilities.VanUtilities.DATE_SIMULATION_VAN;
import static com.example.nicolas.investc.Utilities.VanUtilities.FLUX;
import static com.example.nicolas.investc.Utilities.VanUtilities.ID_VAN;
import static com.example.nicolas.investc.Utilities.VanUtilities.INDICATEUR_VAN;
import static com.example.nicolas.investc.Utilities.VanUtilities.INVESTISSEMENT_VAN;
import static com.example.nicolas.investc.Utilities.VanUtilities.NOMBRE_ANNEES;
import static com.example.nicolas.investc.Utilities.VanUtilities.RESULTAT_VAN;
import static com.example.nicolas.investc.Utilities.VanUtilities.SELECT_VANS;
import static com.example.nicolas.investc.Utilities.VanUtilities.SELECT_VAN_BY_ID;
import static com.example.nicolas.investc.Utilities.VanUtilities.TABLE_VAN;
import static com.example.nicolas.investc.Utilities.VanUtilities.TAUX;

/**
 * Created by NICOLAS on 09/06/2018.
 */

public class VanDAO implements IDAO<Van> {

    private DatabaseHelper helper;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);

    public VanDAO(Context c) {
        helper = new DatabaseHelper(c);
    }

    @Override
    public long insert(Van van) {

        SQLiteDatabase db = helper.getWritableDatabase();

        long id = db.insert(TABLE_VAN,
                null,
                constructObject(van));

        db.close();

        return id;
    }

    @Override
    public Van selectById(int id) {

        SQLiteDatabase db = helper.getReadableDatabase();

        Van v = null;

        Cursor c = db.rawQuery(SELECT_VAN_BY_ID,
                new String[] {String.valueOf(id)});


        if(c.moveToNext()) {

            v = new Van(
                    c.getInt(c.getColumnIndex(ID_VAN)),
                    c.getString(c.getColumnIndex(INDICATEUR_VAN)),
                    c.getString(c.getColumnIndex(DATE_SIMULATION_VAN)),
                    c.getInt(c.getColumnIndex(NOMBRE_ANNEES)),
                    c.getDouble(c.getColumnIndex(INVESTISSEMENT_VAN)),
                    c.getDouble(c.getColumnIndex(TAUX)),
                    c.getDouble(c.getColumnIndex(FLUX)),
                    c.getDouble(c.getColumnIndex(RESULTAT_VAN)));

        }

        c.close();

        db.close();

        return v;
    }

    @Override
    public List<Van> selectAll() {

        SQLiteDatabase db = helper.getReadableDatabase();

        List<Van> vans = new ArrayList<>();

        Cursor c = db.rawQuery(SELECT_VANS,
                null);

        Date d = new Date();


        while(c.moveToNext()) {

            vans.add(new Van(
                    c.getInt(c.getColumnIndex(ID_VAN)),
                    c.getString(c.getColumnIndex(INDICATEUR_VAN)),
                    c.getString(c.getColumnIndex(DATE_SIMULATION_VAN)),
                    c.getInt(c.getColumnIndex(NOMBRE_ANNEES)),
                    c.getDouble(c.getColumnIndex(INVESTISSEMENT_VAN)),
                    c.getDouble(c.getColumnIndex(TAUX)),
                    c.getDouble(c.getColumnIndex(FLUX)),
                    c.getDouble(c.getColumnIndex(RESULTAT_VAN))));
        }

        c.close();

        db.close();

        return vans;
    }

    public List<Indicateur> selectTous() {

        SQLiteDatabase db = helper.getReadableDatabase();

        List<Indicateur> vans = new ArrayList<>();

        Cursor c = db.rawQuery(SELECT_VANS,
                null);


        while(c.moveToNext()) {

            vans.add(new Van(
                    c.getInt(c.getColumnIndex(ID_VAN)),
                    c.getString(c.getColumnIndex(INDICATEUR_VAN)),
                    c.getString(c.getColumnIndex(DATE_SIMULATION_VAN)),
                    c.getInt(c.getColumnIndex(NOMBRE_ANNEES)),
                    c.getDouble(c.getColumnIndex(INVESTISSEMENT_VAN)),
                    c.getDouble(c.getColumnIndex(TAUX)),
                    c.getDouble(c.getColumnIndex(FLUX)),
                    c.getDouble(c.getColumnIndex(RESULTAT_VAN))));
        }

        c.close();

        db.close();

        return vans;
    }




    @Override
    public void deleteAll() {

        SQLiteDatabase db = helper.getWritableDatabase();

        db.delete(TABLE_VAN,
                null,
                null);

        db.close();

    }

    @Override
    public void deleteById(int id) {

        SQLiteDatabase db = helper.getWritableDatabase();

        db.delete(TABLE_VAN,
                ID_VAN + " = ?",
                new String[] {String.valueOf(id)});

        db.close();
    }

    @Override
    public void updateById(int id, Van van) {

        SQLiteDatabase db = helper.getWritableDatabase();


        db.update(TABLE_VAN,
                constructObject(van),
                ID_VAN + " = ?",
                new String[] {String.valueOf(id)});

    }

    private ContentValues constructObject(Van v) {

        ContentValues values = new ContentValues();

        values.put(INDICATEUR_VAN, v.getNomIndicateur());
        values.put(DATE_SIMULATION_VAN, dateFormat.format(new Date()));
        values.put(NOMBRE_ANNEES, v.getNbAnnees());
        values.put(INVESTISSEMENT_VAN, v.getInvestissement());
        values.put(TAUX, v.getTaux());
        values.put(FLUX, v.getFlux());
        values.put(RESULTAT_VAN, v.getResultat());

        return values;


    }
}
