package com.cao.terminal_marittimo.Models;

public class Ritiro {
    private int id;
    private Polizza polizza;
    private Cliente cliente;
    private Guida guida;
    private double peso;
    private String data;

  

    // Getters
    public int getId() {
        return id;
    }
    public Polizza getPolizza() {
        return polizza;
    }

    public Cliente getCliente() {
        return cliente;
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
