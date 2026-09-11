package org.example.Util;


import java.util.LinkedList;


public class ReporteBatalla {

    private String nombre;
    private String enemigo;
    private int derrotados;

    public ReporteBatalla(String nombre, String enemigo, int derrotados) {
        this.nombre = nombre;
        this.enemigo = enemigo;
        this.derrotados = derrotados;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEnemigo() {
        return enemigo;
    }

    public int getDerrotados() {
        return derrotados;
    }

    public void setDerrotados(int derrotados) {
        this.derrotados += derrotados;
    }

    @Override
    public String toString() {
        return "ReporteBatalla{" +
                "nombre='" + nombre + '\'' +
                ", nombreRival='" + enemigo + '\'' +
                ", derrotados=" + derrotados +
                '}';
    }
}
