package com.cao.terminal_marittimo.Entities;

public class Utente {
    private int id;
    private String nome;
    private String email;

    public Utente(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    //Obbligatori per il corretto funzionamento del DAO e del serializzatore
    public  int  getId() {
        return  id;
    }

    public  String  getNome() {
        return  nome;
    }

    public  String  getEmail() {
        return  email;
    }
}