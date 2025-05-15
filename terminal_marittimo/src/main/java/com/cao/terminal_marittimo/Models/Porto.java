package com.cao.terminal_marittimo.Models;

public class Porto {
    private int id;
    private String nome;
    private String nazionalita;

    public Porto(int id, String nome, String nazionalita) {
        this.id = id;
        this.nome = nome;
        this.nazionalita = nazionalita;
    }

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getNazionalita() {
        return this.nazionalita;
    }
}
