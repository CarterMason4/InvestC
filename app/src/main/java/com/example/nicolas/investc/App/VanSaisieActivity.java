package com.example.nicolas.investc.App;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nicolas.investc.Beans.Van;
import com.example.nicolas.investc.DAO.VanDAO;
import com.example.nicolas.investc.R;
import com.example.nicolas.investc.Utilities.VanUtilities;

import static com.example.nicolas.investc.Utilities.Utilities.*;

public class VanSaisieActivity extends AppCompatActivity {

    private EditText edtNbAnnees;
    private EditText edtInvestissementVan;
    private EditText edtFlux;
    private EditText edtTaux;

    private Button effacerVan;
    private Button validerVan;

    private Context c = VanSaisieActivity.this;

    private VanDAO vdao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_van_saisie);

        edtNbAnnees = findViewById(R.id.edtNbAnnes);
        edtInvestissementVan = findViewById(R.id.edtInvestissementVan);
        edtFlux = findViewById(R.id.edtFlux);
        edtTaux = findViewById(R.id.edtTaux);

        effacerVan = findViewById(R.id.effacerVan);
        validerVan = findViewById(R.id.validerVan);

        vdao = new VanDAO(c);

        effacerVan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emptyAllFields();
            }
        });

        validerVan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nbAnnees = edtNbAnnees.getText().toString();
                String investissement = edtInvestissementVan.getText().toString();
                String taux = edtTaux.getText().toString();
                String flux = edtFlux.getText().toString();


                // TODO Faire une vérification par rapport à 0.

                if(nbAnnees.length() == 0 ||
                        investissement.length() == 0 ||
                        flux.length() == 0 ||
                        taux.length() == 0) {

                    makeToast(c, "Veuillez remplir tous les champs");

                } else {

                    int intNbAnnees = Integer.parseInt(nbAnnees);
                    double doubleInvestissement = Double.parseDouble(investissement);
                    double doubleTaux = Double.parseDouble(taux);
                    double doubleFlux = Double.parseDouble(flux);


                    Van van = new Van("VAN",
                            intNbAnnees,
                            doubleInvestissement,
                            doubleTaux,
                            doubleFlux,
                            VanUtilities.calculerVan(intNbAnnees, doubleInvestissement, doubleTaux, doubleFlux));

                    vdao.insert(van);

                    Intent intent = new Intent(c,
                            ResultatVanActivity.class);

                    intent.putExtra("codeActivite", 1);
                    intent.putExtra("van", van);

                    startActivity(intent);

                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        emptyAllFields();
    }


    public void emptyAllFields() {
        edtNbAnnees.setText("");
        edtInvestissementVan.setText("");
        edtFlux.setText("");
        edtTaux.setText("");
    }
}
