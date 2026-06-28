package com.facultad.tp.hechizos;

import com.facultad.tp.Hechizo;
import com.facultad.tp.Personaje;

import estados.EstadoNormal;

public class FiniteIncantatem implements Hechizo {

    @Override
    public void ejecutar(Personaje lanzador, Personaje objetivo) {
        System.out.println(" * " + lanzador.getNombre() + " lanza Finite Incantatem sobre " + objetivo.getNombre() + 
                           " ¡Todos los efectos mágicos son anulados!");
        
        objetivo.setEstado(new EstadoNormal());
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

	@Override
	public int getCostoMana() {
		return 15;
	}
}