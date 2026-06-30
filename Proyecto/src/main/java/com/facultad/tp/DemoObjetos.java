package com.facultad.tp.batallas;

import com.facultad.tp.*;
import com.facultad.tp.hechizos.*;
import com.facultad.tp.objetos.*;

public class DemoObjetos {

    public static void main(String[] args) {
        System.out.println("=== DEMO CONTROLADA: OBJETOS MAGICOS ===");

        Auror auror = new Auror("Harry Potter", 80, 180, 100);
        Seguidor seguidor = new Seguidor("Mortifago", 60, 120, 100);

        Hechizo expelliarmus = new Expelliarmus();
        Hechizo sectumsempra = new Sectumsempra();
        Hechizo protego = new Protego();

        VaritaMagica varita = new VaritaMagica();
        CapaInvisibilidad capa = new CapaInvisibilidad();
        AmuletoMana amuleto = new AmuletoMana();

        // --- Parte 1: Varita Magica ---
        System.out.println("\n--- PARTE 1: VARITA MAGICA ---");
        auror.agregarObjeto(varita);

        System.out.println("Harry ataca SIN varita:");
        auror.lanzarHechizo(expelliarmus, seguidor);
        int vidaTrasPrimerGolpe = seguidor.getPuntosVida();

        auror.equipar(varita);
        System.out.println("Harry ataca CON varita (+30% danio de ataque):");
        auror.lanzarHechizo(expelliarmus, seguidor);

        int danioSin = vidaTrasPrimerGolpe - seguidor.getPuntosVida();
        System.out.println("  -> Diferencia: ataque previo hizo " + (120 - vidaTrasPrimerGolpe) +
            ", ataque con varita hizo " + danioSin);

        // --- Parte 2: Capa de Invisibilidad ---
        System.out.println("\n--- PARTE 2: CAPA DE INVISIBILIDAD ---");
        auror.desequipar(varita);
        auror.agregarObjeto(capa);
        auror.equipar(capa);

        System.out.println("Mortifago ataca a Harry (con capa activa):");
        seguidor.lanzarHechizo(sectumsempra, auror);
        System.out.println("Mortifago ataca de nuevo (capa ya consumida):");
        seguidor.lanzarHechizo(sectumsempra, auror);

        // --- Parte 3: Amuleto de Mana ---
        System.out.println("\n--- PARTE 3: AMULETO DE MANA ---");
        Auror hermione = new Auror("Hermione Granger", 90, 160, 50);
        hermione.agregarObjeto(amuleto);
        hermione.equipar(amuleto);

        System.out.println("Mana actual de Hermione: " + hermione.getManaActual());
        hermione.inicioDeTurno();
        System.out.println("Mana tras inicio de turno (con amuleto): " + hermione.getManaActual());

        // --- Parte 4: Intercambio de objetos en batalla real ---
        System.out.println("\n--- PARTE 4: BATALLA CON OBJETOS (3v3) ---");
        Batallon magos = new Batallon();
        Batallon mortifagos = new Batallon();

        for (int i = 0; i < 3; i++) {
            magos.agregarPersonaje(Reclutador.crearMago());
            mortifagos.agregarPersonaje(Reclutador.crearMortifago());
        }

        int ronda = 0;
        while (magos.tienePersonajesSaludables() && mortifagos.tienePersonajesSaludables() && ronda < 10) {
            ronda++;
            System.out.println("\n--- Ronda " + ronda + " ---");
            magos.atacar(mortifagos);
            if (mortifagos.tienePersonajesSaludables()) {
                mortifagos.atacar(magos);
            }
            System.out.println("Estado:");
            magos.mostrarEstado();
            mortifagos.mostrarEstado();
        }

        System.out.println("\n=== FIN DE LA DEMO DE OBJETOS ===");
    }
}
