package com.facultad.tp.hechizos;

import com.facultad.tp.Hechizo;
import com.facultad.tp.Personaje;

import estados.EstadoAturdido;

//ATAQUE OSCURO
public class Crucio implements Hechizo {

	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		int danioBase = 20;
		boolean impacto;
		
		System.out.println(" * " + lanzador.getNombre() + " lanza Crucio");
		
		if (!lanzador.puedeLanzarMagiaOscura()) {
			impacto = objetivo.recibirDanio(5);
			return;
		}
		int danioFinal = lanzador.calcularDanio(this, danioBase);
		impacto = objetivo.recibirDanio(danioFinal);

		if (impacto) {
			System.out.println("  -> " + objetivo.getNombre() + " queda aturdido.");
			objetivo.setEstado(new EstadoAturdido(2));
		}
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return "Crucio";
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
