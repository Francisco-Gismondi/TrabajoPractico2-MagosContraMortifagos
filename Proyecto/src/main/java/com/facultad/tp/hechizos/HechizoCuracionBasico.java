package com.facultad.tp.hechizos;

import com.facultad.tp.Hechizo;
import com.facultad.tp.Personaje;


//PARA HECHIZOS QUE SOLO CURAN, SIN EFECTOS SECUNDARIOS
//Episkey
//Ferula
//Brackium Emendo
public class HechizoCuracionBasico implements Hechizo {

	private String nombre;
	private int curacionBase;
	
	public HechizoCuracionBasico(String nombre,int curacionBase) {
		this.nombre = nombre;
		this.curacionBase = curacionBase;
	}
	
	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		int curacionFinal = lanzador.calcularCuracion(this, curacionBase);
		objetivo.curar(curacionFinal);
		
		System.out.println(" * " + lanzador.getNombre() + " lanza " + this.nombre + " sobre " +
							objetivo.getNombre() + ", curándole " + curacionFinal + " puntos de vida.");
		
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public boolean esAtaque() {
		return false;
	}

	@Override
	public boolean esDefensa() {
		return false;
	}

	@Override
	public boolean esCuracion() {
		return true;
	}

	@Override
	public int getCostoMana() {
		// TODO Auto-generated method stub
		return 10;
	}

}
