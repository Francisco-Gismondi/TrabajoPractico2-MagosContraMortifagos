package com.facultad.tp.hechizos;

import com.facultad.tp.Hechizo;
import com.facultad.tp.Personaje;

//ATAQUE OSCURO
public class Crucio implements Hechizo{

	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		int dañoBase = 20;
		int afinidad = lanzador.obtenerAfinidadOscura();
		if(afinidad == 0) {
			System.out.println(lanzador.getNombre() + " lanza Crucio sin verdadera intención de causar dolor.");
            objetivo.recibirDanio(5);
            return;
		}
		int danoFinal = 15 * afinidad;
        objetivo.recibirDanio(danoFinal);
        
        System.out.printlnS(lanzador.getNombre() + " tortura a " + objetivo.getNombre() + " con la maldición Cruciatus. Recibe " + danoFinal + " de daño y queda incapacitado.");
        
        //Aplicar estado de incapacitación/aturdimiento
        // objetivo.aplicarEstado(new EstadoAturdido(1));
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return "Crucio";
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
	

}
