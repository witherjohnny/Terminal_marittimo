package com.cao.terminal_marittimo.Models;

public class Ritiro {
    private int id;
    private Buono_consegna buono;
    private Guida guida;
    private double peso;
    private String data;

    // Constructor
    public Ritiro(int id, Buono_consegna buono, Guida guida, String data) {
        this.id = id;
        this.buono = buono;
        this.guida = guida;
        this.data = data;
    }
    // Getters
    public int getId() {
        return id;
    }

    public Buono_consegna getBuono() {
        return buono;
    }

    public Guida getGuida() {
        return guida;
    }

    public double getPeso() {
        return peso;
    }

    public String getData() {
        return data;
    }
}
