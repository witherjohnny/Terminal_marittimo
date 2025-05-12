package com.cao.terminal_marittimo.Models;

public class Porto {
    private int id;
    private Porto porto;
    private String nazionalita;

    public Porto(int id, Porto porto, String nazionalita) {
        this.id = id;
        this.porto = porto;
        this.nazionalita = nazionalita;
    }

    public int getId() {
        return this.id;
    }

    public Porto getPorto() {
        return this.porto;
    }

    public String getNazionalita() {
        return this.nazionalita;
    }
}
