package com.jldes.amuva;

/**
 * Created by JesúsDiego on 09/04/2015.
 */
public class Robot {
    private String nombre;
    private String categoria;
    private int posicion;

    public Robot() {

    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getPosicion() {
        return posicion;
    }
}
