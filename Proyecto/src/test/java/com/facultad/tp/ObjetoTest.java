package com.facultad.tp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.facultad.tp.hechizos.*;
import com.facultad.tp.objetos.*;
import estados.*;

class ObjetoTest {

    class PersonajePrueba extends Personaje {
        public PersonajePrueba(String nombre, int nivelMagia, int puntosVida, int mana) {
            super(nombre, nivelMagia, puntosVida, mana);
        }
        @Override
        public boolean puedeLanzarMagiaOscura() { return false; }
    }

    // ---------- VaritaMagica ----------

    @Test
    public void testVaritaNombreYDescripcion() {
        VaritaMagica v = new VaritaMagica();
        assertEquals("Varita Mágica", v.getNombre());
        assertNotNull(v.getDescripcion());
    }

    @Test
    public void testVaritaPotenciaHechizoAtaque() {
        VaritaMagica v = new VaritaMagica();
        Hechizo ataque = new HechizoAtaqueBasico("Flipendo", 10);
        int danio = v.modificarDanio(ataque, 100);
        assertEquals(130, danio, "La varita debe aumentar el daño en un 30%");
    }

    @Test
    public void testVaritaNoPotenciaCuracion() {
        VaritaMagica v = new VaritaMagica();
        Hechizo cura = new HechizoCuracionBasico("Ferula", 10);
        int danio = v.modificarDanio(cura, 100);
        assertEquals(100, danio, "La varita no debe modificar hechizos de curacion");
    }

    @Test
    public void testVaritaNoPotenciaDefensa() {
        VaritaMagica v = new VaritaMagica();
        Hechizo def = new Protego();
        int danio = v.modificarDanio(def, 100);
        assertEquals(100, danio, "La varita no debe modificar hechizos de defensa");
    }

    // ---------- CapaInvisibilidad ----------

    @Test
    public void testCapaOtorgaEvasionAlEquipar() {
        Personaje p = new PersonajePrueba("Test", 50, 100, 100);
        CapaInvisibilidad capa = new CapaInvisibilidad();
        assertFalse(p.getEstadoActual() instanceof EstadoEvasion);
        capa.alEquipar(p);
        assertTrue(p.getEstadoActual() instanceof EstadoEvasion,
            "La capa debe poner al personaje en EstadoEvasion");
    }

    @Test
    public void testCapaSeRearmaAlDesequipar() {
        Personaje p = new PersonajePrueba("Test", 50, 100, 100);
        CapaInvisibilidad capa = new CapaInvisibilidad();

        capa.alEquipar(p);
        assertTrue(p.getEstadoActual() instanceof EstadoEvasion);

        p.recibirDanio(1);
        assertFalse(p.getEstadoActual() instanceof EstadoEvasion,
            "La evasion debe consumirse al recibir danio");

        capa.alDesequipar(p);
        capa.alEquipar(p);
        assertTrue(p.getEstadoActual() instanceof EstadoEvasion,
            "La capa debe poder reutilizarse tras desequipar y re-equipar");
    }

    // ---------- AmuletoMana ----------

    @Test
    public void testAmuletoRecuperaManaEnInicioDeTurno() {
        Personaje p = new PersonajePrueba("Test", 50, 100, 50);
        AmuletoMana amuleto = new AmuletoMana();

        p.recuperarMana(-30);
        assertEquals(20, p.getManaActual());

        amuleto.inicioDeTurno(p);
        assertEquals(30, p.getManaActual(), "El amuleto debe recuperar 10 de mana");
    }

    @Test
    public void testAmuletoNoSobrepasaManaMaximo() {
        Personaje p = new PersonajePrueba("Test", 50, 100, 30);
        AmuletoMana amuleto = new AmuletoMana();

        amuleto.inicioDeTurno(p);
        assertEquals(30, p.getManaActual(), "No debe superar el mana maximo de 30");
    }

    @Test
    public void testAmuletoAlEquiparNoAlteraMana() {
        Personaje p = new PersonajePrueba("Test", 50, 100, 50);
        AmuletoMana amuleto = new AmuletoMana();
        int manaAntes = p.getManaActual();
        amuleto.alEquipar(p);
        assertEquals(manaAntes, p.getManaActual(),
            "El amuleto no debe modificar mana al equiparse, solo al inicio del turno");
    }

    // ---------- FabricaObjetos ----------

    @Test
    public void testFabricaObjetosCreaTodosLosTipos() {
        boolean vioVarita = false, vioCapa = false, vioAmuleto = false;
        for (int i = 0; i < 30; i++) {
            ObjetoMagico obj = FabricaObjetos.crearObjetoAleatorio();
            assertNotNull(obj);
            assertNotNull(obj.getNombre());
            assertNotNull(obj.getDescripcion());
            if (obj instanceof VaritaMagica) vioVarita = true;
            if (obj instanceof CapaInvisibilidad) vioCapa = true;
            if (obj instanceof AmuletoMana) vioAmuleto = true;
        }
        assertTrue(vioVarita && vioCapa && vioAmuleto,
            "La fabrica debe generar los 3 tipos de objetos");
    }

