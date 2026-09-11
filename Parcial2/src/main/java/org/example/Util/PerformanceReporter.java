package org.example.Util;

// Lombok: crea automáticamente el logger 'log' para registrar mensajes.
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
// JOL: mide el tamaño real del objeto en memoria, incluyendo campos y layout de la JVM.
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;
// OSHI: obtiene métricas del sistema operativo y hardware, como memoria física disponible.
import oshi.SystemInfo;

import java.util.Locale;

@Log4j2(topic = "performance")
public class PerformanceReporter {

    private static final Logger loggerTiempos = LogManager.getLogger("tiempos");

    // Permite consultar la memoria del sistema operativo (RAM real).
    private static final SystemInfo si = new SystemInfo();
    // Convierte bytes a megabytes para mostrar valores legibles.
    private static final long BYTES_PER_MB = 1024L * 1024L;

    // Mide el tamaño del objeto y su árbol de referencias para entender el consumo de memoria.
    public static void medirPesoObjeto(Object objeto, String nombreObjeto) {

        // instanceSize(): tamaño superficial del objeto sin contar referencias anidadas.
        long pesoBytes = ClassLayout.parseInstance(objeto).instanceSize();
        // totalSize(): incluye el objeto y todos los objetos referenciados por él.
        long pesoTotal = GraphLayout.parseInstance(objeto).totalSize();

        log.info("Objeto '{}' | tamaño superficial: {} bytes | con referencias: {} bytes",
                nombreObjeto, pesoBytes, pesoTotal);

    }

    // Consulta la memoria consumida por la JVM y la RAM física disponible del equipo.
    public static void reportarMemoriaSistema() {
        Runtime runtime = Runtime.getRuntime();

        // Memoria del heap de Java: asignada y usada por la JVM.
        long totalHeap = runtime.totalMemory();
        long usedHeap = totalHeap - runtime.freeMemory();
        long maxHeap = runtime.maxMemory();
        double porcentajeUso = totalHeap == 0 ? 0 : (100.0 * usedHeap) / totalHeap;

        // OSHI: obtiene la memoria total y disponible del sistema.
        long totalFisica = si.getHardware().getMemory().getTotal();
        long disponibleFisica = si.getHardware().getMemory().getAvailable();

        log.info("Memoria JVM | usada: {} | asignada: {} | máxima: {} | uso: {}%",
                formatearMegabytes(usedHeap), formatearMegabytes(totalHeap),
                formatearMegabytes(maxHeap), formatearDecimal(porcentajeUso));

        log.info("Memoria física | usada: {} | total: {} | disponible: {}",
                formatearMegabytes(totalFisica - disponibleFisica),
                formatearMegabytes(totalFisica), formatearMegabytes(disponibleFisica));

    }

    public static long memoriaDisponibleSistema() {
        return si.getHardware().getMemory().getAvailable();
    }

    public static long heapUsado() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

    public static void registrarResultadosEntrenamiento(int cantidadBatallas,
                                                        double tiempoSegundos,
                                                        long memoriaInicial,
                                                        long memoriaFinal,
                                                        long heapInicial,
                                                        long heapFinal) {
        loggerTiempos.info("Entrenamiento masivo | batallas: {} | tiempo: {} segundos",
                cantidadBatallas, formatearDecimal(tiempoSegundos));
        log.info("RAM disponible | antes: {} | después: {} | diferencia: {}",
                formatearMegabytes(memoriaInicial),
                formatearMegabytes(memoriaFinal),
                formatearMegabytes(memoriaFinal - memoriaInicial));
        log.info("Heap JVM usado | antes: {} | después: {} | diferencia: {}",
                formatearMegabytes(heapInicial),
                formatearMegabytes(heapFinal),
                formatearMegabytes(heapFinal - heapInicial));
    }

    // Convierte bytes a MB para imprimir valores de memoria en formato legible.
    private static String formatearMegabytes(long bytes) {
        return formatearDecimal((double) bytes / BYTES_PER_MB) + " MB";
    }

    // Locale.ROOT evita problemas de formato con comas/puntos según el idioma del sistema.
    private static String formatearDecimal(double valor) {
        return String.format(Locale.ROOT, "%.2f", valor);
    }

}