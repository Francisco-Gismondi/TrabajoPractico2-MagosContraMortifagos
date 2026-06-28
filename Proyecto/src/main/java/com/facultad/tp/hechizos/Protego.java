package com.facultad.tp.hechizos;

import com.facultad.tp.Hechizo;
import com.facultad.tp.Personaje;

public class Protego implements Hechizo {

    @Override
    public String getNombre() {
        return "Protego";
    }

    @Override
    public void ejecutar(Personaje lanzador, Personaje objetivo) {
        System.out.println(" * " + lanzador.getNombre() + " lanza Protego y se protege.");
    }

    @Override
    public boolean esAtaque() {
        return false;
    }

    @Override
    public boolean esDefensa() {
        return true;
    }

    @Override
    public boolean esCuracion() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Protego;
    }

    @Override
    public int hashCode() {
        return getNombre().hashCode();
    }
}
