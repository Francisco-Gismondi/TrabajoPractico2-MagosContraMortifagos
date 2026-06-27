package com.facultad.tp;

import com.facultad.tp.hechizos.*;
import java.util.Arrays;

public class Seguidor extends Mortifago {

    public Seguidor(String nombre, int nivelMagia, int puntosVida, int mana) {
        super(nombre, nivelMagia, puntosVida,mana);
        this.hechizos = Arrays.asList(
            new Expelliarmus(),
            new AvadaKedavra()
        );
    }
}
