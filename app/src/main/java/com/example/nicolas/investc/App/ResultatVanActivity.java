package com.example.nicolas.investc.App;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.nicolas.investc.Beans.Van;
import com.example.nicolas.investc.R;

import java.text.NumberFormat;

public class ResultatVanActivity extends AppCompatActivity {

    private ImageView isRentable;

    private TextView valeurNbAnnees;
    private TextView valeurInvestissement;
    private TextView valeurTaux;
    private TextView valeurFlux;
    private TextView valeurVan;

    private Toolbar toolbar;

    private Context c = ResultatVanActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat_van);

        isRentable = findViewById(R.id.isRentableVan);
        valeurNbAnnees = findViewById(R.id.valeurNbAnnees);
        valeurInvestissement = findViewById(R.id.valeurInvestissementVan);
        valeurTaux = findViewById(R.id.valeurTaux);
        valeurFlux = findViewById(R.id.valeurFlux);
        valeurVan = findViewById(R.id.valeurVan);
        toolbar = findViewById(R.id.simulationsToolbarVan);


        Van van = getIntent().getParcelableExtra("van");

        if(van != null) {

            NumberFormat format = NumberFormat.getInstance();
            format.setMaximumFractionDigits(2);

            String nbAnnees;
            String investissement = format.format(van.getInvestissement()) + '€';
            String taux = format.format(van.getTaux()) + '%';
            String flux = format.format(van.getFlux()) + '€';

            int idCouleur;

            if(van.getNbAnnees() == 1) {
                nbAnnees = String.valueOf(van.getNbAnnees()) + " an";
            } else {
                 nbAnnees = String.valueOf(van.getNbAnnees()) + " ans";
            }

            if(van.getResultat() < 0) {
                idCouleur = getResources().getColor(R.color.red);
                isRentable.setImageDrawable(getResources().getDrawable(R.drawable.x_mark_2));
            } else {
                idCouleur = getResources().getColor(R.color.green);
                isRentable.setImageDrawable(getResources().getDrawable(R.drawable.check_mark));
            }

           valeurNbAnnees.setText(nbAnnees);
           valeurInvestissement.setText(investissement);
           valeurTaux.setText(taux);
           valeurFlux.setText(flux);

           String stringVan = format.format(van.getResultat()) + '€';
           valeurVan.setText(stringVan);
           valeurVan.setTextColor(idCouleur);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_resultat_van, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.aide) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    

}
