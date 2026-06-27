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
	public void recibirDanio(Personaje personaje, int cantidad) {
		personaje.restarVidaInterno(cantidad); //Delega a la clase personaje
	}

}
