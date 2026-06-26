package com.facultad.tp.hechizos;

import com.facultad.tp.Hechizo;
import com.facultad.tp.Personaje;

//ATAQUE
public class Desmaius implements Hechizo{

	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		int dañoBase = 20;
		int dañoFinal = dañoBase * lanzador.obtenerAfinidadAtaque();
        
        objetivo.recibirDanio(dañoFinal);
        
        System.out.println(lanzador.getNombre() + " lanza Desmaius. " 
                + objetivo.getNombre() + " recibe " + dañoFinal + " de daño y queda aturdido.");
        
        // HAY QUE AGREGAR ESTADOS A LOS PERSONAJES E IMPLEMENTAR ALGO ASI
        //  objetivo.aplicarEstado(new EstadoAturdido(1));
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return "Desmaius";
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
		return false;
	}

}
