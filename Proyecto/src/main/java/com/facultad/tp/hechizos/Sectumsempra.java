package com.facultad.tp.hechizos;

import com.facultad.tp.*;

import estados.*;

//ATAQUE
public class Sectumsempra implements Hechizo {

	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		int danioBase = 5;
		int danioTotal = lanzador.calcularDanio(this, danioBase);
		int turnos = 3;
		System.out.println(" * " + lanzador.getNombre() + " lanza Sectumsempra. ");

		boolean impacto = objetivo.recibirDanio(danioTotal);
		if (impacto) {
			System.out.println("  -> " + objetivo.getNombre() + " comienza a sangrar.");
			objetivo.setEstado(new EstadoSangrado(danioBase, turnos));
		}
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
		return 20;
	}

}
