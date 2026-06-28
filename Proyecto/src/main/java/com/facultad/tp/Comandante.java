package com.facultad.tp;

import com.facultad.tp.hechizos.*;

import estados.EstadoFuria;

import java.util.Arrays;

public class Comandante extends Mortifago {

    public Comandante(String nombre, int nivelMagia, int puntosVida, int mana) {
        super(nombre, nivelMagia, puntosVida,mana);
        this.hechizos = Arrays.asList(
        		new Sectumsempra(),
                new Protego(),
                new Crucio(),
                new Imperio(),
                new AvadaKedavra()
        );
    }
    
    @Override
    public void notificarCaidaAliado(Personaje aliadoCaido) {
        // Validación para que no se enfurezca por enemigos, solo aliados
        System.out.println("\n[!] " + this.nombre + " ve caer a " + aliadoCaido.getNombre() + " y entra en un estado de FURIA.");
        this.setEstado(new EstadoFuria());
    }
}
