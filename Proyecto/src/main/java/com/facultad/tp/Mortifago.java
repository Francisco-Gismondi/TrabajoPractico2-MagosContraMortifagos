package com.facultad.tp;

public abstract class Mortifago extends Personaje {

    public Mortifago(String nombre, int nivelMagia, int puntosVida) {
        super(nombre, nivelMagia, puntosVida);
    }

    @Override
    public int calcularDanio(Hechizo hechizo, int danioBase) {
        return (int) (danioBase * (1.0 + nivelMagia / 80.0));
    }

    @Override
    public int calcularCuracion(Hechizo hechizo, int curacionBase) {
        return (int) (curacionBase * (1.0 - (100 - nivelMagia) / 200.0));
    }
}
