package fr.canm.cyrilstern1.threadcnamtp6;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by cyrilstern1 on 21/04/2016.
 */
public class CantonArrayAdapter extends android.widget.ArrayAdapter{

        private ArrayList<Canton> cantonList;

        public CantonArrayAdapter(Context context,int resource, ArrayList<Canton> cantons) {
            super(context, resource, cantons);
            this.cantonList = cantons;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.cantonrow, parent, false);
            }
            Canton canton = cantonList.get(position);
            TextView cantonName = (TextView) convertView.findViewById(R.id.nom_canton);
            TextView cantonArticle = (TextView) convertView.findViewById(R.id.article_canton);

            cantonName.setText(canton.getArticle());
            cantonArticle.setText(canton.getName());
            return convertView;
        }
    }

