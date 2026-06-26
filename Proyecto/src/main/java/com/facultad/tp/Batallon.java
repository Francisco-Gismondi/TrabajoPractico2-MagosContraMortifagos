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

    public void atacar(Batallon otro) {
        inicializarRonda();

        List<Personaje> atacantes = new ArrayList<>(personajes);

        for (Personaje atacante : atacantes) {
            if (!atacante.estaVivo()) continue;

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
            hechizosPorPersonaje.get(atacante).add(hechizo);

            if (hechizo.esDefensa()) {
                hechizo.ejecutar(atacante, atacante);
            } else if (hechizo.esCuracion()) {
                Personaje objetivo = obtenerMasDebil();
                if (objetivo != null) {
                    hechizo.ejecutar(atacante, objetivo);
                }
            } else {
                List<Personaje> objetivosVivos = otro.getPersonajesVivos();
                if (objetivosVivos.isEmpty()) continue;
                Personaje objetivo = objetivosVivos.get(rand.nextInt(objetivosVivos.size()));
                hechizo.ejecutar(atacante, objetivo);
            }
        }

        limpiarEliminados(otro);
    }

    private boolean necesitaSerCurado(Personaje p) {
        return (double) p.getPuntosVida() / p.getPuntosVidaMaximos() < 0.7;
    }

    private boolean esHechizoValido(Hechizo h, boolean necesitaCuracion, boolean enemigosVivos) {
        if (h.esAtaque() && !enemigosVivos) return false;
        if (h.esCuracion() && !necesitaCuracion) return false;
        return true;
    }

    private void inicializarRonda() {
        for (Personaje p : personajes) {
            p.limpiarProteccion();
        }
    }

    private Personaje obtenerMasDebil() {
        return personajes.stream()
            .filter(Personaje::estaVivo)
            .min(Comparator.comparingDouble(p -> (double) p.getPuntosVida() / p.getPuntosVidaMaximos()))
            .orElse(null);
    }

    private void limpiarEliminados(Batallon otro) {
        personajes.removeIf(p -> !p.estaVivo());
        otro.personajes.removeIf(p -> !p.estaVivo());
        personajes.forEach(p -> hechizosPorPersonaje.get(p).clear());
        otro.personajes.forEach(p -> otro.hechizosPorPersonaje.get(p).clear());
    }

    public int tamaño() {
        return personajes.size();
    }

    public void mostrarEstado() {
        System.out.println("--- Estado del Batalion ---");
        if (personajes.isEmpty()) {
            System.out.println("(vacio)");
        } else {
            for (Personaje p : personajes) {
                System.out.println("  " + p);
            }
        }
    }
}
