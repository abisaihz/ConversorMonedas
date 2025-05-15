package com.alura.conversor.metodos;

public class Moneda {
    private String simbolo;
    private String descripcion;


    public Moneda(String simbolo, String descripcion){
        this.simbolo = simbolo;
        this.descripcion = descripcion;


    }


    public String getSimbolo() {
        return simbolo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return simbolo + ": " + descripcion;
    }
}
