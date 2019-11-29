package com.example.crearpartida;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ManaAvaiable extends AppCompatActivity implements View.OnClickListener{

    Button buttonTotal, buttonAccept, buttonReset, buttonSpent, buttonMenu;
    Globals player = Globals.getInstance();
    String background, text;
    TextView aux;
    ImageButton[] add, sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mana_avaiable);
        int resID, id;
        add = new ImageButton[32];
        sub = new ImageButton[32];

        //visualitzo les files necessaries per a visualitzar el mana que tinc
        for(int i = 0; i < player.getPlayer().getPlayerMana().getRowAvaiable(); i++){
            resID = getResources().getIdentifier("row" + i,"id", getPackageName());
            findViewById(resID).setVisibility(View.VISIBLE);
        }

        for(int i = 0; i < player.getPlayer().getPlayerMana().getQuantManaTotal(); i++) {
            //visualitzo el mana que tinc en les files anteriors
            resID = getResources().getIdentifier("mana" + i, "id", getPackageName());
            findViewById(resID).setVisibility(View.VISIBLE);

            //obtinc el BackGround especific pel tipus de mana
            background = player.getPlayer().getPlayerMana().getManaArray()[i].getBackground();
            id = getResources().getIdentifier(background, "drawable", getPackageName());
            findViewById(resID).setBackgroundResource(id);

            //visualitzo la quantitat de mana total
            resID = getResources().getIdentifier("quant" + i,"id", getPackageName());
            aux = findViewById(resID);
            text = "" + player.getPlayer().getPlayerMana().getManaArray()[i].getAvaiable();
            aux.setText(text);
            aux.setTextColor(Color.WHITE);
            aux.setTextSize(30);

            //OnClickListeners dels botons de afegir i eliminar
            resID = getResources().getIdentifier("add" + i,"id", getPackageName());
            add[i] = findViewById(resID);
            add[i].setOnClickListener(this);
            resID = getResources().getIdentifier("sub" + i,"id", getPackageName());
            sub[i] = findViewById(resID);
            sub[i].setOnClickListener(this);
        }

        for(int i = 0; i < player.getPlayer().getPlayerMana().getRowSpent(); i++){
            resID = getResources().getIdentifier("row0" + i,"id", getPackageName());
            findViewById(resID).setVisibility(View.VISIBLE);
        }

        for(int i = 0; i < player.getPlayer().getPlayerMana().getQuantManaSpent(); i++) {
            //visualitzo el mana que tinc en les files anteriors
            resID = getResources().getIdentifier("manaWasted" + i, "id", getPackageName());
            findViewById(resID).setVisibility(View.VISIBLE);

            //obtinc el BackGround especific pel tipus de mana
            background = player.getPlayer().getPlayerMana().getManaSpent()[i].getBackground();
            id = getResources().getIdentifier(background, "drawable", getPackageName());
            findViewById(resID).setBackgroundResource(id);

            //visualitzo la quantitat de mana total
            resID = getResources().getIdentifier("quantWasted" + i,"id", getPackageName());
            aux = findViewById(resID);
            text = "" + player.getPlayer().getPlayerMana().getManaSpent()[i].getTotal();
            aux.setText(text);
            aux.setTextColor(Color.WHITE);
            aux.setTextSize(30);
        }

        buttonTotal = findViewById(R.id.bTotal);
        buttonTotal.setOnClickListener(this);
        buttonAccept= findViewById(R.id.bAccept);
        buttonAccept.setOnClickListener(this);
        buttonReset= findViewById(R.id.bReset);
        buttonReset.setOnClickListener(this);
        buttonSpent= findViewById(R.id.bSpent);
        buttonSpent.setOnClickListener(this);
        buttonMenu= findViewById(R.id.bMenu);
        buttonMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        int resID, id;
        if(v.getId() == R.id.bTotal){
            Intent toTotal = new Intent(this, ManaTotal.class);
            startActivity(toTotal);
            finish();
        }else if(v.getId() == R.id.bMenu) {
            this.finish();
        }else if(v.getId() == R.id.bAccept){

        }else if(v.getId() == R.id.bReset){

        }else if(v.getId() == R.id.bSpent){
            //visualitzar l'historial de mana gastat
            //startActivity(afegirMana);
        }else{
            for(int i = 0; i < player.getPlayer().getPlayerMana().getQuantManaTotal(); i++) {
                resID = getResources().getIdentifier("add" + i,"id", getPackageName());
                if(v.getId() == resID){
                    player.getPlayer().getPlayerMana().getManaArray()[i].addOneToAvaiable();
                    resID = getResources().getIdentifier("quant" + i,"id", getPackageName());
                    aux = findViewById(resID);
                    text = "" + player.getPlayer().getPlayerMana().getManaArray()[i].getAvaiable();
                    aux.setText(text);
                }
                resID = getResources().getIdentifier("sub" + i,"id", getPackageName());
                if(v.getId() == resID){
                    player.getPlayer().getPlayerMana().getManaArray()[i].subOneToAvaiable();
                    resID = getResources().getIdentifier("quant" + i,"id", getPackageName());
                    aux = findViewById(resID);
                    text = "" + player.getPlayer().getPlayerMana().getManaArray()[i].getAvaiable();
                    aux.setText(text);

                    int pos = player.getPlayer().getPlayerMana().getManaPosition(
                            player.getPlayer().getPlayerMana().getManaArray()[i],
                            player.getPlayer().getPlayerMana().getManaSpent(),
                            player.getPlayer().getPlayerMana().getQuantManaSpent());
                    if(pos == -1){
                        player.getPlayer().getPlayerMana().addManaAtSpent(player.getPlayer().getPlayerMana().getManaArray()[i].Copy());

                        resID = getResources().getIdentifier("row0" + (player.getPlayer().getPlayerMana().getRowSpent()-1),"id", getPackageName());
                        findViewById(resID).setVisibility(View.VISIBLE);

                        resID = getResources().getIdentifier("manaWasted" + (player.getPlayer().getPlayerMana().getQuantManaSpent()-1), "id", getPackageName());
                        findViewById(resID).setVisibility(View.VISIBLE);

                        //obtinc el BackGround especific pel tipus de mana
                        background = player.getPlayer().getPlayerMana().getManaSpent()[i].getBackground();
                        id = getResources().getIdentifier(background, "drawable", getPackageName());
                        findViewById(resID).setBackgroundResource(id);

                        //visualitzo la quantitat de mana total
                        resID = getResources().getIdentifier("quantWasted" + (player.getPlayer().getPlayerMana().getQuantManaSpent()-1),"id", getPackageName());
                        aux = findViewById(resID);
                        text = "" + player.getPlayer().getPlayerMana().getManaSpent()[i].getTotal();
                        aux.setText(text);
                        aux.setTextColor(Color.WHITE);
                        aux.setTextSize(30);
                    }
                    else{
                        player.getPlayer().getPlayerMana().getManaSpent()[pos].addOneToTotal();
                        resID = getResources().getIdentifier("quantWasted" + i,"id", getPackageName());
                        aux = findViewById(resID);
                        text = "" + player.getPlayer().getPlayerMana().getManaSpent()[i].getTotal();
                        aux.setText(text);
                    }
                }
            }
        }
    }
}
