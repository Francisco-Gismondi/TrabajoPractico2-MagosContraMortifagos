package estados;

import com.facultad.tp.Personaje;

public class EstadoEvasion implements EstadoPersonaje {

	    @Override
	    public void inicioDeTurno(Personaje personaje) {
	    }

	    @Override
	    public boolean puedeAtacar() {
	        return true; 
	    }

	    @Override
	    public boolean recibirDanio(Personaje personaje, int cantidad) {
	        System.out.println("  + " + personaje.getNombre() + " evade el ataque por completo gracias a su invisibilidad.");   
	        personaje.setEstado(new EstadoNormal()); 
	        return false;
	    }

}
