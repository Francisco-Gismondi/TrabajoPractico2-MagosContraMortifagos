package com.facultad.tp;

import com.facultad.tp.hechizos.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import estados.*;

public class HechizoTest {

    @Test
    public void testExpelliarmusEsAtaque() {
        Expelliarmus exp = new Expelliarmus();
        assertTrue(exp.esAtaque());
        assertFalse(exp.esDefensa());
        assertFalse(exp.esCuracion());
        assertEquals("Expelliarmus", exp.getNombre());
    }

    @Test
    public void testAvadaKedavraEsAtaque() {
        AvadaKedavra ak = new AvadaKedavra();
        assertTrue(ak.esAtaque());
        assertFalse(ak.esDefensa());
        assertFalse(ak.esCuracion());
        assertEquals("Avada Kedavra", ak.getNombre());
    }

    @Test
    public void testProtegoEsDefensa() {
        Protego prot = new Protego();
        assertFalse(prot.esAtaque());
        assertTrue(prot.esDefensa());
        assertFalse(prot.esCuracion());
        assertEquals("Protego", prot.getNombre());
    }

    @Test
    public void testExpectoPatronumEsCuracion() {
        ExpectoPatronum ep = new ExpectoPatronum();
        assertFalse(ep.esAtaque());
        assertFalse(ep.esDefensa());
        assertTrue(ep.esCuracion());
        assertEquals("Expecto Patronum", ep.getNombre());
    }

    @Test
    public void testExpelliarmusCausaDanio() {
        Auror auror = new Auror("Harry", 50, 150,100);
        Seguidor seguidor = new Seguidor("Mortifago", 50, 100,100);
        Expelliarmus exp = new Expelliarmus();

        exp.ejecutar(auror, seguidor);
        assertTrue(seguidor.getPuntosVida() < 100);
        assertTrue(seguidor.getPuntosVida() >= 0);
    }

    @Test
    public void testAvadaKedavraAltoDanio() {
        Auror auror = new Auror("Harry", 50, 150,100);
        Seguidor seguidor = new Seguidor("Mortifago", 50, 100,100);
        AvadaKedavra ak = new AvadaKedavra();

        ak.ejecutar(seguidor, auror);
        assertTrue(auror.getPuntosVida() < 150);
    }

    @Test
    public void testAvadaKedavraMortifagoMasLetal() {
        Auror auror = new Auror("Harry", 50, 150,100);
        Seguidor seguidor = new Seguidor("Mortifago", 80, 100,100);
        Expelliarmus exp = new Expelliarmus();

        exp.ejecutar(seguidor, auror);
        int danioMortifago = 150 - auror.getPuntosVida();

        auror = new Auror("Harry", 50, 150,100);
        Mago mago = new Auror("Otro Harry", 80, 150,100);
        exp.ejecutar(mago, auror);
        int danioMago = 150 - auror.getPuntosVida();

        assertTrue(danioMortifago > danioMago,
            "Un mortifago con mayor nivel de magia deberia causar mas danio con el mismo hechizo");
    }

    @Test
    public void testProtegoActivaProteccion() {
        Auror auror = new Auror("Harry", 50, 150, 300);
        Protego prot = new Protego();

        assertFalse(auror.getEstadoActual() instanceof EstadoEscudo, 
            "El personaje no debería arrancar con un escudo activo");
        prot.ejecutar(auror, auror);
        assertTrue(auror.getEstadoActual() instanceof EstadoEscudo, 
            "Protego debería haberle asignado un EstadoEscudo al personaje");
    }

    @Test
    public void testProtegoReduceDanio() {
        Auror auror = new Auror("Harry", 50, 150, 300);
        Seguidor seguidor = new Seguidor("Mortifago", 50, 100, 300);
        
        Expelliarmus exp = new Expelliarmus();
        Protego pro = new Protego();

        pro.ejecutar(auror, seguidor);
        int vidaAntes = auror.getPuntosVida();
        exp.ejecutar(seguidor, auror);
        int vidaDespues = auror.getPuntosVida();
        int danioReal = vidaAntes - vidaDespues;

        assertTrue(danioReal < 50, "Protego deberia reducir el danio a la mitad");
        assertFalse(danioReal > 50, "La proteccion deberia consumirse al recibir danio");
    }

    @Test
    public void testExpectoPatronumCura() { //recibe daño y despues se cura con expetopatronum
        Auror auror = new Auror("Harry", 50, 150, 300);
        auror.recibirDanio(80);
        assertEquals(70, auror.getPuntosVida());

        ExpectoPatronum ep = new ExpectoPatronum();
        ep.ejecutar(auror, auror);
        
        assertTrue(auror.getPuntosVida() > 70);
        assertTrue(auror.getPuntosVida() <= 150);
    }

    @Test
    public void testFabricaHechizos() {
        Hechizo exp = FabricaHechizos.crearHechizo("expelliarmus");
        assertTrue(exp instanceof Expelliarmus);

        Hechizo ak = FabricaHechizos.crearHechizo("avadakedavra");
        assertTrue(ak instanceof AvadaKedavra);

        Hechizo prot = FabricaHechizos.crearHechizo("protego");
        assertTrue(prot instanceof Protego);

        Hechizo ep = FabricaHechizos.crearHechizo("expectopatronum");
        assertTrue(ep instanceof ExpectoPatronum);
    }

    @Test
    public void testEqualsHechizos() {
        Expelliarmus e1 = new Expelliarmus();
        Expelliarmus e2 = new Expelliarmus();
        assertEquals(e1, e2);

        assertNotEquals(e1, new Protego());
    }
}
