package com.facultad.tp.hechizos;

import java.util.Random;
import com.facultad.tp.Hechizo;
import com.facultad.tp.Personaje;
import estados.EstadoEscudo;

public class Protego implements Hechizo {

	private Random rand = new Random();
	
    @Override
    public String getNombre() {
        return "Protego";
    }

    @Override
    public void ejecutar(Personaje lanzador, Personaje objetivo) {
    	int escudoMaximo = 40 + (lanzador.getNivelMagia() / 10) + (lanzador.getPuntosVida() / 10); //100% del escudo
    	int escudoMinimo = (int) (escudoMaximo * 0.70); //70% del escudo

    	int rango = escudoMaximo - escudoMinimo + 1; //btenemos un rango entre 1 y 30

    	int poderDelEscudo = rand.nextInt(rango) + escudoMinimo; //generamos un escudo que varia
        
        objetivo.setEstado(new EstadoEscudo(poderDelEscudo));
        
        System.out.println(" * " + lanzador.getNombre() + " lanza protego y genera un escudo de " + poderDelEscudo + " puntos.");
    }

    @Override
    public boolean esAtaque() {
        return false;
    }

    @Override
    public boolean esDefensa() {
        return true;
    }

    @Override
    public boolean esCuracion() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Protego;
    }

    @Override
    public int hashCode() {
        return getNombre().hashCode();
    }

	@Override
	public int getCostoMana() {
		// TODO Auto-generated method stub
		return 15;
	}
}
