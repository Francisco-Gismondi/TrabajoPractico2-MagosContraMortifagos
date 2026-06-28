package com.facultad.tp;

import java.util.*;
import java.util.stream.Collectors;
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
<<<<<<< Updated upstream
        inicializarRonda();
=======
        // 1. Aplicamos venenos, curaciones continuas, etc.
        this.procesarInicioDeRonda(); 
>>>>>>> Stashed changes

        List<Personaje> atacantes = new ArrayList<>(personajes);

        for (Personaje atacante : atacantes) {
            // 2. Validamos vida y EL ESTADO (Aturdido, etc.)
            if (!atacante.estaVivo() || !atacante.puedeAtacar()) continue;

            // Filtramos hechizos no repetidos en la ronda (usando el Set)
            List<Hechizo> hechizosDisponibles = atacante.getHechizos().stream()
                .filter(h -> !hechizosPorPersonaje.get(atacante).contains(h))
                .collect(Collectors.toList());

            if (hechizosDisponibles.isEmpty()) continue;

            boolean necesitaCuracion = necesitaSerCurado(atacante);
            boolean enemigosVivos = !otro.getPersonajesVivos().isEmpty();

            List<Hechizo> hechizosValidos = hechizosDisponibles.stream()
                .filter(h -> esHechizoValido(h, necesitaCuracion, enemigosVivos))
                .collect(Collectors.toList());

            if (hechizosValidos.isEmpty()) continue;

            Hechizo hechizo = hechizosValidos.get(rand.nextInt(hechizosValidos.size()));
            
            // Agregamos al Set para que no lo repita en el futuro
            hechizosPorPersonaje.get(atacante).add(hechizo); 

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
<<<<<<< Updated upstream

=======
    
>>>>>>> Stashed changes
    private boolean necesitaSerCurado(Personaje p) {
        return (double) p.getPuntosVida() / p.getPuntosVidaMaximos() < 0.7;
    }

    private boolean esHechizoValido(Hechizo h, boolean necesitaCuracion, boolean enemigosVivos) {
        if (h.esAtaque() && !enemigosVivos) return false;
        if (h.esCuracion() && !necesitaCuracion) return false;
        return true;
    }

    private Personaje obtenerMasDebil() {
        return personajes.stream()
            .filter(Personaje::estaVivo)
            .min(Comparator.comparingDouble(p -> (double) p.getPuntosVida() / p.getPuntosVidaMaximos()))
            .orElse(null);
    }
    ///REVISAR ESTA PARTE
    // Refactorizado para respetar encapsulamiento y evitar Memory Leaks
    public void limpiarEliminados() {
        Iterator<Personaje> iterator = personajes.iterator();
        while (iterator.hasNext()) {
            Personaje p = iterator.next();
            if (!p.estaVivo()) {
                // Lo borramos del Map para liberar memoria
                hechizosPorPersonaje.remove(p); 
                // Lo borramos de la Lista
                iterator.remove(); 
            } else {
                // Si está vivo, solo le vaciamos el Set de hechizos usados para la próxima ronda
                hechizosPorPersonaje.get(p).clear();
            }
        }
    }

    public int tamaño() {
        return personajes.size();
    }

    public void mostrarEstado() {
<<<<<<< Updated upstream
        System.out.println("--- Estado del Batalion ---");
=======
>>>>>>> Stashed changes
        if (personajes.isEmpty()) {
            System.out.println("(vacio)");
        } else {
            for (Personaje p : personajes) {
                System.out.println("  " + p);
            }
        }
    }
}