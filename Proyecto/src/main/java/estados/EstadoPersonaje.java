package estados;
import com.facultad.tp.*;

public interface EstadoPersonaje {
	
	// Se ejecuta apenas le toca el turno al personaje (aplica sangrados, curas, o reduce contadores)
    void inicioDeTurno(Personaje personaje);
    
    // El motor de batalla preguntará esto antes de dejarlo lanzar un hechizo
    boolean puedeAtacar();
    
    // Define qué pasa cuando alguien le hace daño
    boolean recibirDanio(Personaje personaje, int cantidad);
    
    int potenciarDanio(Personaje personaje, int danioBase);
}
