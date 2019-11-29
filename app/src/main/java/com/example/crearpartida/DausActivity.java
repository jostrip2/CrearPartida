package com.example.crearpartida;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class DausActivity extends AppCompatActivity implements View.OnClickListener{

    Button back, throwDau;
    Random r = new Random();
    TextView dau;
    EditText etcares;
    int rand, cares = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daus);
        
        back = findViewById(R.id.bDausBack);
        back.setOnClickListener(this);

        throwDau = findViewById(R.id.bThrow);
        throwDau.setOnClickListener(this);

        dau = findViewById(R.id.tResultatDau);

        etcares = findViewById(R.id.etCares);
    }


    @Override
    public void onClick(View v) {
        
        switch (v.getId()){
            case R.id.bDausBack:
                finish();
                break;

            case R.id.bThrow:
                if(etcares.getText() != null) {
                    cares = Integer.parseInt(etcares.getText().toString());
                    if (cares >= 2) {
    
                        rand = r.nextInt(cares);
                        dau.setText(String.valueOf(rand + 1));
                    }
                    else
                        Toast.makeText(getApplicationContext(), "El número de caras debe ser superior a 2", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "Introduzca el número de caras del dado", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
