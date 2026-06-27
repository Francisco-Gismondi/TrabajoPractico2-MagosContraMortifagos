package estados;

import com.facultad.tp.Personaje;

public class EstadoAturdido implements EstadoPersonaje {

	private int turnosRestantes;
	
	public EstadoAturdido(int turnosRestantes) {
        this.turnosRestantes = turnosRestantes;
    }
	
	@Override
	public void inicioDeTurno(Personaje personaje) {
		
		if (this.turnosRestantes <= 0) {
            System.out.println(personaje.getNombre() + " se ha recuperado del aturdimiento.");
            personaje.setEstado(new EstadoNormal()); // Vuelve a la normalidad
        }
		else {
			System.out.println(personaje.getNombre() + " esta aturdido. Turnos restantes: " + this.turnosRestantes);
		}
		this.turnosRestantes--;
			
	}

	@Override
	public boolean puedeAtacar() {
		return false;
	}

	@Override
	public void recibirDanio(Personaje personaje, int cantidad) {
		personaje.restarVidaInterno(cantidad);	
	}

}
