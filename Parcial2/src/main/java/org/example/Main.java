package org.example;
import org.example.Model.Pokemon;
import org.example.Model.LineaEvolutiva;
import org.example.Util.GeneradorHorda;
import org.example.Util.ReporteBatalla;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Pokemon charmander = new Pokemon("Charmander", 39, 52, 43, 42, 1500);
        Pokemon charmeleon = new Pokemon("Charmeleon", 58, 64, 58, 63, 5000);
        Pokemon charizard = new Pokemon("Charizard", 78, 84, 78, 75, -1);

        Pokemon gible = new Pokemon("Gible", 49, 62, 63, 32, 1500);
        Pokemon gabite = new Pokemon("Gabite", 68, 74, 78, 53, 5000);
        Pokemon garchomp = new Pokemon("Garchomp", 98, 124, 108, 85, -1);

        Pokemon squirtle = new Pokemon("Squirtle", 29, 42, 43, 40, 1500);
        Pokemon wartortle = new Pokemon("Wartortle", 48, 54, 68, 66, 5000);
        Pokemon blastoise = new Pokemon("Blastoise", 88, 74, 88, 76, -1);
        
        charmander.setSiguienteEvolucion(charmeleon);
        charmeleon.setSiguienteEvolucion(charizard);

        gible.setSiguienteEvolucion(gabite);
        gabite.setSiguienteEvolucion(garchomp);

        squirtle.setSiguienteEvolucion(wartortle);
        wartortle.setSiguienteEvolucion(blastoise);


        LineaEvolutiva miPokemon = new LineaEvolutiva(charmander);
        LineaEvolutiva miPokemon2 = new LineaEvolutiva(gible);
        LineaEvolutiva miPokemon3 = new LineaEvolutiva(squirtle);

        Queue<LineaEvolutiva> miEquipo = new LinkedList<>();

        miEquipo.add(miPokemon);
        miEquipo.add(miPokemon2);
        miEquipo.add(miPokemon3);

        //Prueba unitaria solo con rattata
        Pokemon enemigos[] = new Pokemon[1];
        Pokemon rattata = new Pokemon("rattata", 10, 10, 10, 100, 1000);

        enemigos[0] = rattata;
        //LineaEvolutiva.iniciarEntrenamientoMasivo(miPokemon, enemigos);

        //Prueba de el ejercito de catterpies
        Pokemon[] hordaCatterpie = new Pokemon[100000];

        for (int i = 0; i < hordaCatterpie.length; i++) {

            hordaCatterpie[i] = new Pokemon("catterpie", 45, 30, 35, 20, 50);

        }


        //Despues de comprobar que funcionaban los catterpies hice(con la ayuda de gemini) un aleatorizador de los pokemones en la horda, de todas formas sigue dando 50 de xp por combate entonces igual sigue evolucionando a los 30 y a los 100
        //Pokemon[] hordaSuprema = GeneradorHorda.generarHordaAleatoria(100000);

        LineaEvolutiva.iniciarEntrenamientoMasivo(miEquipo, hordaCatterpie);





    }
}


