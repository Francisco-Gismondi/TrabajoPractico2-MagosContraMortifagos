package com.facultad.tp;
import estados.*;
import java.util.List;

public abstract class Personaje {
    protected String nombre;
    protected int nivelMagia;
    protected int manaMaximo;
    protected int manaActual;
    protected int puntosVida;
    protected int puntosVidaMaximos;
    protected List<Hechizo> hechizos;
    protected EstadoPersonaje estadoActual;
    
    public Personaje(String nombre, int nivelMagia, int puntosVida, int mana) {
        this.nombre = nombre;
        this.nivelMagia = nivelMagia;
        this.puntosVida = puntosVida;
        this.puntosVidaMaximos = puntosVida;
        this.manaActual = mana;
        this.manaMaximo = mana;
        this.estadoActual = new EstadoNormal();
    }
    
    public void setEstado(EstadoPersonaje nuevoEstado) {
        this.estadoActual = nuevoEstado;
    }
    
    public void inicioDeTurno() {
    	this.estadoActual.inicioDeTurno(this);
    }
    
    public boolean puedeAtacar() {
        return this.estadoActual.puedeAtacar();
    }
    
    // Modificamos recibirDanio para que delegue la responsabilidad al estado actual
    
    public void recibirDanio(int danio) {
        this.estadoActual.recibirDanio(this, danio);
    }

    // Método interno que los estados llamarán para restar la vida
    
    public void restarVidaInterno(int danio) {
        this.puntosVida = Math.max(0, this.puntosVida - danio);
        if (this.puntosVida == 0) {
            System.out.println(this.nombre + " ha sido eliminado del combate.");
        }
    }
    
    
    public int calcularDanio(Hechizo hechizo, int danioBase) {
        return danioBase;
    }
    
    
    public int calcularCuracion(Hechizo hechizo, int curacionBase) {
        return curacionBase;
    }


    public void curar(int cantidad) {
        int vidaAnterior = puntosVida;
        puntosVida = Math.min(puntosVidaMaximos, puntosVida + cantidad);
        int curacionReal = puntosVida - vidaAnterior;
        System.out.println(nombre + " se ha curado " + curacionReal + " puntos de vida (PV: " + puntosVida + "/" + puntosVidaMaximos + ")");
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
