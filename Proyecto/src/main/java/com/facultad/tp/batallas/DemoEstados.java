package com.facultad.tp.batallas;

import java.util.*;

import com.facultad.tp.*;
import com.facultad.tp.hechizos.*;

public class DemoEstados {
	public static void main(String[] args) {
        System.out.println("=== DEMO CONTROLADA: EVASION, VENENO Y PURIFICACION ===");

        // Instanciamos con las listas exactas
        Auror defensor = new Auror("Kingsley Shacklebolt", 80, 150, 100);
        Profesor oscuro = new Profesor("Severus Snape", 85, 150, 100);

        Hechizo evasion = new CaveInimicum();
        Hechizo veneno = new Sectumsempra();
        Hechizo purificacion = new FiniteIncantatem();

        System.out.println("\n--- INICIA EL COMBATE ---");

        // 1. El Auror se vuelve invisible
        defensor.lanzarHechizo(evasion, defensor);

        // 2. El Profesor ataca, pero falla porque el Auror es invisible. (Rompe la evasión)
        oscuro.lanzarHechizo(veneno, defensor);
        
        // 3. El Profesor ataca de nuevo. Como el Auror ya es visible, lo envenena.
        System.out.println("\n> El Profesor ataca de nuevo...");
        oscuro.lanzarHechizo(veneno, defensor);

        // 4. Simulamos el inicio del turno del Auror (Sufre daño por veneno)
        System.out.println("\n> Comienza el turno del Auror...");
        defensor.inicioDeTurno(); 

        // 5. El Auror usa Finite Incantatem y vuelve a Estado Normal
        defensor.lanzarHechizo(purificacion, defensor);

        System.out.println("\n=== FIN DE LA DEMO ===");
    }
}
