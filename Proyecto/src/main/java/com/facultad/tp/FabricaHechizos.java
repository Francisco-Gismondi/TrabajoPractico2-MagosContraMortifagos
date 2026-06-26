package com.facultad.tp;

import com.facultad.tp.hechizos.*;

public class FabricaHechizos {

    public static Hechizo crearHechizo(String tipo) {
        return switch (tipo.toLowerCase()) {
            case "expelliarmus" -> new Expelliarmus();
            case "avadakedavra" -> new AvadaKedavra();
            case "protego" -> new Protego();
            case "expectopatronum" -> new ExpectoPatronum();
            default -> throw new IllegalArgumentException("Hechizo desconocido: " + tipo);
        };
    }

    public static Hechizo crearHechizoAleatorio() {
        java.util.Random rand = new java.util.Random();
        return switch (rand.nextInt(4)) {
            case 0 -> new Expelliarmus();
            case 1 -> new AvadaKedavra();
            case 2 -> new Protego();
            case 3 -> new ExpectoPatronum();
            default -> new Expelliarmus();
        };
    }
}
