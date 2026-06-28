package com.facultad.tp;

public interface Hechizo {
    String getNombre();
    void ejecutar(Personaje lanzador, Personaje objetivo);
    public int getCostoMana();
    boolean esAtaque();
    boolean esDefensa();
    boolean esCuracion();
    
}
