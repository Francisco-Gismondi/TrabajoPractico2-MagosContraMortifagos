package com.facultad.tp.objetos;

import com.facultad.tp.Hechizo;

public class VaritaMagica implements ObjetoMagico {
    private static final double BONUS_ATAQUE = 0.3;

    @Override
    public String getNombre() {
        return "Varita Mágica";
    }

    @Override
    public String getDescripcion() {
        return "Potencia el daño de hechizos de ataque en un 30%";
    }

    @Override
    public int modificarDanio(Hechizo hechizo, int danio) {
        if (hechizo.esAtaque()) {
            return (int) (danio * (1.0 + BONUS_ATAQUE));
        }
        return danio;
    }
}
