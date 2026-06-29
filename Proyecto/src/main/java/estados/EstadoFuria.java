package estados;

import com.facultad.tp.Personaje;

public class EstadoFuria implements EstadoPersonaje{

	@Override
    public void inicioDeTurno(Personaje personaje) { }

    @Override
    public boolean puedeAtacar() { return true; }

    @Override
    public boolean recibirDanio(Personaje personaje, int cantidad) {
        System.out.println("  -> " + personaje.getNombre() + " ignora el dolor cegado por la ira.");
        int danioCegado = (int) (cantidad * 0.7); //Le hacen menos daño
        personaje.restarVidaInterno(danioCegado);
        return true;
    }

    @Override
    public int potenciarDanio(Personaje personaje, int danioBase) {
        int  danioPotenciado = (int) (danioBase *1.5);
    	System.out.println("  -> ¡" + personaje.getNombre() + " explota de bronca y da un golpe tremendo!");
        
        personaje.setEstado(new EstadoNormal()); 

        return danioPotenciado; 
    }

}
