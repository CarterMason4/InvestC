package com.example.nicolas.investc.App;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.nicolas.investc.R;

public class ChoixSimulationActivity extends AppCompatActivity {

    private Context c = ChoixSimulationActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_simulation);

    }
    public void getToSimulationRoi(View v) {
        startActivity(new Intent(c,
                ConsulterRoiActivity.class));
    }

    public void getToSimulationVan(View v) {
        startActivity(new Intent(c,
                ConsulterVanActivity.class));
    }

}
