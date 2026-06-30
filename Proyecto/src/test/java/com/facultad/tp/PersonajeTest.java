package com.facultad.tp;

import org.junit.jupiter.api.Test;

import com.facultad.tp.hechizos.Protego;

import estados.*;

import static org.junit.jupiter.api.Assertions.*;

public class PersonajeTest {

    @Test
    public void testCreacionAuror() {
        Auror auror = new Auror("Harry", 85, 150,100);
        assertEquals("Harry", auror.getNombre());
        assertEquals(85, auror.getNivelMagia());
        assertEquals(150, auror.getPuntosVida());
        assertEquals(150, auror.getPuntosVidaMaximos());
        assertTrue(auror.estaVivo());
        assertEquals(5, auror.getHechizos().size());
    }

    @Test
    public void testCreacionEstudiante() {
        Estudiante estudiante = new Estudiante("Ron", 45, 90,100);
        assertEquals("Ron", estudiante.getNombre());
        assertEquals(3, estudiante.getHechizos().size());
    }

    @Test
    public void testCreacionProfesor() {
        Profesor profesor = new Profesor("Dumbledore", 95, 180,100);
        assertEquals("Dumbledore", profesor.getNombre());
        assertEquals(4, profesor.getHechizos().size());
    }

    @Test
    public void testCreacionSeguidor() {
        Seguidor seguidor = new Seguidor("Mortifago1", 60, 110,100);
        assertEquals("Mortifago1", seguidor.getNombre());
        assertEquals(3, seguidor.getHechizos().size());
    }

    @Test
    public void testCreacionComandante() {
        Comandante comandante = new Comandante("Voldemort", 90, 200,100);
        assertEquals("Voldemort", comandante.getNombre());
        assertEquals(5, comandante.getHechizos().size());
    }

    @Test
    public void testRecibirDanio() {
        Auror auror = new Auror("Harry", 85, 150,100);
        auror.recibirDanio(50);
        assertEquals(100, auror.getPuntosVida());
        assertTrue(auror.estaVivo());
    }

    @Test
    public void testRecibirDanioMortal() {
        Auror auror = new Auror("Harry", 85, 150,100);
        auror.recibirDanio(150);
        assertEquals(0, auror.getPuntosVida());
        assertFalse(auror.estaVivo());
    }

    @Test
    public void testRecibirDanioConProtego() {
        Auror auror = new Auror("Harry", 85, 150,100);
        Protego prot = new Protego();
        prot.ejecutar(auror, auror);
        auror.recibirDanio(20);
        
        
        assertEquals(150, auror.getPuntosVida(),"El mago no deberia recibir daño");
    }

    @Test
    public void testCuracion() {
        Auror auror = new Auror("Harry", 85, 150,100);
        auror.recibirDanio(80);
        assertEquals(70, auror.getPuntosVida());
        auror.curar(40);
        assertEquals(110, auror.getPuntosVida());
    }

    @Test
    public void testCuracionNoExcedeMaximo() {
        Auror auror = new Auror("Harry", 85, 150,100);
        auror.curar(50);
        assertEquals(150, auror.getPuntosVida());
    }

    @Test
    public void testMagoCalculaDanioReducido() {
        Mago mago = new Auror("Harry", 100, 150,100);
        int danio = mago.calcularDanio(null, 100);
        assertTrue(danio <= 100, "El mago deberia reducir el danio base");
    }

    @Test
    public void testMortifagoCalculaDanioAumentado() {
        Mortifago mortifago = new Comandante("Voldemort", 100, 200,100);
        int danio = mortifago.calcularDanio(null, 100);
        assertTrue(danio > 100, "El mortifago deberia aumentar el danio base");
    }

    @Test
    public void testMagoCalculaCuracionAumentada() {
        Mago mago = new Auror("Harry", 100, 150,100);
        int curacion = mago.calcularCuracion(null, 100);
        assertTrue(curacion > 100, "El mago deberia aumentar la curacion base");
    }

    @Test
    public void testPolimorfismoDanio() {
        Personaje mago = new Auror("Harry", 50, 150,100);
        Personaje mortifago = new Seguidor("Mortifago", 50, 150,100);

        int danioMago = mago.calcularDanio(null, 100);
        int danioMortifago = mortifago.calcularDanio(null, 100);

        assertTrue(danioMortifago > danioMago,
            "Un mortifago con el mismo nivel de magia deberia hacer mas danio que un mago");
    }
}
