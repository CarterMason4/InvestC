package com.example.nicolas.investc.Utilities;

/**
 * Created by NICOLAS on 07/06/2018.
 */

public class RoiUtilities {

    public static final String TABLE_ROI = "ROI";

    public static final String ID_ROI = "_idRoi";
    public static final String INDICATEUR_ROI = "indicateurRoi";
    public static final String DATE_SIMULATION_ROI = "dateSimulationRoi";
    public static final String GAINPERTE = "gain_perte";
    public static final String INVESTISSEMENT_ROI = "investissementRoi";
    public static final String RESULTAT_ROI = "resultatRoi";

    public static final String CREATE_ROI = "CREATE TABLE " + TABLE_ROI + " (" +
            ID_ROI + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            INDICATEUR_ROI + " TEXT NOT NULL, " +
            DATE_SIMULATION_ROI + " DATETIME NOT NULL, " +
            GAINPERTE + " REAL NOT NULL, " +
            INVESTISSEMENT_ROI + " REAL NOT NULL, " +
            RESULTAT_ROI + " REAL NOT NULL)";

    public static final String DROP_ROI = "DROP TABLE IF EXISTS " + TABLE_ROI;

    public static final String SELECT_ROIS = "SELECT * FROM " + TABLE_ROI;

    public static final String SELECT_ROI_BY_ID = SELECT_ROIS + " WHERE " + ID_ROI + " = ?";

    public static double calculerRoi(double gainPerte, double investissement) {
        return ((gainPerte - investissement) / investissement) * 100;
    }
}