package com.example.nicolas.investc.Beans;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.Date;

/**
 * Created by NICOLAS on 07/06/2018.
 */

public class Roi extends Indicateur implements Parcelable {

    private int id;
    private String nomIndicateur;
    private String dateSimulation;
    private double gainPerte;
    private double investissement;
    private double resultat;



    public Roi() {}

    public Roi(int id) {
        this();
        setId(id);
    }

    public Roi(int id, String nomIndicateur) {
        this(id);
        setNomIndicateur(nomIndicateur);
    }

    public Roi(int id, String nomIndicateur, String dateSimulation) {
        this(id, nomIndicateur);
        setDateSimulation(dateSimulation);
    }

    public Roi(int id, String nomIndicateur, String dateSimulation, double gainPerte) {
        this(id, nomIndicateur, dateSimulation);
        setGainPerte(gainPerte);
    }

    public Roi(int id, String nomIndicateur, String dateSimulation, double gainPerte, double investissement) {
        this(id, nomIndicateur, dateSimulation, gainPerte);
        setInvestissement(investissement);
    }

    public Roi(int id, String nomIndicateur, String dateSimulation, double gainPerte, double investissement, double resultat) {
        this(id, nomIndicateur, dateSimulation, gainPerte, investissement);
        setResultat(resultat);
    }

    public Roi(int id, String nomIndicateur, double gainPerte, double investissement, double resultat) {
        this(id);
        setNomIndicateur(nomIndicateur);
        setGainPerte(gainPerte);
        setInvestissement(investissement);
        setResultat(resultat);
    }

    public Roi(String nomIndicateur, double gainPerte, double investissement, double resultat) {
        this();
        setNomIndicateur(nomIndicateur);
        setGainPerte(gainPerte);
        setInvestissement(investissement);
        setResultat(resultat);
    }

    public Roi(String nomIndicateur, String dateSimulation, double gainPerte, double investissement, double resultat) {
        this(nomIndicateur, gainPerte, investissement, resultat);
        setDateSimulation(dateSimulation);
    }



    @Override
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
                !nomIndicateur.equals("ROI")) {
            this.nomIndicateur = "ROI";
        } else {
            this.nomIndicateur = nomIndicateur;
        }
    }



    @Override
    public String getNomIndicateur() {

        return nomIndicateur;
    }

    public double getGainPerte() {
        return gainPerte;
    }


    public void setGainPerte(double gainPerte) {
        this.gainPerte = gainPerte;
    }

    public double getInvestissement() {
        return investissement;
    }

    public void setInvestissement(double investissement) {
        this.investissement = investissement;
    }

    @Override
    public double getResultat() {
        return resultat;
    }

    @Override
    public void setResultat(double resultat) {
        this.resultat = resultat;
    }

    @Override
    public void setDateSimulation(String dateSimulation) {
        this.dateSimulation = dateSimulation;
    }


    @Override
    public String getDateSimulation() {
        return dateSimulation;
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

                .append(dateSimulation)
                .append('\n')

                .append("Gain ou perte : ")
                .append(gainPerte)
                .append('\n')

                .append("Investissement initial : ")
                .append(investissement)
                .append('\n')

                .append("Resultat : ")
                .append(resultat)
                .append('\n').toString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nomIndicateur);
        dest.writeString(this.dateSimulation);
        dest.writeDouble(this.gainPerte);
        dest.writeDouble(this.investissement);
        dest.writeDouble(this.resultat);
    }

    protected Roi(Parcel in) {
        this.id = in.readInt();
        this.nomIndicateur = in.readString();
        this.dateSimulation = in.readString();
        this.gainPerte = in.readDouble();
        this.investissement = in.readDouble();
        this.resultat = in.readDouble();
    }

    public static final Creator<Roi> CREATOR = new Creator<Roi>() {
        @Override
        public Roi createFromParcel(Parcel source) {
            return new Roi(source);
        }

        @Override
        public Roi[] newArray(int size) {
            return new Roi[size];
        }
    };


}
