package com.cao.terminal_marittimo.Models;

public class Viaggio 
{
    private int id;
    private int id_nave;
    private java.sql.Date data_partenza;
    private java.sql.Date data_allibramento;
    private int id_porto_partenza;
    private int id_porto_arrivo;

    public Viaggio(int id, int id_nave, java.sql.Date data_partenza, int id_porto_partenza, int id_porto_arrivo, java.sql.Date data_allibramento) {
        this.id = id;
        this.id_nave = id_nave;
        this.data_partenza = data_partenza;
        this.id_porto_partenza = id_porto_partenza;
        this.id_porto_arrivo = id_porto_arrivo;
        this.data_allibramento = data_allibramento;
    }

    public Viaggio(int id, int id_nave, java.sql.Date data_partenza, int id_porto_partenza) {
        this(id, id_nave, data_partenza, id_porto_partenza, 0, null);
    }

    public int getId() {
        return this.id;
    }

    public int getId_nave() {
        return this.id_nave;
    }

    public java.sql.Date getData_partenza() {
        return this.data_partenza;
    }

    public java.sql.Date getData_allibramento() {
        return this.data_allibramento;
    }

    public int getId_porto_partenza() {
        return this.id_porto_partenza;
    }

    public int getId_porto_arrivo() {
        return this.id_porto_arrivo;
    }
}
