package com.cao.terminal_marittimo.Models;

public class Utente {
    private int id;
    private String nome;
    private String ruolo;
    

    public Utente(int id, String nome, String ruolo) {
        this.id = id;
        this.nome = nome;
        this.ruolo = ruolo;
    }

    //Obbligatori per il corretto funzionamento del DAO e del serializzatore
    public  int  getId() {
        return  id;
    }

    public  String  getNome() {
        return  nome;
    }
    public  String  getRuolo() {
        return  ruolo;
    }
}