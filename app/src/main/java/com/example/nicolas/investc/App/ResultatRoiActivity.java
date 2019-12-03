package com.example.nicolas.investc.App;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.nicolas.investc.Beans.Roi;
import com.example.nicolas.investc.R;

import java.text.NumberFormat;

public class ResultatRoiActivity extends AppCompatActivity {

    private Context c = ResultatRoiActivity.this;

    private TextView valeurGain, valeurInvestissement, valeurResultat;

    private ImageView isRentable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_resultat_roi2);

        Toolbar toolbar = findViewById(R.id.roiToolbar);
        setSupportActionBar(toolbar);

        valeurGain = findViewById(R.id.valeurGain);
        valeurInvestissement = findViewById(R.id.valeurInvestissement);
        valeurResultat = findViewById(R.id.valeurResultat);
        isRentable = findViewById(R.id.isRentable);

        Roi roi = getIntent().getParcelableExtra("roi");

        if(roi != null) {

            NumberFormat format = NumberFormat.getInstance();
            format.setMaximumFractionDigits(2);

            String stringGain = format.format(roi.getGainPerte()) + '€';
            String stringInvestissement = format.format(roi.getInvestissement()) + '€';
            String stringResultat = format.format(roi.getResultat()) + '%';

            valeurGain.setText(stringGain);
            valeurInvestissement.setText(stringInvestissement);
            valeurResultat.setText(stringResultat);

            int idCouleur;


            if(roi.getResultat() < 25) {
                idCouleur = getResources().getColor(R.color.red);
                isRentable.setImageDrawable(getResources().getDrawable(R.drawable.x_mark_2));


            } else {
                idCouleur = getResources().getColor(R.color.green);
                isRentable.setImageDrawable(getResources().getDrawable(R.drawable.check_mark));
            }

            valeurResultat.setTextColor(idCouleur);

        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_resultat_roi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.mes_simulations_item) {
            startActivity(new Intent(c,
                    ConsulterRoiActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
