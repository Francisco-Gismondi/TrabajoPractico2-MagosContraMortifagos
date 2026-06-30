package com.facultad.tp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.facultad.tp.hechizos.HechizoAtaqueBasico;

import estados.*;

class EstadosTest {
	private Personaje victima;

    class PersonajePrueba extends Personaje {
        public PersonajePrueba(String nombre, int nivelMagia, int puntosVida,int mana) {
            super(nombre, nivelMagia, puntosVida,mana);
        }
        @Override
        public boolean puedeLanzarMagiaOscura() {
            return false;
        }
    }

    @BeforeEach
    public void setUp() {
        victima = new PersonajePrueba("Sujeto de Prueba", 50, 100,100);
    }

    // TESTS DE ESTADO NORMAL

    @Test
    public void testEstadoNormalPuedeAtacarYRecibeDanio() {
        assertTrue(victima.puedeAtacar(), "En estado normal debería poder atacar.");
        
        boolean impacto = victima.recibirDanio(20);
        
        assertTrue(impacto, "El ataque debería impactar.");
        assertEquals(80, victima.getPuntosVida(), "Debería haber perdido 20 de vida.");
        assertEquals(EstadoNormal.class, victima.getEstadoActual().getClass(), "Debería seguir en EstadoNormal tras recibir daño.");
    }

    // TESTS DE ESTADO ATURDIDO

    @Test
    public void testEstadoAturdidoBloqueaAtaqueYSeConsumeConElTiempo() {
        victima.setEstado(new EstadoAturdido(1)); // Aturdido por 2 turnos
        
        // Turno 1
        assertFalse(victima.puedeAtacar(), "No debería poder atacar en el turno 1.");
        victima.inicioDeTurno(); 
        
        // Turno 2
        assertFalse(victima.puedeAtacar(), "No debería poder atacar en el turno 2.");
        victima.inicioDeTurno(); 
        
        // Turno 3 (Ya debería haberse recuperado)
        assertTrue(victima.puedeAtacar(), "Debería haberse recuperado tras 2 turnos.");
        assertEquals(EstadoNormal.class, victima.getEstadoActual().getClass(), "El estado debió volver a Normal.");
    }

    // TESTS DE ESTADO SANGRADO

    @Test
    public void testEstadoSangradoAplicaDanioPorTurno() {
        victima.setEstado(new EstadoSangrado(2, 10)); // 2 turnos, 10 de daño por turno
        
        // Turno 1
        victima.inicioDeTurno();
        assertEquals(90, victima.getPuntosVida(), "El Sangrado debió restar 10 de vida en el primer turno.");
        
        // Turno 2
        victima.inicioDeTurno();
        assertEquals(80, victima.getPuntosVida(), "El Sangrado debió restar 10 de vida en el segundo turno.");
        
        assertEquals(EstadoNormal.class, victima.getEstadoActual().getClass(), "El efecto de veneno debió disiparse.");
    }

    // TESTS DE ESTADO REGENERACION

    @Test
    public void testEstadoRegeneracionCuraVidaPorTurno() {
        victima.recibirDanio(50); // Lo dejamos en 50 de vida
        victima.setEstado(new EstadoRegeneracion(2, 15)); // 2 turnos, cura 15 por turno
        
        // Turno 1
        victima.inicioDeTurno();
        assertEquals(65, victima.getPuntosVida(), "Debería haberse curado 15 puntos (50 + 15).");
        
        // Turno 2
        victima.inicioDeTurno();
        assertEquals(80, victima.getPuntosVida(), "Debería haberse curado otros 15 puntos (65 + 15).");
        
        assertEquals(EstadoNormal.class, victima.getEstadoActual().getClass(), "El estado de regeneración debió terminar.");
    }

    // TESTS DE ESTADO ESCUDO (Protego)

    @Test
    public void testEstadoEscudoAbsorbeDanioParcialYSeMantiene() {
        victima.setEstado(new EstadoEscudo(30)); // Escudo de 30 puntos
        
        victima.recibirDanio(20); // Recibe 20 de daño
        
        assertEquals(100, victima.getPuntosVida(), "La vida no debería haber bajado.");
        assertEquals(EstadoEscudo.class, victima.getEstadoActual().getClass(), "El escudo no se rompió, debería seguir activo.");
    }

    @Test
    public void testEstadoEscudoSeRompeYFiltraDanioSobrante() {
        victima.setEstado(new EstadoEscudo(20)); // Escudo de 20 puntos
        
        victima.recibirDanio(50); // Recibe 50 de daño (20 al escudo, 30 a la vida)
        
        assertEquals(70, victima.getPuntosVida(), "Debería haber recibido 30 puntos de daño tras romperse el escudo.");
        assertEquals(EstadoNormal.class, victima.getEstadoActual().getClass(), "El escudo debió romperse y volver al estado normal.");
    }
    
    // TESTS DE ESTADO EVASIÓN (Cave Inimicum)

    @Test
    public void testEstadoEvasionAnulaPrimerAtaqueYVuelveANormal() {
        victima.setEstado(new EstadoEvasion());
        int vidaInicial = victima.getPuntosVida();

        // Primer ataque: Debe ser esquivado por completo (devuelve false)
        boolean impactoPrimero = victima.recibirDanio(40);
        
        assertFalse(impactoPrimero, "El primer ataque debió haber sido esquivado.");
        assertEquals(vidaInicial, victima.getPuntosVida(), "La vida no debió bajar.");
        assertEquals(EstadoNormal.class, victima.getEstadoActual().getClass(), "Debió transicionar a EstadoNormal tras la evasión.");

        // Segundo ataque: Ya no está invisible, debe impactar (devuelve true)
        boolean impactoSegundo = victima.recibirDanio(20);
        
        assertTrue(impactoSegundo, "El segundo ataque debió impactar porque ya se rompió la evasión.");
        assertEquals(vidaInicial - 20, victima.getPuntosVida(), "La vida debió bajar en el segundo impacto.");
    }

    // TESTS DE ESTADO FURIA (Comandante Reactivo)

    @Test
    public void testEstadoFuriaPotenciaElProximoDanioYSeConsume() {
        victima.setEstado(new EstadoFuria());
        int danioBase = 50;
        Hechizo hechizoPrueba = new HechizoAtaqueBasico("Flipendo", danioBase);
        // Primer cálculo: Debe aplicar el multiplicador del 50% (50 * 1.5 = 75)
        int danioPotenciado = victima.calcularDanio(hechizoPrueba,danioBase);
        
        assertEquals(75, danioPotenciado, "El daño debió potenciarse un 50% debido a la furia.");
        assertEquals(EstadoNormal.class, victima.getEstadoActual().getClass(), "El estado debió volver a Normal inmediatamente tras atacar.");

        // Segundo cálculo: Al haber vuelto a la normalidad, el daño ya no se potencia
        int danioSiguienteTurno = victima.calcularDanio(hechizoPrueba,danioBase);
        
        assertEquals(danioBase, danioSiguienteTurno, "El segundo ataque debió infligir el daño base porque la furia ya se consumió.");
    }

}
