package com.facultad.tp.objetos;

import com.facultad.tp.Personaje;

public class AmuletoMana implements ObjetoMagico {
    private static final int MANA_POR_TURNO = 10;

    @Override
    public String getNombre() {
        return "Amuleto de Maná";
    }

    @Override
    public String getDescripcion() {
        return "Recupera " + MANA_POR_TURNO + " de maná al inicio de cada turno";
    }

    @Override
    public void alEquipar(Personaje p) {
        System.out.println("  ~ " + p.getNombre() + " se equipa el " + getNombre());
    }

    @Override
    public void inicioDeTurno(Personaje p) {
        int manaAntes = p.getManaActual();
        p.recuperarMana(MANA_POR_TURNO);
        int recuperado = p.getManaActual() - manaAntes;
        if (recuperado > 0) {
            System.out.println("  ~ " + p.getNombre() + " recupera " + recuperado + " de maná por el " + getNombre());
        }
    }
}
