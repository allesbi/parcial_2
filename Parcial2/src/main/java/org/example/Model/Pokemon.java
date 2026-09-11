package org.example.Model;

public class Pokemon {

    private String nombre;
    private int puntosDeVidaMaximos;
    private int ataque;
    private int defensa;
    private int velocidad; //lo siento profe pero pokemon sin velocidad no tiene sentido
    private int exp;
    private Pokemon siguienteEvolucion;


    public Pokemon(String nombre, int puntosDeVidaMaximos, int ataque, int defensa, int velocidad, int exp) {
        this.nombre = nombre;
        this.puntosDeVidaMaximos = puntosDeVidaMaximos;
        this.ataque = ataque;
        this.defensa = defensa;
        this.velocidad = velocidad;
        this.exp = exp;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntosDeVidaMaximos() {
        return puntosDeVidaMaximos;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int getExp() {
        return exp;
    };

    public Pokemon getSiguienteEvolucion() {
        return siguienteEvolucion;
    }

    public void setSiguienteEvolucion(Pokemon siguienteEvolucion) {
        this.siguienteEvolucion = siguienteEvolucion;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "nombre='" + nombre + '\'' +
                ", puntosDeVidaMaximos=" + puntosDeVidaMaximos +
                ", ataque=" + ataque +
                ", defensa=" + defensa +
                ", siguienteEvolucion=" + siguienteEvolucion +
                '}';
    }
}
