package com.facultad.tp;

import java.util.Random;

public class Reclutador {

    private static Random rand = new Random();
    private static int contadorMagos = 0;
    private static int contadorMortifagos = 0;
    
    public static Mago crearMago() {
    	contadorMagos++;
        return switch (rand.nextInt(3)) {
<<<<<<< Updated upstream
            case 0 -> new Auror("Auror " + contador, 70 + rand.nextInt(31), 120 + rand.nextInt(81),100);
            case 1 -> new Estudiante("Estudiante " + contador, 40 + rand.nextInt(31), 80 + rand.nextInt(41),100);
            case 2 -> new Profesor("Profesor " + contador, 80 + rand.nextInt(21), 130 + rand.nextInt(71),100);
            default -> new Auror("Auror " + contador, 70 + rand.nextInt(31), 120 + rand.nextInt(81),100);
=======
            case 0 -> new Auror("Auror " + contadorMagos, 120 + rand.nextInt(31), 120 + rand.nextInt(81),150);
            case 1 -> new Estudiante("Estudiante " + contadorMagos, 40 + rand.nextInt(31), 80 + rand.nextInt(41),100);
            case 2 -> new Profesor("Profesor " + contadorMagos, 80 + rand.nextInt(21), 130 + rand.nextInt(71),125);
            default -> new Auror("Auror " + contadorMagos, 120 + rand.nextInt(31), 120 + rand.nextInt(81),150);
>>>>>>> Stashed changes
        };
    }

    public static Mortifago crearMortifago() {
    	contadorMortifagos++;
        return switch (rand.nextInt(2)) {
<<<<<<< Updated upstream
            case 0 -> new Seguidor("Seguidor " + contador, 50 + rand.nextInt(31), 90 + rand.nextInt(51),100);
            case 1 -> new Comandante("Comandante " + contador, 75 + rand.nextInt(26), 130 + rand.nextInt(71),100);
            default -> new Seguidor("Seguidor " + contador, 50 + rand.nextInt(31), 90 + rand.nextInt(51),100);
=======
            case 0 -> new Seguidor("Seguidor " + contadorMortifagos, 50 + rand.nextInt(31), 30 + rand.nextInt(51),110);
            case 1 -> new Comandante("Comandante " + contadorMortifagos, 75 + rand.nextInt(26), 130 + rand.nextInt(71),160);
            default -> new Seguidor("Seguidor " + contadorMortifagos, 50 + rand.nextInt(31), 90 + rand.nextInt(51),110);
>>>>>>> Stashed changes
        };
    }

}
