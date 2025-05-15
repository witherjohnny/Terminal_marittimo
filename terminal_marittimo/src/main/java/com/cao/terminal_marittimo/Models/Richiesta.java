package com.cao.terminal_marittimo.Models;

public class Richiesta {
    private int id_utente;
    private Polizza polizza;
    private float peso;

    public Richiesta(int id_utente, Polizza polizza, float peso) {
        this.id_utente = id_utente;
        this.polizza = polizza;
        this.peso = peso;
    }

    public int getId_utente() {
        return id_utente;
    }
    public Polizza getPolizza() {
        return polizza;
    }
    public float getPeso() {
        return peso;
    }
}