    // ---------- Personaje: inventario y equipamiento ----------

    @Test
    public void testAgregarObjetoAlInventario() {
        Personaje p = new PersonajePrueba("Test", 50, 100, 100);
        assertTrue(p.getInventario().isEmpty());
        p.agregarObjeto(new VaritaMagica());
        assertEquals(1, p.getInventario().size());
    }

    @Test
    public void testEquiparObjetoDelInventario() {
        Personaje p = new PersonajePrueba("Test", 50, 100, 100);
        ObjetoMagico v = new VaritaMagica();
        p.agregarObjeto(v);

        assertTrue(p.getObjetosEquipados().isEmpty());
        assertTrue(p.equipar(v));
        assertTrue(p.getObjetosEquipados().contains(v));
        assertFalse(p.getInventario().contains(v));
    }

    @Test
    public void testDesequiparVuelveAlInventario() {
        Personaje p = new PersonajePrueba("Test", 50, 100, 100);
        ObjetoMagico v = new VaritaMagica();
        p.agregarObjeto(v);
        p.equipar(v);

        assertTrue(p.desequipar(v));
        assertFalse(p.getObjetosEquipados().contains(v));
        assertTrue(p.getInventario().contains(v));
    }

    @Test
    public void testNoSePuedeEquiparObjetoNoEnInventario() {
        Personaje p = new PersonajePrueba("Test", 50, 100, 100);
        assertFalse(p.equipar(new VaritaMagica()),
            "No se deberia poder equipar un objeto que no esta en el inventario");
    }

    @Test
    public void testEquiparMultipleHastaLimite() {
        Personaje p = new PersonajePrueba("Test", 50, 100, 100);
        ObjetoMagico v = new VaritaMagica();
        ObjetoMagico c = new CapaInvisibilidad();
        ObjetoMagico a = new AmuletoMana();
        p.agregarObjeto(v); p.equipar(v);
        p.agregarObjeto(c); p.equipar(c);
        p.agregarObjeto(a); p.equipar(a);

        assertEquals(3, p.getObjetosEquipados().size());

        ObjetoMagico extra = new VaritaMagica();
        p.agregarObjeto(extra);
        assertFalse(p.equipar(extra), "No debe permitir mas de 3 objetos equipados");
    }

    // ---------- Integracion: danio con/sin objetos ----------

    @Test
    public void testDanioAumentaConVaritaEquipada() {
        Personaje p = new PersonajePrueba("Test", 50, 100, 100);
        Hechizo ataque = new HechizoAtaqueBasico("Flipendo", 10);
        int danioSin = p.calcularDanio(ataque, 100);

        ObjetoMagico v = new VaritaMagica();
        p.agregarObjeto(v);
        p.equipar(v);

        int danioCon = p.calcularDanio(ataque, 100);
        assertEquals((int)(danioSin * 1.3), danioCon,
            "El danio debe ser 30% mayor con la varita equipada");
    }

    @Test
    public void testDanioSinObjetosEsNormal() {
        Personaje p = new PersonajePrueba("Test", 50, 100, 100);
        Hechizo ataque = new HechizoAtaqueBasico("Flipendo", 10);
        int danio = p.calcularDanio(ataque, 100);
        assertTrue(danio > 0);
    }

    @Test
    public void testManaRecuperaConAmuletoEnInicioDeTurno() {
        Personaje p = new PersonajePrueba("Test", 50, 100, 30);
        AmuletoMana a = new AmuletoMana();
        p.agregarObjeto(a);
        p.equipar(a);

        p.recuperarMana(-20);
        assertEquals(10, p.getManaActual());

        p.inicioDeTurno();
        assertEquals(20, p.getManaActual(),
            "El amuleto debe recuperar 10 de mana al inicio del turno via Personaje.inicioDeTurno()");
    }

    @Test
    public void testDanioNoSeModificaConCapa() {
        Personaje p = new PersonajePrueba("Test", 50, 100, 100);
        Hechizo ataque = new HechizoAtaqueBasico("Flipendo", 10);
        int danioSin = p.calcularDanio(ataque, 100);

        CapaInvisibilidad c = new CapaInvisibilidad();
        p.agregarObjeto(c);
        p.equipar(c);

        int danioCon = p.calcularDanio(ataque, 100);
        assertEquals(danioSin, danioCon,
            "La capa no debe modificar el danio");
    }
}
