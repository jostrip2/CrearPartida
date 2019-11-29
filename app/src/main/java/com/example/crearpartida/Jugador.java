package com.example.crearpartida;

import java.util.ArrayList;

public class Jugador {
    private int vida;
    private String nom;
    private ManaPool playerMana = new ManaPool();
    private Avis[] llistaAvisos;
    private int numAvisos;

    public Jugador(){

    }
    public Jugador(int vida, String nom) {
        this.vida = vida;
        this.nom = nom;
        this.numAvisos = 0;
    }
    public void setVida(int vida){
        this.vida = vida;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public String getNom(){
        return nom;
    }
    public ManaPool getPlayerMana(){
        return playerMana;
    }
    public void decreaseVida(){ vida--; }
    public void incrementVida(){ vida++; }
    
    public void setAvis (Avis avis){
        llistaAvisos[numAvisos] = avis;
        numAvisos++;
    }
    

}
