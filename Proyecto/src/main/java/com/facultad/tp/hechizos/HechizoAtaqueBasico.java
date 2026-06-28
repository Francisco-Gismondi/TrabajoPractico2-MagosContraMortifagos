package com.facultad.tp.hechizos;

import com.facultad.tp.Hechizo;
import com.facultad.tp.Personaje;

//PARA HECHIZOS QUE SOLO SACAN VIDA, SIN EFECTOS SECUNDARIOS
//Flipendo
//Expelliarmus
//Incendio
public class HechizoAtaqueBasico implements Hechizo {
	private String nombre;
	private int dañoBase;

	public HechizoAtaqueBasico(String nombre, int dañoBase) {
		this.nombre = nombre;
		this.dañoBase = dañoBase;
	}

	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		int danioFinal = lanzador.calcularDanio(this, dañoBase);
		System.out.println(" * " + lanzador.getNombre() + " lanza " + this.nombre);
		objetivo.recibirDanio(danioFinal);
	}

	@Override
	public String getNombre() {
		return this.nombre;
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
		return 5;
	}

}
