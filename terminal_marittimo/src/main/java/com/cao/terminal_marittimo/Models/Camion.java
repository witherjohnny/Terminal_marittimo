package com.cao.terminal_marittimo.Models;

public class Camion 
{
    private int id;
    private String targa;

    
    public Camion(int id, String targa) {
        this.id = id;
        this.targa = targa;

    }

    public String getTarga() {
        return targa;
    }
    public int getId() {
        return id;
    }

}
