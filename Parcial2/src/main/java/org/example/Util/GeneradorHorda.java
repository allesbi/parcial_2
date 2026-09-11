package org.example.Util;

import org.example.Model.Pokemon;
import org.example.Model.PokedexKanto;
import java.util.Random;

//Como dice el nombre es para generar de manera aleatorea TODA la horda, desde los nombres que toma de PokedexKanto hasta las estadisticas.
public class GeneradorHorda {

    private static final Random rand = new Random();

    public static Pokemon[] generarHordaAleatoria(int cantidad) {
        Pokemon[] horda = new Pokemon[cantidad];
        String[] nombresBase = PokedexKanto.NOMBRES_PRIMERA_GENERACION;

        for (int i = 0; i < cantidad; i++) {
            String nombre = nombresBase[rand.nextInt(nombresBase.length)];

            int hp = rand.nextInt(20) + 30;
            int ataque = rand.nextInt(15) + 20;
            int defensa = rand.nextInt(15) + 20;
            int velocidad = rand.nextInt(15) + 20;

            horda[i] = new Pokemon(nombre, hp, ataque, defensa, velocidad, 50);
        }

        return horda;
    }
}
