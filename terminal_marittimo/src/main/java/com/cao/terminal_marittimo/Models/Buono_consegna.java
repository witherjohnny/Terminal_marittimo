package com.cao.terminal_marittimo.Models;

public class Buono_consegna {

    private int id;
    private double peso;
    private int id_cliente;
    private Polizza polizza;

    public Buono_consegna(int id, double peso, int id_cliente, Polizza polizza){
        this.id = id;
        this.peso = peso;
        this.id_cliente = id_cliente;
        this.polizza = polizza;
    }
    public int getId() {
        return id;
    }
    public double getPeso() {
        return peso;
    }
    public int getId_cliente() {
        return id_cliente;
    }
    public Polizza getPolizza(){
        return polizza;
    }
}