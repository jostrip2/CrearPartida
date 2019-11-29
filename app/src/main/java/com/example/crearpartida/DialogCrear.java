package com.example.crearpartida;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogCrear extends AppCompatDialogFragment {
    //private AdapterView<?> adapterViewDuracion, adapterViewEfecto, adapterViewQuan;
    private int posQuan;
    private boolean toast = false;
    private DialogCrearListener listener;
    
    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final EditText etNombre, etDesc;
        final Spinner quan;
        final ArrayAdapter<CharSequence> adapterQuan;
    

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_avis, null);
    
        etNombre = view.findViewById(R.id.etNomAvis);
        etDesc = view.findViewById(R.id.etDescAvis);
    
        quan = view.findViewById(R.id.sQuan);
        adapterQuan = ArrayAdapter.createFromResource(getActivity(), R.array.quan, android.R.layout.simple_spinner_item);
        quan.setAdapter(adapterQuan);
        quan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posQuan = position;
            }
    
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                toast = true;
                posQuan = -1;
            }
        });
    
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
    
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nombre = etNombre.getText().toString();
                        if (nombre.equals("-"))
                            toast = true;
    
                        String desc = etDesc.getText().toString();
                        if (desc.equals("-"))
                            toast = true;
                        
                        if (posQuan <= 0 || posQuan >= adapterQuan.getCount())
                            toast = true;
    
                        listener.parametresDialog(nombre, desc, posQuan, toast);
                    }
                });
    
        return builder.create();
    }
    
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (DialogCrearListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "falta implementar DialogCrearListener");
        }
    }
    
    public interface DialogCrearListener{
        void parametresDialog(String nombre, String desc, int quan, boolean toast);
    }
}
