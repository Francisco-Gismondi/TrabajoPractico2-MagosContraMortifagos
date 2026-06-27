package com.facultad.tp;

import com.facultad.tp.hechizos.*;
import java.util.Arrays;

public class Auror extends Mago {

    public Auror(String nombre, int nivelMagia, int puntosVida,int mana) {
        super(nombre, nivelMagia, puntosVida, mana);
        this.hechizos = Arrays.asList(
            new Expelliarmus(),
            new Protego(),
            new ExpectoPatronum()
        );
    }
}
