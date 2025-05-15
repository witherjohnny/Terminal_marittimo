package com.cao.terminal_marittimo.Models;

public class Autista 
{
    private int id;
    private String nome;
    private String cognome;
    private Cliente cliente;

    public Autista(int id, String nome, String cognome, Cliente cliente) 
    {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.cliente = cliente;
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
    public Cliente getCliente() {
        return this.cliente;
    }

}
