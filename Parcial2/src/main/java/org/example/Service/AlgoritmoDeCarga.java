package org.example.Service;

import org.example.Util.PerformanceReporter;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Log4j2
public class AlgoritmoDeCarga {

    private static final Logger loggerTiempos = LogManager.getLogger( "tiempos" );

    public static void multiplicacionMatrices(int cantidadEspacio, String caso) {

        double[][] A = new double[cantidadEspacio][cantidadEspacio];
        double[][] B = new double[cantidadEspacio][cantidadEspacio];
        double[][] C = new double[cantidadEspacio][cantidadEspacio];

        for (int i = 0; i < cantidadEspacio; i++) {
            for (int j = 0; j < cantidadEspacio; j++) {
                A[i][j] = Math.random();
                B[i][j] = Math.random();
            }
        }

        log.info("Iniciando multiplicación de matrices | caso: {} | tamaño: {}x{}", caso, cantidadEspacio, cantidadEspacio);

        PerformanceReporter.medirPesoObjeto(A, "Matriz A [" + cantidadEspacio + "][" + cantidadEspacio + "]");
        PerformanceReporter.reportarMemoriaSistema();

        long inicioTiempoProceso = System.nanoTime();

        for (int i = 0; i < cantidadEspacio; i++) {
            for (int j = 0; j < cantidadEspacio; j++) {
                for (int k = 0; k < cantidadEspacio; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        double tiempoTotal = (System.nanoTime() - inicioTiempoProceso) / 1_000_000_000.0;

        PerformanceReporter.medirPesoObjeto(C, "Matriz C [" + cantidadEspacio + "][" + cantidadEspacio + "]");
        PerformanceReporter.reportarMemoriaSistema();

        loggerTiempos.info("Caso: {} | tamaño: {}x{} | tiempo: {} segundos",
                caso, cantidadEspacio, cantidadEspacio,
                String.format(java.util.Locale.ROOT, "%.4f", tiempoTotal));

        log.info("Finalizó la multiplicación | caso: {} | resultado: {}",
                caso, C[cantidadEspacio / 2][cantidadEspacio / 2]);

    }

}