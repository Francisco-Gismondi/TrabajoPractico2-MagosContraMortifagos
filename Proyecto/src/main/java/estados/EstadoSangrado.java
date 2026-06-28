package estados;

import com.facultad.tp.Personaje;

public class EstadoSangrado implements EstadoPersonaje {

	
	private int turnosRestantes;
    private int danioPorTurno;

    public EstadoSangrado(int turnosRestantes, int danioPorTurno) {
        this.turnosRestantes = turnosRestantes;
        this.danioPorTurno = danioPorTurno;
    }
    
	@Override
	public void inicioDeTurno(Personaje personaje) {
		
        if (this.turnosRestantes <= 0) {
            System.out.println("  + El efecto del sangrado en " + personaje.getNombre() + " se ha disipado.");
            // Solo vuelve a la normalidad si el sangrado no lo eliminó
            if (personaje.estaVivo()) {
                personaje.setEstado(new EstadoNormal());
            }
            else
            {
            	return;
            }
        }
        personaje.restarVidaInterno(this.danioPorTurno);
        System.out.println("  + " +personaje.getNombre() + " sufre " + this.danioPorTurno + " puntos de danio por sangrado.");    
        this.turnosRestantes--;
        
	}

	@Override
	public boolean puedeAtacar() {
		return false;
	}

	@Override
	public boolean recibirDanio(Personaje personaje, int cantidad) {
		personaje.restarVidaInterno(cantidad);		
		return true;
	}
	
	@Override
	public int potenciarDanio(Personaje personaje, int danioBase) {
		// TODO Auto-generated method stub
		return danioBase;
	}


}
