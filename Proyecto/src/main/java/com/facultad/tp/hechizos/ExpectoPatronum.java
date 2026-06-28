package com.facultad.tp.hechizos;

import com.facultad.tp.Hechizo;
import com.facultad.tp.Personaje;

public class ExpectoPatronum implements Hechizo {

    @Override
    public String getNombre() {
        return "Expecto Patronum";
    }

    @Override
    public void ejecutar(Personaje lanzador, Personaje objetivo) {
        int curacionBase = 35;
        int curacionFinal = lanzador.calcularCuracion(this, curacionBase);
        System.out.println(" * " + lanzador.getNombre() + " lanza Expecto Patronum");
        objetivo.curar(curacionFinal);
    }

    @Override
    public boolean esAtaque() {
        return false;
    }

    @Override
    public boolean esDefensa() {
        return false;
    }

    @Override
    public boolean esCuracion() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ExpectoPatronum;
    }

    @Override
    public int hashCode() {
        return getNombre().hashCode();
    }
}
