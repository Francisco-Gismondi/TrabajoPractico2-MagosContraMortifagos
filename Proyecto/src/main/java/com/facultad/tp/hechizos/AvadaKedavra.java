package com.facultad.tp.hechizos;

import com.facultad.tp.Hechizo;
import com.facultad.tp.Personaje;

public class AvadaKedavra implements Hechizo {

    @Override
    public String getNombre() {
        return "Avada Kedavra";
    }

    @Override
    public void ejecutar(Personaje lanzador, Personaje objetivo) {
        int danioBase = 40;
        int danioFinal = lanzador.calcularDanio(this, danioBase);
        objetivo.recibirDanio(danioFinal);
        System.out.println(" * " + lanzador.getNombre() + " lanza Avada Kedavra a " + objetivo.getNombre()
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
        return obj instanceof AvadaKedavra;
    }

    @Override
    public int hashCode() {
        return getNombre().hashCode();
    }
}
