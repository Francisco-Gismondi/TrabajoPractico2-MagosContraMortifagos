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
        // 1. Aplicamos venenos, curaciones continuas, etc.
        this.procesarInicioDeRonda(); 

        // Guardamos los hechizos que el batallon uso este turno (al principio esta vacia)
        Set<String> nombresHechizosUsados = new HashSet<>();

        List<Personaje> atacantes = new ArrayList<>(personajes);

        for (Personaje atacante : atacantes) {
            // 2. Validamos vida y EL ESTADO (Aturdido, etc.)
            if (!atacante.estaVivo() || !atacante.puedeAtacar()) continue;

            // 2b. Equipamiento automático durante la batalla
            if (atacante.tieneObjetosEnInventario()) {
                if (atacante.getObjetosEquipados().isEmpty()) {
                    ObjetoMagico obj = atacante.getInventario().get(0);
                    System.out.println("  ~ " + atacante.getNombre() + " equipa " + obj.getNombre());
                    atacante.equipar(obj);
                }
            }

            // Filtramos hechizos que NO hayan sido usados por NADIE del batallón en este turno
            List<Hechizo> hechizosDisponibles = atacante.getHechizos().stream()
                .filter(h -> !nombresHechizosUsados.contains(h.getNombre()))
                .collect(Collectors.toList());

            if (hechizosDisponibles.isEmpty()) continue; // Se quedó sin opciones, pasa al siguiente mago

            boolean necesitaCuracion = necesitaSerCurado(atacante);
            boolean enemigosVivos = !otro.getPersonajesVivos().isEmpty();

            List<Hechizo> hechizosValidos = hechizosDisponibles.stream()
                .filter(h -> esHechizoValido(h, necesitaCuracion, enemigosVivos))
                .collect(Collectors.toList());

            if (hechizosValidos.isEmpty()) continue; // No tiene hechizos tácticamente válidos, pasa

            Hechizo hechizo = hechizosValidos.get(rand.nextInt(hechizosValidos.size()));
            
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