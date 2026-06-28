package estados;

import com.facultad.tp.Personaje;

public class EstadoNormal implements EstadoPersonaje {

	@Override
	public void inicioDeTurno(Personaje personaje) {
		//No aplica nada
	}

	@Override
	public boolean puedeAtacar() {
	
		return true;
	}

	@Override
	public boolean recibirDanio(Personaje personaje, int cantidad) {
		System.out.println("  -> " + personaje.getNombre() + " recibe " + cantidad + " puntos de daño.");
        personaje.restarVidaInterno(cantidad);
        return true;
	}


}
