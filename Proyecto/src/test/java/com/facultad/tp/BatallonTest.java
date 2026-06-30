package com.facultad.tp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BatallonTest {

    @Test
    public void testBatallonVacioNoTienePersonajesSaludables() {
        Batallon b = new Batallon();
        assertFalse(b.tienePersonajesSaludables());
    }

    @Test
    public void testAgregarPersonaje() {
        Batallon b = new Batallon();
        b.agregarPersonaje(new Auror("Harry", 85, 150, 200));
        assertTrue(b.tienePersonajesSaludables());
        assertEquals(1, b.tamaño());
    }

    @Test
    public void testTienePersonajesSaludablesConVivos() {
        Batallon b = new Batallon();
        Auror auror = new Auror("Harry", 85, 150, 300);
        b.agregarPersonaje(auror);
        assertTrue(b.tienePersonajesSaludables());
    }

    @Test
    public void testBatallonAtacarReduceVida() {
        Batallon magos = new Batallon();
        Batallon mortifagos = new Batallon();

        Auror auror = new Auror("Harry", 90, 200,300);
        Seguidor seguidor = new Seguidor("Mortifago", 50, 99999,200); //pongo un objetivo con mucha vida

        magos.agregarPersonaje(auror);
        mortifagos.agregarPersonaje(seguidor);

        int vidaInicial = seguidor.getPuntosVida();
        for (int i = 0; i < 10; i++) { //estadisticamente es casi imposible que el mago lanze protego 10 veces seguidas
            System.out.println("Turno " + (i+1));
        	magos.atacar(mortifagos); 
        }
        int vidaFinal = seguidor.getPuntosVida();

        assertTrue(vidaFinal < vidaInicial, "El ataque deberia reducir la vida");
    }

    @Test
    public void testPersonajeEliminadoAlMorir() {
        Batallon magos = new Batallon();
        Batallon mortifagos = new Batallon();

        Auror auror = new Auror("Harry", 50, 20, 300);
        Comandante voldemort = new Comandante("Voldemort", 95, 500, 300);

        magos.agregarPersonaje(auror);
        mortifagos.agregarPersonaje(voldemort);

        int rondas = 0;
        while (magos.tienePersonajesSaludables()) {
            mortifagos.atacar(magos); //el mortifago ataca hasta que el batallon de magos quede vacia
            rondas++;
        }

        assertFalse(magos.tienePersonajesSaludables(),
            "Harry deberia haber muerto tras " + rondas + " rondas");
    }

    @Test
    public void testBatallonMultipleAtaques() {
        Batallon magos = new Batallon();
        Batallon mortifagos = new Batallon();

        for (int i = 0; i < 3; i++) {
            magos.agregarPersonaje(new Auror("Auror " + i, 70, 150, 100));
            mortifagos.agregarPersonaje(new Seguidor("Seguidor " + i, 60, 120, 100));
        }

        magos.atacar(mortifagos);
        assertTrue(mortifagos.tienePersonajesSaludables() || mortifagos.tamaño() < 3,
            "Despues del ataque, los mortifagos pueden tener bajas o seguir todos vivos");
    }

    @Test
    public void testGetPersonajesVivos() {
        Batallon b = new Batallon();
        Auror a1 = new Auror("Harry", 85, 150, 200);
        Auror a2 = new Auror("Ron", 50, 10, 230); //creo un auror con pica vida para matarlo y reducir el batallon

        b.agregarPersonaje(a1);
        b.agregarPersonaje(a2);

        a2.recibirDanio(10);

        assertEquals(1, b.getPersonajesVivos().size());
    }

    @Test
    public void testBatallaCompletaGanaUnBando() {
        Batallon magos = new Batallon();
        Batallon mortifagos = new Batallon();

        magos.agregarPersonaje(new Profesor("Dumbledore", 99, 500, 300));
        mortifagos.agregarPersonaje(new Seguidor("Seguidor debil", 10, 30, 40));

        int rondas = 0;
        while (magos.tienePersonajesSaludables() && mortifagos.tienePersonajesSaludables() && rondas < 30) {
            magos.atacar(mortifagos);
            if (mortifagos.tienePersonajesSaludables()) {
                mortifagos.atacar(magos);
            }
            rondas++;
        }

        assertTrue(magos.tienePersonajesSaludables(),
            "Dumbledore deberia ganar contra un seguidor debil, rondas: " + rondas);
        assertFalse(mortifagos.tienePersonajesSaludables());
    }

    @Test
    public void testMapHechizosPorPersonaje() {
        Batallon b = new Batallon();
        Auror auror = new Auror("Harry", 85, 150, 120);
        b.agregarPersonaje(auror);

        assertTrue(b.getPersonajesVivos().contains(auror));
    }

    @Test
    public void testReclutadorCreaMagos() {
        Mago mago = Reclutador.crearMago();
        assertNotNull(mago);
        assertTrue(mago instanceof Mago);
        assertTrue(mago.estaVivo());
        assertTrue(mago.getPuntosVida() > 0);
        assertTrue(mago.getNivelMagia() > 0);
    }

    @Test
    public void testReclutadorCreaMortifagos() {
        Mortifago mortifago = Reclutador.crearMortifago();
        assertNotNull(mortifago);
        assertTrue(mortifago instanceof Mortifago);
        assertTrue(mortifago.estaVivo());
    }
}
