package com.cao.terminal_marittimo.Models;

public class Autista 
{
    private int id;
    private String nome;
    private String cognome;
    private Utente utente;

    public Autista(int id, String nome, String cognome) 
    {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
    }

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCognome() {
        return this.cognome;
    }
    public Utente getUtente() {
        return this.utente;
    }

}
