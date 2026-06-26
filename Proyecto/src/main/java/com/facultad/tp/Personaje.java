package com.facultad.tp;

import java.util.List;

public abstract class Personaje {
    protected String nombre;
    protected int nivelMagia;
    protected int puntosVida;
    protected int puntosVidaMaximos;
    protected List<Hechizo> hechizos;
    protected boolean protegido;

    public Personaje(String nombre, int nivelMagia, int puntosVida) {
        this.nombre = nombre;
        this.nivelMagia = nivelMagia;
        this.puntosVida = puntosVida;
        this.puntosVidaMaximos = puntosVida;
        this.protegido = false;
    }

    public int calcularDanio(Hechizo hechizo, int danioBase) {
        return danioBase;
    }

    public int calcularCuracion(Hechizo hechizo, int curacionBase) {
        return curacionBase;
    }

    public void recibirDanio(int danio) {
        if (protegido) {
            danio = danio / 2;
            protegido = false;
            System.out.println(nombre + " esta protegido por Protego, el danio se reduce a " + danio);
        }
        puntosVida = Math.max(0, puntosVida - danio);
        if (puntosVida == 0) {
            System.out.println(nombre + " ha sido eliminado del combate.");
        }
    }

    public void curar(int cantidad) {
        int vidaAnterior = puntosVida;
        puntosVida = Math.min(puntosVidaMaximos, puntosVida + cantidad);
        int curacionReal = puntosVida - vidaAnterior;
        System.out.println(nombre + " se ha curado " + curacionReal + " puntos de vida (PV: " + puntosVida + "/" + puntosVidaMaximos + ")");
    }

    public void proteger() {
        this.protegido = true;
    }

    public void limpiarProteccion() {
        this.protegido = false;
    }

    public boolean estaProtegido() {
        return protegido;
    }

    public boolean estaVivo() {
        return puntosVida > 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNivelMagia() {
        return nivelMagia;
    }

    public int getPuntosVida() {
        return puntosVida;
    }

    public int getPuntosVidaMaximos() {
        return puntosVidaMaximos;
    }

    public List<Hechizo> getHechizos() {
        return hechizos;
    }

    @Override
    public String toString() {
        return nombre + " (PV: " + puntosVida + "/" + puntosVidaMaximos + ", Magia: " + nivelMagia + ")";
    }
}
