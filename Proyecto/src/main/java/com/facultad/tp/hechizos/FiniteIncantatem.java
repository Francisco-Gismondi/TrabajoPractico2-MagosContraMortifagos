package com.facultad.tp.hechizos;

import com.facultad.tp.Hechizo;
import com.facultad.tp.Personaje;

public class FiniteIncantatem implements Hechizo {

    @Override
    public void ejecutar(Personaje lanzador, Personaje objetivo) {
        System.out.println(lanzador.getNombre() + " lanza Finite Incantatem sobre " + objetivo.getNombre() + 
                           " ¡Todos los efectos mágicos son anulados!");
        
        // objetivo.limpiarTodosLosEstados(); 
        // (Este método vaciaría la lista de estados alterados y pondría los escudos a 0).
    }

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return "Finite Incantatem";
	}

	@Override
	public boolean esAtaque() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean esDefensa() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean esCuracion() {
		// TODO Auto-generated method stub
		return true;
	}
}