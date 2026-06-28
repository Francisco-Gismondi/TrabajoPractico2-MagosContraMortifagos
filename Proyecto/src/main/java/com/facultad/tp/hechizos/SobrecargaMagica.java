package com.facultad.tp.hechizos;

import com.facultad.tp.Hechizo;
import com.facultad.tp.Personaje;

public class SobrecargaMagica implements Hechizo {

	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		int danioPorDescontrol = 25; // El daño fijo que sufrirán ambos
		System.out.println(" * " + "¡BOOM! La varita de " + lanzador.getNombre() + " estalla por falta de energía mágica.");
		lanzador.recibirDanio(danioPorDescontrol);
		objetivo.recibirDanio(danioPorDescontrol);
		
	}

	@Override
	public int getCostoMana() {
		return 0; // Este hechizo es un castigo, no cuesta maná
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
}
