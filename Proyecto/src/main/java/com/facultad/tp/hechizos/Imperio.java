package com.facultad.tp.hechizos;

import com.facultad.tp.Hechizo;
import com.facultad.tp.Personaje;

//ATAQUE OSCURO
public class Imperio implements Hechizo{

	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		int afinidad = lanzador.obtenerAfinidadOscura();
		int daño = 10 * objetivo.obtenerAfinidadAtaque();
		
		if(afinidad < objetivo.obtenerAfinidadSoporte()) {
			System.out.println(lanzador.getNombre() + "no logra controlar a " + objetivo.getNombre());
			return;
		}
		
		System.out.println(lanzador.getNombre() + "lanza Imperio y controla la mente de " + objetivo.getNombre());
		objetivo.recibirDanio(daño);
		System.out.println("Bajo control mental, " + objetivo.getNombre() + "se hiere a si mismo, perdiendo " + daño + " puntos de vida.");
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public boolean esAtaque() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean esDefensa() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean esCuracion() {
		// TODO Auto-generated method stub
		return false;
	}

}
