package com.example.nicolas.investc.App;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.nicolas.investc.Adapter.IndicateurAdapter;
import com.example.nicolas.investc.Beans.Indicateur;
import com.example.nicolas.investc.Beans.Van;
import com.example.nicolas.investc.DAO.VanDAO;
import com.example.nicolas.investc.R;
import com.example.nicolas.investc.Utilities.VanUtilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.nicolas.investc.Utilities.Utilities.*;

public class ConsulterVanActivity extends AppCompatActivity {

    private ListView myList;
    private List<Indicateur> indicateurs;
    private VanDAO vdao;
    private IndicateurAdapter adapter;

    private Context c = ConsulterVanActivity.this;

    private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        myList = findViewById(R.id.myList);
        indicateurs = new ArrayList<>();
        vdao = new VanDAO(c);
        Toolbar toolbar = findViewById(R.id.simulationsToolbar);
        setSupportActionBar(toolbar);

        indicateurs = vdao.selectTous();

        if(indicateurs.size() > 0) {

            adapter = new IndicateurAdapter(c,
                    R.layout.resultat_layout,
                    indicateurs);

            myList.setAdapter(adapter);
        } else {
            makeToast(c, "Aucun élément à afficher.");
        }

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Indicateur i = (Indicateur) parent.getItemAtPosition(position);

                if(i != null) {
                    runDialog(i, view, position);
                }

            }
        });

    }


    public void runDialog(final Indicateur i, View v, final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);

        builder.setMessage(R.string.que_voulez_vous_faire);
        builder.setCancelable(true);

        builder.setPositiveButton(R.string.modifier,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showPopModifier(i, position);
                    }
                });

        builder.setNegativeButton(R.string.supprimer, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                vdao.deleteById(i.getId());

                indicateurs.remove(i);

                adapter.notifyDataSetChanged();

                makeToast(c, "Un élément supprimé avec succès.");

            }
        });


        builder.setNeutralButton("Consulter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(c,
                        ResultatVanActivity.class)
                        .putExtra("van", (Van) i));
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void showPopModifier(final Indicateur i, final int position) {

        final Dialog dialog = new Dialog(c);
        dialog.setContentView(R.layout.popup_saisie_van);
        dialog.setCancelable(true);

        final EditText edtNbAnnees = dialog.findViewById(R.id.popUpEdtNbAnnees);
        final EditText edtInvestissement = dialog.findViewById(R.id.popUpEdtInvestissementVan);
        final EditText edtTaux = dialog.findViewById(R.id.popUpEdtTaux);
        final EditText edtFlux = dialog.findViewById(R.id.popUpEdtFlux);

        final Button btnValider = dialog.findViewById(R.id.popUpBtnValiderVan);
        final Button btnEffacer = dialog.findViewById(R.id.popUpBtnEffacerVan);


        Van tempVan = (Van) i;

        edtNbAnnees.setText(String.valueOf(tempVan.getNbAnnees()));
        edtInvestissement.setText(String.valueOf(tempVan.getInvestissement()));
        edtTaux.setText(String.valueOf(tempVan.getTaux()));
        edtFlux.setText(String.valueOf(tempVan.getFlux()));

        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stringNbAnnees = edtNbAnnees.getText().toString();
                String stringInvestissement = edtInvestissement.getText().toString();
                String stringTaux = edtTaux.getText().toString();
                String stringFlux = edtFlux.getText().toString();

                if(stringNbAnnees.length() == 0 ||
                        stringInvestissement.length() == 0 ||
                        stringTaux.length() == 0 ||
                        stringFlux.length() == 0) {

                    makeToast(c, "Veuillez remplir tous les champs.");


                } else {

                    int nbAnnees = Integer.parseInt(stringNbAnnees);
                    double investissement = Double.parseDouble(stringInvestissement);
                    double taux = Double.parseDouble(stringTaux);
                    double flux = Double.parseDouble(stringFlux);

                    Van van = new Van("VAN",
                            format.format(new Date()),
                            nbAnnees,
                            investissement,
                            taux,
                            flux,
                            VanUtilities.calculerVan(nbAnnees, investissement, taux, flux));

                    vdao.updateById(i.getId(), van);

                    indicateurs.set(position, van);

                    adapter.notifyDataSetChanged();

                    dialog.dismiss();

                    makeToast(c, "Élement modifié avec succès.");

                }


            }
        });


        btnEffacer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtNbAnnees.setText("");
                edtInvestissement.setText("");
                edtTaux.setText("");
                edtFlux.setText("");
            }
        });

        dialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.meun_consulter_simu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.supprimer_tout) {

            if(indicateurs.size() == 0) {
                makeToast(c, "Il n'y a aucun élément à supprimer.");
            } else {
                showDeleteAllPop();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showDeleteAllPop() {

        final AlertDialog.Builder builder = new  AlertDialog.Builder(c);
        builder.setCancelable(true);

        builder.setMessage(R.string.tout_supprimer);

        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                vdao.deleteAll();

                indicateurs.removeAll(indicateurs);

                adapter.notifyDataSetChanged();

                makeToast(c, "Tous les éléments ont été supprimés.");

            }
        });

        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
