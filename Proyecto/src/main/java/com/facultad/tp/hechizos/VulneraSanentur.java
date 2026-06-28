package com.facultad.tp.hechizos;

import com.facultad.tp.Hechizo;
import com.facultad.tp.Personaje;
import estados.EstadoRegeneracion;

//CURACION
public class VulneraSanentur implements Hechizo {

	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		int curacionBase = 10;
		int curacionInicial = lanzador.calcularCuracion(this, curacionBase);
        int curacionPorTurno = curacionInicial/2;
        int turnosDeRegeneracion = 3;
				
		objetivo.curar(curacionInicial);
		
		System.out.println(" * " + lanzador.getNombre() + " lanza Vulnera Sanentur sobre " 
                + objetivo.getNombre() + ". Se curan " + curacionInicial + " puntos de vida y sus heridas comienzan a cerrarse lentamente.");
		
        objetivo.setEstado(new EstadoRegeneracion(curacionPorTurno,turnosDeRegeneracion));

	}

	@Override
	public String getNombre() {
		return "Vulnera Sanentur";
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
		return 25;
	}
}
