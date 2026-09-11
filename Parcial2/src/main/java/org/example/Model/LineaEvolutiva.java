package org.example.Model;

import org.example.Util.PerformanceReporter;
import org.example.Util.ReporteBatalla;

import java.lang.classfile.instruction.SwitchCase;
import java.util.LinkedList;
import java.util.Queue;

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

    public static void iniciarEntrenamientoMasivo(Queue<LineaEvolutiva> miEquipo, Pokemon[] hordaEnemigos){

        long memoriaDisponibleInicial = PerformanceReporter.memoriaDisponibleSistema();
        long heapUsadoInicial = PerformanceReporter.heapUsado();
        PerformanceReporter.medirPesoObjeto(miEquipo.element(), "LineaEvolutiva");
        PerformanceReporter.medirPesoObjeto(miEquipo.element().getFaseActual(), "Pokemon");
        long inicioTiempoProceso = System.nanoTime();

        int victorias = 0; //Profe se que estoy rompiendo las reglas porque mi pokemon siempre ataca primero pero esto tiene que hacerse
        int K = 0;
        int cantidadDerrotados = 1;

        LinkedList<ReporteBatalla> CajaNegra = new LinkedList<>();


        for (Pokemon enemigo : hordaEnemigos){
            if(K == 50) {
                miEquipo.add(miEquipo.element());
                miEquipo.poll();
                K = 0;
            }

            Pokemon jugador = miEquipo.element().getFaseActual();
            int hpJugador = jugador.getPuntosDeVidaMaximos();
            int hpEnemigo = enemigo.getPuntosDeVidaMaximos();


            while (hpJugador > 0 && hpEnemigo > 0){

                if(jugador.getVelocidad() > enemigo.getVelocidad()) {


                    int ataqueJugador = Math.max(1, jugador.getAtaque() - enemigo.getDefensa());
                    hpEnemigo -= ataqueJugador;
                    System.out.println(enemigo.getNombre() + " ha perdido " + ataqueJugador + " puntos de vida, le quedan: " + hpEnemigo);

                    if (hpEnemigo <= 0) {
                        break;
                    }

                    int ataqueEnemigo = Math.max(1, enemigo.getAtaque() - jugador.getDefensa());
                    hpJugador -= ataqueEnemigo;
                    System.out.println(jugador.getNombre() + " ha perdido " + ataqueEnemigo + " puntos de vida, le quedan: " + hpJugador);

                    if (hpJugador <= 0) {
                        break;
                    }
                }else {
                    int ataqueEnemigo = Math.max(1, enemigo.getAtaque() - jugador.getDefensa());
                    hpJugador -= ataqueEnemigo;
                    System.out.println(jugador.getNombre() + " ha perdido " + ataqueEnemigo + " puntos de vida, le quedan: " + hpJugador);

                    if (hpJugador <= 0) {
                        break;
                    }

                    int ataqueJugador = Math.max(1, jugador.getAtaque() - enemigo.getDefensa());
                    hpEnemigo -= ataqueJugador;
                    System.out.println(enemigo.getNombre() + " ha perdido " + ataqueJugador + " puntos de vida, le quedan: " + hpEnemigo);


                    if (hpEnemigo <= 0) {
                        break;
                    }
                }
            }

            if(hpJugador > 0) {
                victorias++;
                K++;
                ReporteBatalla r = new ReporteBatalla(jugador.getNombre(), enemigo.getNombre(), cantidadDerrotados);
                    if (CajaNegra.size() == 0) {
                        CajaNegra.add(r);
                    }
                    else if(CajaNegra.peekFirst().equals(r)){
                        CajaNegra.peekFirst().setDerrotados(1);
                    }
                    else{
                        CajaNegra.add(r);
                    }

                    if(CajaNegra.size() > 10){
                        CajaNegra.removeLast();
                    }


                System.out.println(jugador.getNombre() + " ha derrotado a " + enemigo.getNombre() + "!");
                miEquipo.element().ganarExp(50);
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

        System.out.println("Entrenamiento completado\n Wins: " + victorias + "/" + hordaEnemigos.length + "\n Bien hecho equipo!");


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
