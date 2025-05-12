package com.cao.terminal_marittimo.Models;

public class Merce 
{
    private int id;
    private String tipologia_merce;

    public Merce(int id, String tipologia_merce) 
    {
        this.id = id;
        this.tipologia_merce = tipologia_merce;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTipologia_merce() {
        return tipologia_merce;
    }
    public void setTipologia_merce(String tipologia_merce) {
        this.tipologia_merce = tipologia_merce;
    }
}
