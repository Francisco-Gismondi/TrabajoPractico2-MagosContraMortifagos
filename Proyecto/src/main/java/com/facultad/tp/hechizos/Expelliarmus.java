package com.facultad.tp.hechizos;

import com.facultad.tp.Hechizo;
import com.facultad.tp.Personaje;

public class Expelliarmus implements Hechizo {

    @Override
    public String getNombre() {
        return "Expelliarmus";
    }

    @Override
    public void ejecutar(Personaje lanzador, Personaje objetivo) {
        int danioBase = 25;
        int danioFinal = lanzador.calcularDanio(this, danioBase);
        objetivo.recibirDanio(danioFinal);
        System.out.println(" * " + lanzador.getNombre() + " lanza Expelliarmus a " + objetivo.getNombre()
            + " causando " + danioFinal + " de danio.");
    }

    @Override
    public boolean esAtaque() {
        return true;
    }

    @Override
    public boolean esDefensa() {
        return false;
    }

    @Override
    public boolean esCuracion() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Expelliarmus;
    }

    @Override
    public int hashCode() {
        return getNombre().hashCode();
    }
}
