package estados;

import com.facultad.tp.Personaje;

public class EstadoEnvenenado implements EstadoPersonaje {

	
	private int turnosRestantes;
    private int danioPorTurno;

    public EstadoEnvenenado(int turnosRestantes, int danioPorTurno) {
        this.turnosRestantes = turnosRestantes;
        this.danioPorTurno = danioPorTurno;
    }
    
	@Override
	public void inicioDeTurno(Personaje personaje) {
		
        if (this.turnosRestantes <= 0) {
            System.out.println("El efecto del veneno en " + personaje.getNombre() + " se ha disipado.");
            // Solo vuelve a la normalidad si el veneno no lo eliminó
            if (personaje.estaVivo()) {
                personaje.setEstado(new EstadoNormal());
            }
            else
            {
            	return;
            }
        }
        personaje.restarVidaInterno(this.danioPorTurno);
        System.out.println(personaje.getNombre() + " sufre " + this.danioPorTurno + " puntos de danio por envenenamiento.");    
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
