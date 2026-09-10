package org.example.Model;

import org.example.Util.PerformanceReporter;

public class LineaEvolutiva {

    private Pokemon faseActual;
    private int experienciaAcumulada;

    public LineaEvolutiva(Pokemon faseInicial) {
        this.faseActual = faseInicial;
        this.experienciaAcumulada = 0;
    }

    public Pokemon getFaseActual() {
        return faseActual;
    }

    public int getExperienciaAcumulada() {
        return experienciaAcumulada;
    }

    public void ganarExp(int cantidadExp){
        if (cantidadExp > 0){
            this.experienciaAcumulada += cantidadExp;
            System.out.println("Tu pokemon ha ganado " + cantidadExp + " de Exp");
            evaluarEvolucion();
        }
    }

    public boolean evaluarEvolucion() {
        boolean evoluciono = false;

        while (faseActual != null && faseActual.getSiguienteEvolucion() != null && faseActual.getExp() != -1 && getExperienciaAcumulada() >= faseActual.getExp()) {


                faseActual = faseActual.getSiguienteEvolucion();
                evoluciono = true;
                System.out.println("Tu pokemon ha evolucionado a " + faseActual.getNombre() + ", felicidades!");

        }
        return evoluciono;

    }

    public static void iniciarEntrenamientoMasivo(LineaEvolutiva myPokemon, Pokemon[] hordaEnemigos){

        long memoriaDisponibleInicial = PerformanceReporter.memoriaDisponibleSistema();
        long heapUsadoInicial = PerformanceReporter.heapUsado();
        PerformanceReporter.medirPesoObjeto(myPokemon, "LineaEvolutiva");
        PerformanceReporter.medirPesoObjeto(myPokemon.getFaseActual(), "Pokemon");
        long inicioTiempoProceso = System.nanoTime();
        int victorias = 0; //Profe se que estoy rompiendo las reglas porque mi pokemon siempre ataca primero pero esto tiene que hacerse

        for (Pokemon enemigo : hordaEnemigos){
            Pokemon jugador = myPokemon.getFaseActual();
            int hpJugador = jugador.getPuntosDeVidaMaximos();
            int hpEnemigo = enemigo.getPuntosDeVidaMaximos();


            while (hpJugador > 0 && hpEnemigo > 0){

                if(myPokemon.getFaseActual().getVelocidad() > enemigo.getVelocidad()) {


                    int ataqueJugador = Math.max(1, myPokemon.getFaseActual().getAtaque() - enemigo.getDefensa());
                    hpEnemigo -= ataqueJugador;
                    System.out.println(enemigo.getNombre() + " ha perdido " + ataqueJugador + " puntos de vida, le quedan: " + hpEnemigo);

                    if (hpEnemigo <= 0) {
                        break;
                    }

                    int ataqueEnemigo = Math.max(1, enemigo.getAtaque() - myPokemon.getFaseActual().getDefensa());
                    hpJugador -= ataqueEnemigo;
                    System.out.println(myPokemon.getFaseActual().getNombre() + " ha perdido " + ataqueEnemigo + " puntos de vida, le quedan: " + hpJugador);

                    if (hpJugador <= 0) {
                        break;
                    }
                }else {
                    int ataqueEnemigo = Math.max(1, enemigo.getAtaque() - myPokemon.getFaseActual().getDefensa());
                    hpJugador -= ataqueEnemigo;
                    System.out.println(myPokemon.getFaseActual().getNombre() + " ha perdido " + ataqueEnemigo + " puntos de vida, le quedan: " + hpJugador);

                    if (hpJugador <= 0) {
                        break;
                    }

                    int ataqueJugador = Math.max(1, myPokemon.getFaseActual().getAtaque() - enemigo.getDefensa());
                    hpEnemigo -= ataqueJugador;
                    System.out.println(enemigo.getNombre() + " ha perdido " + ataqueJugador + " puntos de vida, le quedan: " + hpEnemigo);


                    if (hpEnemigo <= 0) {
                        break;
                    }
                }
            }

            if(hpJugador > 0){
                victorias++;
                System.out.println(myPokemon.getFaseActual().getNombre() + " ha ganado derrotado a " + enemigo.getNombre() + "!");
                myPokemon.ganarExp(50);
            }
        }

        double tiempoTotalSegundos = (System.nanoTime() - inicioTiempoProceso) / 1_000_000_000.0;
        long memoriaDisponibleFinal = PerformanceReporter.memoriaDisponibleSistema();
        long heapUsadoFinal = PerformanceReporter.heapUsado();
        PerformanceReporter.registrarResultadosEntrenamiento(
            hordaEnemigos.length,
            tiempoTotalSegundos,
            memoriaDisponibleInicial,
            memoriaDisponibleFinal,
            heapUsadoInicial,
            heapUsadoFinal);

        System.out.println("Entrenamiento completado\n Wins: " + victorias + "/" + hordaEnemigos.length + "\n Bien hecho " + myPokemon.getFaseActual().getNombre() + "!");

    }

    public static void iniciarEntrentamientoMasivo(LineaEvolutiva myPokemon, Pokemon[] hordaEnemigos) {
        iniciarEntrenamientoMasivo(myPokemon, hordaEnemigos);
    }

    @Override
    public String toString(){

        String mensaje = "";

        if ( faseActual == null ) { return "Linea evolutiva vacia"; }

        Pokemon referencia = faseActual;

        while ( referencia != null ){

            mensaje += "[" + referencia.getNombre() + "," +  referencia.getPuntosDeVidaMaximos() + "," + referencia.getAtaque() + "," + referencia.getDefensa() + "," + referencia.getExp() + "] evoluciona a -> ";

            referencia = referencia.getSiguienteEvolucion();

        }

        return mensaje;

    }
}
