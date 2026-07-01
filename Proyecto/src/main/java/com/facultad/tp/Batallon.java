package com.facultad.tp;

import java.util.*;
import java.util.stream.Collectors;

import com.facultad.tp.objetos.ObjetoMagico;
public class Batallon {

    private List<Personaje> personajes;
    private Map<Personaje, Set<Hechizo>> hechizosPorPersonaje;
    private Random rand;

    public Batallon() {
        this.personajes = new ArrayList<>();
        this.hechizosPorPersonaje = new HashMap<>();
        this.rand = new Random();
    }

    public void agregarPersonaje(Personaje p) {
        personajes.add(p);
        hechizosPorPersonaje.put(p, new HashSet<>());
    }

    public boolean tienePersonajesSaludables() {
        return personajes.stream().anyMatch(Personaje::estaVivo);
    }

    public List<Personaje> getPersonajesVivos() {
        return personajes.stream()
            .filter(Personaje::estaVivo)
            .collect(Collectors.toList());
    }

    // Nuevo método para procesar los estados al inicio de la ronda de este batallón
    public void procesarInicioDeRonda() {
        for (Personaje p : personajes) {
            if (p.estaVivo()) {
                p.inicioDeTurno();
            }
        }
    }

    public void atacar(Batallon otro) {
        // Aplicamos venenos, curaciones continuas, etc.
        this.procesarInicioDeRonda(); 

        // Guardamos los hechizos que el batallon uso este turno (al principio esta vacia) punto 6 del tp
        Set<String> nombresHechizosUsados = new HashSet<>();

        List<Personaje> atacantes = new ArrayList<>(personajes);

        for (Personaje atacante : atacantes) {
            // Validamos vida y EL ESTADO de uno de los atacantes6(Aturdido, etc.)
            if (!atacante.estaVivo() || !atacante.puedeAtacar()) continue;

            // Equipamiento automático durante la batalla
            if (atacante.tieneObjetosEnInventario()) {
                if (atacante.getObjetosEquipados().isEmpty()) {
                    ObjetoMagico obj = atacante.getInventario().get(0);
                    System.out.println("  ~ " + atacante.getNombre() + " equipa " + obj.getNombre());
                    atacante.equipar(obj);
                }
            }

            // Filtramos hechizos que NO hayan sido usados por NADIE del batallón en este turno
            List<Hechizo> hechizosDisponibles = new ArrayList<>();
            for (Hechizo h : atacante.getHechizos()) {
                if (!nombresHechizosUsados.contains(h.getNombre())) {
                    hechizosDisponibles.add(h);
                }
            }

            if (hechizosDisponibles.isEmpty()) continue; // Se quedó sin opciones, pasa al siguiente mago

            boolean necesitaCuracion = necesitaSerCurado(atacante);			//se fija si esta herido
            boolean enemigosVivos = !otro.getPersonajesVivos().isEmpty();

            List<Hechizo> hechizosValidos = new ArrayList<>();  //
            for (Hechizo h : hechizosDisponibles) { //hechizos que tiene el personaje + curacion(si hay personaje herido) + los de daño
                if (esHechizoValido(h, necesitaCuracion, enemigosVivos)) {
                    hechizosValidos.add(h);
                }
            }

            if (hechizosValidos.isEmpty()) continue; // No tiene hechizos válidos, pasa

            Hechizo hechizo = hechizosValidos.get(rand.nextInt(hechizosValidos.size())); //elije un hechizo al azar
            
            // Bloqueamos el NOMBRE del hechizo para que ningún compañero lo repita
            nombresHechizosUsados.add(hechizo.getNombre()); 

            // 3. Ejecutamos pasando por el control de MANÁ
            if (hechizo.esDefensa()) {
                atacante.lanzarHechizo(hechizo, atacante);
            } else if (hechizo.esCuracion()) {
                Personaje objetivo = obtenerMasDebil();
                if (objetivo != null) {
                    atacante.lanzarHechizo(hechizo, objetivo);
                }
            } else {
                List<Personaje> objetivosVivos = otro.getPersonajesVivos();
                if (objetivosVivos.isEmpty()) continue;
                Personaje objetivo = objetivosVivos.get(rand.nextInt(objetivosVivos.size()));
                atacante.lanzarHechizo(hechizo, objetivo);
            }
        }

        // 4. Cada batallón se limpia a sí mismo
        this.limpiarEliminados();
        otro.limpiarEliminados();
    }
    
    //Devuelve true si el personaje tiene menos del 70% de vida 
    private boolean necesitaSerCurado(Personaje p) {
        return (double) p.getPuntosVida() / p.getPuntosVidaMaximos() < 0.7;
    }

    private boolean esHechizoValido(Hechizo h, boolean necesitaCuracion, boolean enemigosVivos) {
        if (h.esAtaque() && !enemigosVivos) return false; //si hay un enemigo vivo y el hechizo es de daño entonces es valido
        if (h.esCuracion() && !necesitaCuracion) return false; //si algun aliado tiene poca vida y el hechizo es de curacion entonces es valido
        return true;
    }

    private Personaje obtenerMasDebil() {
        return personajes.stream()
            .filter(Personaje::estaVivo)
            .min(Comparator.comparingDouble(p -> (double) p.getPuntosVida() / p.getPuntosVidaMaximos()))
            .orElse(null);
    }

    public void limpiarEliminados() {
        List<Personaje> caidosEnEstaRonda = new ArrayList<>();
        Iterator<Personaje> iterator = personajes.iterator();
        
        while (iterator.hasNext()) {
            Personaje p = iterator.next();
            if (!p.estaVivo()) {
                caidosEnEstaRonda.add(p); // Lo guardamos temporalmente
                hechizosPorPersonaje.remove(p); 
                iterator.remove(); 
            } else {
                hechizosPorPersonaje.get(p).clear();
            }
        }

        // Notificamos a los sobrevivientes del mismo batallón
        for (Personaje caido : caidosEnEstaRonda) {
            for (Personaje vivo : personajes) {
                vivo.notificarCaidaAliado(caido);
            }
        }
    }

    public int tamaño() {
        return personajes.size();
    }

    public void mostrarEstado() {
        if (personajes.isEmpty()) {
            System.out.println("   (vacio)");
        } else {
            for (Personaje p : personajes) {
                String items = p.mostrarItems();
                if (!items.isEmpty()) {
                    System.out.println("  " + p + "  " + items);
                } else {
                    System.out.println("  " + p);
                }
            }
        }
    }
}