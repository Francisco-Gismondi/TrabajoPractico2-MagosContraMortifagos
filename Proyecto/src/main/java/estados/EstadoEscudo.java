package estados;

import com.facultad.tp.Personaje;

public class EstadoEscudo implements EstadoPersonaje {

	private int puntosDeEscudo;

    public EstadoEscudo(int puntosDeEscudo) {
        this.puntosDeEscudo = puntosDeEscudo;
    }
    
	@Override
	public void inicioDeTurno(Personaje personaje) {
		// No hace nada
		
	}

	@Override
	public boolean puedeAtacar() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean recibirDanio(Personaje personaje, int cantidad) {
		
		if (cantidad >= this.puntosDeEscudo) {
			
            int dañoSobrante = cantidad - this.puntosDeEscudo;
            System.out.println("   ¡El escudo de " + personaje.getNombre() + " se ha roto!");
            personaje.restarVidaInterno(dañoSobrante);
            personaje.setEstado(new EstadoNormal()); // El escudo se rompió, vuelve a normal
            return true;
        } else {
            this.puntosDeEscudo -= cantidad;
            System.out.println("   El escudo de " + personaje.getNombre() + " absorbió el ataque. Escudo restante: " + this.puntosDeEscudo);
        }
		return false;
	}

}
