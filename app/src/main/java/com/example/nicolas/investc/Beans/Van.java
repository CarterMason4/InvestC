package com.example.nicolas.investc.Beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by NICOLAS on 07/06/2018.
 */

public class Van extends Indicateur implements Parcelable {

    private int id;
    private String nomIndicateur;
    private String dateSimulation;
    private int nbAnnees;
    private double investissement;
    private double taux;
    private double flux;
    private double resultat;

    public Van() {}

    public Van(int id) {
        this();
        setId(id);
    }

    public Van(int id, String nomIndicateur) {
        this(id);
        setNomIndicateur(nomIndicateur);
    }

    public Van(int id, String nomIndicateur, String dateSimulation) {
        this(id, nomIndicateur);
        setDateSimulation(dateSimulation);
    }

    public Van(int id, String nomIndicateur, String dateSimulation, int nbAnnees) {
        this(id, nomIndicateur, dateSimulation);
        setNbAnnees(nbAnnees);
    }

    public Van(int id, String nomIndicateur, String dateSimulation, int nbAnnees, double investissement) {
        this(id, nomIndicateur, dateSimulation, nbAnnees);
        setInvestissement(investissement);
    }

    public Van(int id, String nomIndicateur, String dateSimulation, int nbAnnees, double investissement, double taux)  {
        this(id, nomIndicateur, dateSimulation, nbAnnees, investissement);
        setTaux(taux);
    }

    public Van(int id, String nomIndicateur, String dateSimulation, int nbAnnees, double investissement, double taux, double flux) {
        this(id, nomIndicateur, dateSimulation, nbAnnees, investissement, taux);
        setFlux(flux);
    }

    public Van(int id, String nomIndicateur, String dateSimulation, int nbAnnees, double investissement, double taux, double flux, double resultat) {
        this(id, nomIndicateur, dateSimulation, nbAnnees, investissement, taux, flux);
        setResultat(resultat);
    }

    public Van(String nomIndicateur, int nbAnnees, double investissement, double taux, double flux, double resultat) {
        this();
        setNomIndicateur(nomIndicateur);
        setNbAnnees(nbAnnees);
        setInvestissement(investissement);
        setTaux(taux);
        setFlux(flux);
        setResultat(resultat);
    }

    public Van(String nomIndicateur, String dateSimulation, int nbAnnees, double investissement, double taux, double flux, double resultat) {
        this(nomIndicateur, nbAnnees, investissement, taux, flux, resultat);
        setDateSimulation(dateSimulation);
    }



    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }



    public void setNomIndicateur(String nomIndicateur) {
        if(nomIndicateur == null ||
                nomIndicateur.trim().length() == 0 ||
                !nomIndicateur.equals("VAN")) {
            this.nomIndicateur = "VAN";

        } else {
            this.nomIndicateur = nomIndicateur;
        }
    }

    public String getNomIndicateur() {
        return nomIndicateur;
    }

    public void setDateSimulation(String dateSimulation) {
        this.dateSimulation = dateSimulation;
    }

    public String getDateSimulation() {
        return dateSimulation;
    }


    public int getNbAnnees() {
        return nbAnnees;
    }

    public void setNbAnnees(int nbAnnees) {
        this.nbAnnees = nbAnnees;
    }

    public double getInvestissement() {
        return investissement;
    }

    public void setInvestissement(double investissement) {
        this.investissement = investissement;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public double getFlux() {
        return flux;
    }

    public void setFlux(double flux) {
        this.flux = flux;
    }

    public double getResultat() {
        return resultat;
    }

    public void setResultat(double resultat) {
        this.resultat = resultat;
    }


    @Override
    public String toString() {

        return new StringBuffer()

                .append("ID : ")
                .append(id)
                .append('\n')

                .append("Nom de l'indicateur : ")
                .append(nomIndicateur)
                .append('\n')

                .append("Nombre d'années : ")
                .append(nbAnnees)
                .append('\n')

                .append("Investissement initial : ")
                .append(investissement)
                .append('\n')

                .append("Taux : ")
                .append(taux)
                .append('\n')

                .append("Flux : ")
                .append(flux)
                .append('\n')

                .append("Résultat : ")
                .append(resultat)
                .append('\n').toString();
    }

    protected Van(Parcel in) {
        id = in.readInt();
        nomIndicateur = in.readString();
        dateSimulation = in.readString();
        nbAnnees = in.readInt();
        investissement = in.readDouble();
        taux = in.readDouble();
        flux = in.readDouble();
        resultat = in.readDouble();
    }

    public static final Creator<Van> CREATOR = new Creator<Van>() {
        @Override
        public Van createFromParcel(Parcel in) {
            return new Van(in);
        }

        @Override
        public Van[] newArray(int size) {
            return new Van[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nomIndicateur);
        dest.writeString(dateSimulation);
        dest.writeInt(nbAnnees);
        dest.writeDouble(investissement);
        dest.writeDouble(taux);
        dest.writeDouble(flux);
        dest.writeDouble(resultat);
    }
}
