package com.example.nicolas.investc.App;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.nicolas.investc.R;

public class ChoixIndicateurActivity extends AppCompatActivity {

    private Context c = ChoixIndicateurActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix);

        Toolbar toolbar = findViewById(R.id.choixToolbar);
        setSupportActionBar(toolbar);

    }

    public void runRoi(View v) {
        startActivity(new Intent(c,
                RoiSaisieActivity.class));
    }

    public void runVan(View v) {
        startActivity(new Intent(c,
                VanSaisieActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
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
