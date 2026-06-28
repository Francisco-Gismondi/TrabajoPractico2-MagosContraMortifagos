package estados;

import com.facultad.tp.Personaje;

public class EstadoRegeneracion implements EstadoPersonaje {

	private int turnosRestantes;
    private int curacionPorTurno;

    public EstadoRegeneracion(int turnosRestantes, int curacionPorTurno) {
        this.turnosRestantes = turnosRestantes;
        this.curacionPorTurno = curacionPorTurno;
    }
    
	@Override
	public void inicioDeTurno(Personaje personaje) {
		personaje.curar(this.curacionPorTurno);
        System.out.println("  + " + personaje.getNombre() + " regenera " + curacionPorTurno + " puntos de vida.");

        if (this.turnosRestantes <= 0) {
            personaje.setEstado(new EstadoNormal());
        }
        this.turnosRestantes--;
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

	@Override
	public int potenciarDanio(Personaje personaje, int danioBase) {
		// TODO Auto-generated method stub
		return danioBase;
	}



}
