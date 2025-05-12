package com.cao.terminal_marittimo.Models;

public class Viaggio 
{
    private int id;
    private Nave nave;
    private String data_partenza;
    private String data_allibramento;

    public Viaggio(int id, Nave nave, String data_partenza, String data_allibramento) {
        this.id = id;
        this.nave = nave;
        this.data_partenza = data_partenza;
        this.data_allibramento = data_allibramento;
    }

    public int getId() {
        return this.id;
    }

    public Nave getNave() {
        return this.nave;
    }

    public String getData_partenza() {
        return this.data_partenza;
    }

    public String getData_allibramento() {
        return this.data_allibramento;
    }
}
