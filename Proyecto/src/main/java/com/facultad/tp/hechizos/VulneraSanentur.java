package com.facultad.tp.hechizos;

import batalla.Personaje;
//CURACION
public class VulneraSanentur implements Hechizo {

	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		int curacionBase = 10;
		
		int curacionFinal = curacionBase * lanzador.obtenerAfinidadCuracion();
		
		objetivo.curarVida(curacionFinal);
		
		System.out.println(lanzador.getNombre() + " lanza Vulnera Sanentur sobre " 
                + objetivo.getNombre() + ". Se curan " + curacionFinal + " puntos de vida y sus heridas comienzan a cerrarse lentamente.");
		
		// En el motor de turnos, el objetivo debería recibir un estado de "Regeneración"
        // objetivo.aplicarEstado(new EstadoRegeneracion(5 * lanzador.obtenerAfinidadCuracion(), 3));
		// (Ejemplo: cura X puntos extra durante los próximos 3 turnos)
	}
	
}
