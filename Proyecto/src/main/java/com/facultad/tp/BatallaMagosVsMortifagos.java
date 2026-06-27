package com.facultad.tp;
/* comentario de prueba para github de marcelo*/
import java.util.Random;

public class BatallaMagosVsMortifagos {

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("   BATALLA: MAGOS vs MORTIFAGOS");
        System.out.println("==========================================");

        Batallon batallonMagos = new Batallon();
        Batallon batallonMortifagos = new Batallon();

        for (int i = 0; i < 3; i++) {
            batallonMagos.agregarPersonaje(Reclutador.crearMago());
            batallonMortifagos.agregarPersonaje(Reclutador.crearMortifago());
        }

        System.out.println("\n--- BATALLONES INICIALES ---");
        System.out.println("Magos:");
        batallonMagos.mostrarEstado();
        System.out.println("Mortifagos:");
        batallonMortifagos.mostrarEstado();

        Random rand = new Random();
        int ronda = 0;

        while (batallonMagos.tienePersonajesSaludables() && batallonMortifagos.tienePersonajesSaludables()) {
            ronda++;
            System.out.println("\n========== RONDA " + ronda + " ==========");

            if (rand.nextBoolean()) {
                System.out.println("\n-- Turno de los Magos --");
                batallonMagos.atacar(batallonMortifagos);
                if (batallonMortifagos.tienePersonajesSaludables()) {
                    System.out.println("\n-- Turno de los Mortifagos --");
                    batallonMortifagos.atacar(batallonMagos);
                }
            } else {
                System.out.println("\n-- Turno de los Mortifagos --");
                batallonMortifagos.atacar(batallonMagos);
                if (batallonMagos.tienePersonajesSaludables()) {
                    System.out.println("\n-- Turno de los Magos --");
                    batallonMagos.atacar(batallonMortifagos);
                }
            }

            System.out.println("\n--- Estado actual ---");
            System.out.println("Magos (" + batallonMagos.tamaño() + "):");
            batallonMagos.mostrarEstado();
            System.out.println("Mortifagos (" + batallonMortifagos.tamaño() + "):");
            batallonMortifagos.mostrarEstado();
            System.out.println("----------------------------");

            if (ronda >= 50) {
                System.out.println("\nLa batalla ha llegado a su limite de rondas.");
                break;
            }
        }

        System.out.println("\n==========================================");
        System.out.println("   RESULTADO FINAL");
        System.out.println("==========================================");
        if (batallonMagos.tienePersonajesSaludables()) {
            System.out.println("   \u00a1Los magos han ganado la batalla!");
        } else if (batallonMortifagos.tienePersonajesSaludables()) {
            System.out.println("   \u00a1Los mortifagos han ganado la batalla!");
        } else {
            System.out.println("   \u00a1Empate! Todos han caido.");
        }
        System.out.println("==========================================");
    }
}
