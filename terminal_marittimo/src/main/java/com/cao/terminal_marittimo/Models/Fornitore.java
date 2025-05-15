package com.cao.terminal_marittimo.Models;

public class Fornitore {
    private int id;
    private String nome;

    public Fornitore(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() { return this.id; }
    public String getNome() { return this.nome; }

}

