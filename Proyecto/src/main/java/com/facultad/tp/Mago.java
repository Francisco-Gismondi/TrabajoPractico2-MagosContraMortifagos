package com.facultad.tp;

public abstract class Mago extends Personaje {

	public Mago(String nombre, int nivelMagia, int puntosVida, int mana) {
		super(nombre, nivelMagia, puntosVida, mana);
	}

	@Override
	public int calcularCuracion(Hechizo hechizo, int curacionBase) {

        return (int) (curacionBase * (1.0 + nivelMagia / 100.0));
	}

	@Override
	public int calcularDanio(Hechizo hechizo, int danioBase) {
		int danioPorAtributos = (int) (danioBase * (1.0 + nivelMagia / 100.0));

		return super.calcularDanio(hechizo, danioPorAtributos);
	}

	@Override
	public boolean puedeLanzarMagiaOscura() {
		return false;
	}
}
