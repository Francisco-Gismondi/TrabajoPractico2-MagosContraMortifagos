package com.facultad.tp;

public interface Hechizo {
    String getNombre();
    void ejecutar(Personaje lanzador, Personaje objetivo);
    boolean esAtaque();
    boolean esDefensa();
    boolean esCuracion();
}
