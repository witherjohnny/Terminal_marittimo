package com.cao.terminal_marittimo.Models;

public class Cliente {
    private int id;
    private String nome;
    private String cognome;
    private int utente;

    public Cliente(int id, String nome, String cognome, int utente) {
        this.utente = utente;
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;

    }

    public int getId() { return this.id; }
    public String getNome() { return this.nome; }
    public String getCognome() { return this.cognome; }
    public int getUtente() { return this.utente; }

}

