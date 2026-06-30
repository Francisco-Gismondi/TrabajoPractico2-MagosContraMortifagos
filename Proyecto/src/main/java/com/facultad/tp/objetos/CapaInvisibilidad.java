package com.facultad.tp.objetos;

import com.facultad.tp.Personaje;
import estados.EstadoEvasion;

public class CapaInvisibilidad implements ObjetoMagico {
    private boolean disponible = true;

    @Override
    public String getNombre() {
        return "Capa de Invisibilidad";
    }

    @Override
    public String getDescripcion() {
        return "Esquiva automáticamente el próximo ataque. Se reactiva al re-equipar.";
    }

    @Override
    public void alEquipar(Personaje p) {
        if (disponible) {
            p.setEstado(new EstadoEvasion());
            System.out.println("  ~ " + p.getNombre() + " se vuelve invisible con la " + getNombre());
            disponible = false;
        }
    }

    @Override
    public void alDesequipar(Personaje p) {
        disponible = true;
    }
}
