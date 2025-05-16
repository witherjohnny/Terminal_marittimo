package com.cao.terminal_marittimo.Models;

public class Guida {
    private int id;
    private Camion camion;
    private Autista autista;
    public Guida(int id, Camion camion, Autista autista) {
        this.id = id;
        this.camion = camion;
        this.autista = autista;
    }
    // Getters and Setters
    public int getId() {
        return id;
    }

    public Camion getCamion() {
        return camion;
    }

    public Autista getAutista() {
        return autista;
    }


}
