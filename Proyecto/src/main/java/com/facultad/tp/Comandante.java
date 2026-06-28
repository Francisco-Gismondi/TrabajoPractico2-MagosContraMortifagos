package com.facultad.tp;

import com.facultad.tp.hechizos.*;
import java.util.Arrays;

public class Comandante extends Mortifago {

    public Comandante(String nombre, int nivelMagia, int puntosVida, int mana) {
        super(nombre, nivelMagia, puntosVida,mana);
        this.hechizos = Arrays.asList(
        		new Sectumsempra(),
                new Protego(),
                new Crucio(),
                new Imperio(),
                new AvadaKedavra()
        );
    }
}
