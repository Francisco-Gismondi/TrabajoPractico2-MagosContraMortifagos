package com.facultad.tp;

import com.facultad.tp.hechizos.*;

public class FabricaHechizos {

    public static Hechizo crearHechizo(String tipo) {
        return switch (tipo.toLowerCase()) {
        case "avadakedavra" -> new AvadaKedavra();
        case "caveinimicum" -> new CaveInimicum();
        case "crucio" -> new Crucio();
        case "desmaius" -> new Desmaius();
        case "expectropatronum" -> new ExpectoPatronum();
        case "expelliarmus" -> new Expelliarmus();
        case "finiteincantetem" -> new FiniteIncantatem();
        case "imperio" -> new Imperio();
        case "protego" -> new Protego();
        case "sectumsempra" -> new Sectumsempra();
        case "vulnerasanentur" -> new VulneraSanentur();
        default -> throw new IllegalArgumentException("Hechizo desconocido: " + tipo);
        };
    }

    public static Hechizo crearHechizoAleatorio() {
        java.util.Random rand = new java.util.Random();
        return switch (rand.nextInt(12)) {
        case 1 -> new AvadaKedavra();
        case 2 -> new CaveInimicum();
        case 3 -> new Crucio();
        case 4 -> new Desmaius();
        case 5 -> new ExpectoPatronum();
        case 6 -> new Expelliarmus();
        case 7 -> new FiniteIncantatem();
        case 8 -> new Imperio();
        case 9 -> new Protego();
        case 10 -> new Sectumsempra();
        case 11 -> new VulneraSanentur();
        default -> new HechizoAtaqueBasico("Flipendo", 10);
        };
    }
}