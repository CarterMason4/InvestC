package com.example.nicolas.investc.Beans;

/**
 * Created by NICOLAS on 08/06/2018.
 */

public class Indicateur {

    private int id;
    private String nomIndicateur;
    private String dateSimulation;
    private double resultat;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomIndicateur() {
        return nomIndicateur;
    }

    public String getDateSimulation() {
        return dateSimulation;
    }

    public void setDateSimulation(String dateSimulation) {

        this.dateSimulation = dateSimulation;
    }

    public double getResultat() {
        return resultat;
    }

    public void setResultat(double resultat) {
        this.resultat = resultat;
    }
}
