package com.example.nicolas.investc.Utilities;

import static java.lang.Math.pow;

/**
 * Created by NICOLAS on 07/06/2018.
 */

public class VanUtilities {

    public static final String TABLE_VAN = "VAN";

    public static final String ID_VAN = "_idVan";
    public static final String INDICATEUR_VAN = "indicateurVan";
    public static final String DATE_SIMULATION_VAN = "dateSimulationVan";
    public static final String NOMBRE_ANNEES = "nombreAnnees";
    public static final String INVESTISSEMENT_VAN = "investissementVan";
    public static final String TAUX = "taux";
    public static final String FLUX = "flux";
    public static final String RESULTAT_VAN = "resultatVan";

    public static final String CREATE_VAN = "CREATE TABLE " + TABLE_VAN + " (" +
            ID_VAN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            INDICATEUR_VAN + " TEXT NOT NULL, " +
            DATE_SIMULATION_VAN + " DATETIME NOT NULL, " +
            NOMBRE_ANNEES + " INTEGER NOT NULL, " +
            INVESTISSEMENT_VAN + " REAL NOT NULL, " +
            TAUX + " REAL NOT NULL, " +
            FLUX + " REAL NOT NULL, " +
            RESULTAT_VAN + " REAL NOT NULL)";


    public static final String DROP_VAN = "DROP TABLE IF EXISTS " + TABLE_VAN;


    public static final String SELECT_VANS = "SELECT * FROM " + TABLE_VAN;

    public static final String SELECT_VAN_BY_ID = SELECT_VANS + " WHERE " + ID_VAN +  " = ?";

    public static double calculerVan(int nbAnnees, double investissement, double taux, double flux) {

        double van = 0.0;

        for(int i = 0 ; i < nbAnnees ; i++) {
            van += (flux / pow(1 + taux, i));
        }

        van -= investissement;

        return van;
    }




}