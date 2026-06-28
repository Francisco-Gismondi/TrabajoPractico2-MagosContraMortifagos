package com.facultad.tp.hechizos;

import com.facultad.tp.Hechizo;
import com.facultad.tp.Personaje;

import estados.EstadoEvasion;

public class CaveInimicum implements Hechizo {

	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		System.out.println(" * " + lanzador.getNombre() + " lanza Cave Inimicum. " + objetivo.getNombre()
				+ " se vuelve invisible y evitara el proximo ataque.");
		objetivo.setEstado(new EstadoEvasion());
	}

	@Override
	public String getNombre() {

		return "Cave Inimicum";
	}

	@Override
	public boolean esAtaque() {
		return false;
	}

	@Override
	public boolean esDefensa() {
		return true;
	}

	@Override
	public boolean esCuracion() {
		return false;
	}

	@Override
	public int getCostoMana() {
		return 10;
	}

}
