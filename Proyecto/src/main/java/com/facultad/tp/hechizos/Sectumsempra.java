package com.facultad.tp.hechizos;

import batalla.Personaje;
//ATAQUE
public class Sectumsempra implements Hechizo {

	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		//Ponele que sea solo el estado sin daño base
		int dañoBase = 10;
		int dañoFinal = dañoBase * lanzador.obtenerAfinidadAtaque();
		
		objetivo.recibirDaño(dañoFinal);
		
		System.out.println(lanzador);
		
		System.out.println(lanzador.getNombre() + " lanza Sectumsempra. " 
                + objetivo.getNombre() + " recibe " + dañoFinal + " de daño inicial y comienza a sangrar.");
		
		// Agregar a la lista de estados del personaje algo que le reste vida cada turno:
        // objetivo.aplicarEstado(new EstadoSangrado(5)); // Le resta 5 de vida por turno
	}
	

}
