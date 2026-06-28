package com.facultad.tp;

import java.util.Random;

public class Reclutador {

    private static int contador = 0;
    private static Random rand = new Random();

    public static Mago crearMago() {
        contador++;
        return switch (rand.nextInt(3)) {
            case 0 -> new Auror("Auror " + contador, 120 + rand.nextInt(31), 120 + rand.nextInt(81),100);
            case 1 -> new Estudiante("Estudiante " + contador, 40 + rand.nextInt(31), 80 + rand.nextInt(41),100);
            case 2 -> new Profesor("Profesor " + contador, 80 + rand.nextInt(21), 130 + rand.nextInt(71),100);
            default -> new Auror("Auror " + contador, 120 + rand.nextInt(31), 120 + rand.nextInt(81),100);
        };
    }

    public static Mortifago crearMortifago() {
        contador++;
        return switch (rand.nextInt(2)) {
            case 0 -> new Seguidor("Seguidor " + contador, 50 + rand.nextInt(31), 30 + rand.nextInt(51),100);
            case 1 -> new Comandante("Comandante " + contador, 75 + rand.nextInt(26), 130 + rand.nextInt(71),100);
            default -> new Seguidor("Seguidor " + contador, 50 + rand.nextInt(31), 90 + rand.nextInt(51),100);
        };
    }

    public static void resetearContador() {
        contador = 0;
    }
}
