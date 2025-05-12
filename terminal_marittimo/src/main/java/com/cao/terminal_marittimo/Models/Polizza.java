package com.cao.terminal_marittimo.Models;

public class Polizza 
{
    private int id;
    private Viaggio viaggio;
    private Fornitore fornitore;
    private float peso;
    private Merce merce;
    private int durata_franchigia;
    private float costo_franchigia;

    public Polizza(int id, Viaggio viaggio, Fornitore fornitore, float peso, Merce id_merce, int durata_franchigia, float costo_franchigia) {
        this.id = id;
        this.viaggio = viaggio;
        this.fornitore = fornitore;
        this.peso = peso;
        this.merce = id_merce;
        this.durata_franchigia = durata_franchigia;
        this.costo_franchigia = costo_franchigia;
    }

    public int getId() {
        return id;
    }
    public Viaggio getViaggio() {
        return viaggio;
    }
    public Fornitore getFornitore() {
        return fornitore;
    }

    public float getPeso() {
        return peso;
    }
    public Merce getMerce() {
        return merce;
    }
    public int getDurata_franchigia() {
        return durata_franchigia;
    }
    public float getCosto_franchigia() {
        return costo_franchigia;
    }
}
