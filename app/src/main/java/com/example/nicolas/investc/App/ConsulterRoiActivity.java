package com.example.nicolas.investc.App;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import com.example.nicolas.investc.Beans.Roi;
import com.example.nicolas.investc.DAO.RoiDAO;
import com.example.nicolas.investc.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.nicolas.investc.Utilities.RoiUtilities.calculerRoi;
import static com.example.nicolas.investc.Utilities.Utilities.makeToast;

public class ConsulterRoiActivity extends AppCompatActivity {

    private ListView myList;
    private List<Indicateur> indicateurs;
    private RoiDAO rdao;
    private IndicateurAdapter adapter;

    private Context c = ConsulterRoiActivity.this;

    private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);

    private String gain = "", investissementS = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        setTheme(R.style.RoiTheme);

        myList = findViewById(R.id.myList);

        Toolbar toolbar = findViewById(R.id.simulationsToolbar);
        setSupportActionBar(toolbar);

        rdao = new RoiDAO(c);

        indicateurs = new ArrayList<>();

        indicateurs = rdao.selectTous();

        if(indicateurs.size() > 0) {

            adapter = new IndicateurAdapter(c,
                    R.layout.resultat_layout,
                    indicateurs);

            myList.setAdapter(adapter);
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


                rdao.deleteById(i.getId());

                indicateurs.remove(i);

                adapter.notifyDataSetChanged();

                makeToast(c, "Un élément supprimé avec succès.");

            }
        });


        builder.setNeutralButton("Consulter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(c,
                        ResultatRoiActivity.class)
                .putExtra("codeActivite", 2)
                .putExtra("roi", (Roi) i));
            }
        });

        builder.create().show();
    }

    public void showPopModifier(final Indicateur i, final int index) {

        final Dialog dialog = new Dialog(c);
        dialog.setContentView(R.layout.popup_saisie_roi);
        dialog.setCancelable(true);

        final EditText edtGain = dialog.findViewById(R.id.popUpGainEdt);
        final EditText edtInvest = dialog.findViewById(R.id.popUpInvestEdt);
        final Button btnValider = dialog.findViewById(R.id.popUpBtnValider);
        final Button btnEffacer = dialog.findViewById(R.id.popUpBtnEffacer);


        Roi tempRoi = (Roi) i;
        edtGain.setText(String.valueOf(tempRoi.getGainPerte()));
        edtInvest.setText(String.valueOf(tempRoi.getInvestissement()));


        gain = edtGain.getText().toString();
        investissementS = edtInvest.getText().toString();

        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stringGain = edtGain.getText().toString();
                String stringInvestissement = edtInvest.getText().toString();

                if(stringGain.length() == 0 || stringInvestissement.length() == 0) {

                    makeToast(c, "Veuillez remplir tous les champs.");

                } else {

                    double doubleGain = Double.parseDouble(edtGain.getText().toString());
                    double doubleInvestissement = Double.parseDouble(edtInvest.getText().toString());

                    Roi newRoi = new Roi(
                            "ROI",
                            format.format(new Date()),
                            doubleGain,
                            doubleInvestissement,
                            calculerRoi(doubleGain, doubleInvestissement));

                    rdao.updateById(i.getId(), newRoi);

                    indicateurs.set(index, newRoi);

                    makeToast(c, "Élément modifié avec succès.");

                    adapter.notifyDataSetChanged();

                    dialog.dismiss();
                }



            }
        });


        btnEffacer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtGain.setText("");
                edtInvest.setText("");
            }
        });

        dialog.show();
    }




    public void showDeleteAllPop() {

        final AlertDialog.Builder builder = new  AlertDialog.Builder(c);
        builder.setCancelable(true);

        builder.setMessage(R.string.tout_supprimer);

        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                rdao.deleteAll();

                indicateurs.removeAll(indicateurs);

                adapter.notifyDataSetChanged();

                makeToast(c, "Tous les éléments ont été supprimés.");

            }
        });

        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
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

}
