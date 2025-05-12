package com.cao.terminal_marittimo.Models;

public class Nave
{
    private int id;
    private String nome;

    public Nave(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }


}
