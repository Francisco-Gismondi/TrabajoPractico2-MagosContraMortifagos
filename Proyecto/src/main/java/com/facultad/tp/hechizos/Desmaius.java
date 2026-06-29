package com.facultad.tp.hechizos;

import com.facultad.tp.Hechizo;
import com.facultad.tp.Personaje;

import estados.EstadoAturdido;

//ATAQUE
public class Desmaius implements Hechizo{

	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		int dañoBase = 5;
		int dañoFinal = lanzador.calcularDanio(this, dañoBase);
        int turnos = 1;
        System.out.println(" * " + lanzador.getNombre() + " lanza Desmaius. "); 
        
        boolean impacto = objetivo.recibirDanio(dañoFinal);
        
        if(impacto) {
        System.out.println("  -> " + objetivo.getNombre() + " queda aturdido.");
        objetivo.setEstado(new EstadoAturdido(turnos));
        }
	}

	@Override
	public String getNombre() {
		return "Desmaius";
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
		return 10;
	}

}
