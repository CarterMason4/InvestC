package com.example.nicolas.investc.Utilities;

/**
 * Created by NICOLAS on 07/06/2018.
 */

public class UtilisateurUtilities {

    // TABLE

    public static final String TABLE_UTILISATEURES = "UTILISATEURS";

    // CHAMPS

    public static final String ID_UTILISATEUR = "_idUtilisateur";
    public static final String PSEUDO = "pseudo";
    public static final String ENTREPRISE = "entreprise";
    public static final String SITE_WEB = "site_web";

    public static final String CREATE_UTILISATEURS = "CREATE TABLE " + TABLE_UTILISATEURES + " (" +
            ID_UTILISATEUR + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PSEUDO + " TEXT NOT NULL, " +
            ENTREPRISE + " TEXT NOT NULL, " +
            SITE_WEB + " TEXT NOT NULL)";

    public static final String DROP_UTILISATEURS = "DROP TABLE IF EXISTS " + TABLE_UTILISATEURES;

    public static final String SELECT_UTILISATEURS = "SELECT * FROM " + TABLE_UTILISATEURES;

    public static final String SELECT_UTILISATEUR_BY_ID = SELECT_UTILISATEURS + " WHERE " + ID_UTILISATEUR + " = ?";



}
