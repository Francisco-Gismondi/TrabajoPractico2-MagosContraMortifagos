package com.facultad.tp.objetos;

import com.facultad.tp.Hechizo;
import com.facultad.tp.Personaje;

public interface ObjetoMagico {
    String getNombre();
    String getDescripcion();

    default int modificarDanio(Hechizo hechizo, int danio) {
        return danio;
    }

    default void alEquipar(Personaje p) {}
    default void alDesequipar(Personaje p) {}
    default void inicioDeTurno(Personaje p) {}
}
