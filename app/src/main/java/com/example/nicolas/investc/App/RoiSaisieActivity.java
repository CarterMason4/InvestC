package com.example.nicolas.investc.App;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nicolas.investc.Beans.Roi;
import com.example.nicolas.investc.DAO.RoiDAO;
import com.example.nicolas.investc.R;
import com.example.nicolas.investc.Utilities.RoiUtilities;

import static com.example.nicolas.investc.Utilities.RoiUtilities.calculerRoi;
import static com.example.nicolas.investc.Utilities.Utilities.*;

public class RoiSaisieActivity extends AppCompatActivity {

    private EditText gain_perte, investissement;

    private Button effacer, valider;

    private String textGainPerte = "", textInvestissement = "";
    private Context c = RoiSaisieActivity.this;

    private RoiDAO roiDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roi_saisie2);


        final Toolbar toolbar = findViewById(R.id.roiToolbar);
        setSupportActionBar(toolbar);

        gain_perte = findViewById(R.id.gain_perte);
        investissement = findViewById(R.id.investissement_roi);

        roiDAO = new RoiDAO(c);

        effacer = findViewById(R.id.effacerRoi);
        valider = findViewById(R.id.validerRoi);

        effacer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              gain_perte.setText("");
              investissement.setText("");
            }
        });


        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stringGain = gain_perte.getText().toString();
                String stringInvestissement = investissement.getText().toString();

                if((stringGain.length() == 0 || stringInvestissement.length() == 0) ||
                        (stringGain.equals("0") || stringInvestissement.equals("0"))) {

                    makeToast(c, "Veuillez remplir les champs correctement.");

                } else {

                    Intent intent = new Intent(c, ResultatRoiActivity.class);

                    double gain = Double.parseDouble(stringGain);
                    double investissement = Double.parseDouble(stringInvestissement);

                    Roi roi = new Roi(
                            "ROI",
                            gain,
                            investissement,
                            calculerRoi(gain, investissement));


                    roiDAO.insert(roi);

                    intent.putExtra("roi", roi);

                    startActivity(intent);
                }


            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        gain_perte.setText("");
        investissement.setText("");


    }
}