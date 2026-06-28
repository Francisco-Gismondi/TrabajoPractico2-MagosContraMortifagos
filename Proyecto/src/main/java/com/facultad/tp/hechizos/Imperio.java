package com.facultad.tp.hechizos;

import com.facultad.tp.Hechizo;
import com.facultad.tp.Personaje;

//ATAQUE OSCURO
public class Imperio implements Hechizo {

	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		int nivelLanzador = lanzador.getNivelMagia();
		int nivelObjetivo = objetivo.getNivelMagia();
		int danioBase = 20;
		int danioFinal = objetivo.calcularDanio(this, danioBase);
		System.out.println(" * " + lanzador.getNombre() + " lanza Imperio...");
		if (nivelLanzador < nivelObjetivo) {
			System.out.println("  -> Pero no logra controlar a " + objetivo.getNombre());
			return;
		}
		objetivo.recibirDanio(danioFinal);

	}

	@Override
	public String getNombre() {
		return null;
	}

	@Override
	public boolean esAtaque() {
		return true;
	}

	@Override
	public boolean esDefensa() {
		return false;
	}

	@Override
	public boolean esCuracion() {
		return false;
	}

	@Override
	public int getCostoMana() {
		// TODO Auto-generated method stub
		return 25;
	}

}
