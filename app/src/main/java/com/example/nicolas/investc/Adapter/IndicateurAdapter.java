package com.example.nicolas.investc.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nicolas.investc.Beans.Indicateur;
import com.example.nicolas.investc.Beans.Roi;
import com.example.nicolas.investc.Beans.Van;
import com.example.nicolas.investc.R;

import java.text.NumberFormat;
import java.util.List;


public class IndicateurAdapter extends ArrayAdapter<Indicateur> {

    private Context context;
    private int resource;
    private List<Indicateur> indicateurs;

    public IndicateurAdapter(@NonNull Context context, int resource, @NonNull List<Indicateur> objects) {

        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.indicateurs = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

       ViewHolder vh;

       LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

       if(convertView == null) {

           vh = new ViewHolder();

            if(inflater != null) {


                convertView = inflater.inflate(resource, parent, false);
                vh.indicateur = convertView.findViewById(R.id.indicateur);
                vh.resultat = convertView.findViewById(R.id.resultat);
                vh.date = convertView.findViewById(R.id.date);

                convertView.setTag(vh);
            }

        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        Indicateur i = getItem(position);

        if(i != null) {

            NumberFormat format = NumberFormat.getInstance();
            format.setMaximumFractionDigits(2);

            String temp = format.format(i.getResultat());

            vh.indicateur.setText(i.getNomIndicateur());
            vh.date.setText(i.getDateSimulation());

            int idCouleur = 0;

            if(i instanceof Roi) {

                temp += '%';

                if(i.getResultat() > 25) {

                    idCouleur = ContextCompat.getColor(context, R.color.green);

                } else {

                    idCouleur = ContextCompat.getColor(context, R.color.darkOrange);
                }

            } else if(i instanceof Van) {

                temp += '€';

                if(i.getResultat() < 0) {
                    idCouleur = ContextCompat.getColor(context, R.color.darkOrange);
                } else {
                    idCouleur = ContextCompat.getColor(context, R.color.green);
                }
            }

            convertView.setBackgroundColor(idCouleur);

            vh.resultat.setText(temp);

            int colorWhite = ContextCompat.getColor(context, R.color.white);

            vh.indicateur.setTextColor(colorWhite);
            vh.resultat.setTextColor(colorWhite);
            vh.date.setTextColor(colorWhite);

        }


        return convertView;
    }

    static class ViewHolder {

        TextView indicateur;
        TextView resultat;
        TextView date;
    }


}
