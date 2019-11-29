package com.example.crearpartida.triggers;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.crearpartida.Avis;
import com.example.crearpartida.Avisos;
import com.example.crearpartida.Globals;
import com.example.crearpartida.R;

public class TriggersFragment extends Fragment {

    TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
    TableRow row;
    TableLayout tlAvis;
    private int posActual;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_triggers, container, false);
        Intent toTriggers = new Intent(getActivity(), Avisos.class);
        startActivity(toTriggers);


        tlAvis = findViewById(R.id.tlTriggers);

        TextView tvNom;
        String nom;
        for(posActual = 0; posActual < Globals.getInstance().getGame().getNumJug(); posActual++){
            row = new TableRow(this);
            row.setLayoutParams(lp);
            nom = Globals.getInstance().getGame().getListaJug()[posActual].getNom();
            tvNom = new TextView(this);
            tvNom.setText(nom);
            tvNom.setPadding(8, 8, 8, 8);
            row.addView(tvNom);
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent loadAvis = new Intent(getContext(), Avisos.class);
                    startActivity(loadAvis);
                }
            });
            tlAvis.addView(row);
        }

        return root;
    }
}