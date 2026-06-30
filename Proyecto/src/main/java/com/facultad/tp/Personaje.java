package com.facultad.tp;
import estados.*;
import java.util.List;
import java.util.ArrayList;

import com.facultad.tp.hechizos.SobrecargaMagica;
import com.facultad.tp.objetos.ObjetoMagico;

public abstract class Personaje {
    protected String nombre;
    protected int nivelMagia;
    protected int manaMaximo;
    protected int manaActual;
    protected int puntosVida;
    protected int puntosVidaMaximos;
    protected List<Hechizo> hechizos;
    protected EstadoPersonaje estadoActual;
    protected List<ObjetoMagico> inventario;
    protected List<ObjetoMagico> objetosEquipados;
    
    public Personaje(String nombre, int nivelMagia, int puntosVida, int mana) {
        this.inventario = new ArrayList<>();
        this.objetosEquipados = new ArrayList<>();
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
        for (ObjetoMagico obj : objetosEquipados) {
            obj.inicioDeTurno(this);
        }
    }
    
    public boolean puedeAtacar() {
        return this.estadoActual.puedeAtacar();
    }
    
    // Modificamos recibirDanio para que delegue la responsabilidad al estado actual
    
    public boolean recibirDanio(int danio) {
        return this.estadoActual.recibirDanio(this, danio);
    }

    // Método interno que los estados llamarán para restar la vida
    
    public void restarVidaInterno(int danio) {
        this.puntosVida = Math.max(0, this.puntosVida - danio);
        if (this.puntosVida == 0) {
            System.out.println("  ! " + this.nombre + " ha sido eliminado del combate.");
        }
    }
    
    public void lanzarHechizo(Hechizo hechizo, Personaje objetivo) {
        // 1. Verificamos si tiene maná suficiente
        if (this.manaActual >= hechizo.getCostoMana()) {
            this.manaActual -= hechizo.getCostoMana();
            
            hechizo.ejecutar(this, objetivo);
            
        } else {
            // 2. Si NO tiene maná, forzamos la sobrecarga mágica
            System.out.println("  * "+this.nombre + " intentó usar magia sin maná suficiente...");
            Hechizo castigo = new SobrecargaMagica();
            castigo.ejecutar(this, objetivo);
        }
    }
    public int calcularDanio(Hechizo hechizo, int danioBase) {
<<<<<<< Updated upstream
        return danioBase;
=======
        int danio = this.estadoActual.potenciarDanio(this, danioBase);
        for (ObjetoMagico obj : objetosEquipados) {
            danio = obj.modificarDanio(hechizo, danio);
        }
        return Math.max(0, danio);
>>>>>>> Stashed changes
    }
    
    
    public int calcularCuracion(Hechizo hechizo, int curacionBase) {
        return curacionBase;
    }


    public void curar(int cantidad) {
        int vidaAnterior = puntosVida;
        puntosVida = Math.min(puntosVidaMaximos, puntosVida + cantidad);
        int curacionReal = puntosVida - vidaAnterior;
        System.out.println("  + " + nombre + " se ha curado " + curacionReal + " puntos de vida (PV: " + puntosVida + "/" + puntosVidaMaximos + ")");
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
    
<<<<<<< Updated upstream
=======
    public EstadoPersonaje getEstadoActual() {
        return this.estadoActual;
    }
    
    
    
    public void agregarObjeto(ObjetoMagico obj) {
        inventario.add(obj);
    }

    public boolean equipar(ObjetoMagico obj) {
        if (!inventario.contains(obj)) return false;
        if (objetosEquipados.size() >= 3) return false;
        inventario.remove(obj);
        objetosEquipados.add(obj);
        obj.alEquipar(this);
        return true;
    }

    public boolean desequipar(ObjetoMagico obj) {
        if (!objetosEquipados.contains(obj)) return false;
        objetosEquipados.remove(obj);
        inventario.add(obj);
        obj.alDesequipar(this);
        return true;
    }

    public List<ObjetoMagico> getObjetosEquipados() {
        return objetosEquipados;
    }

    public List<ObjetoMagico> getInventario() {
        return inventario;
    }

    public boolean tieneObjetosEnInventario() {
        return !inventario.isEmpty();
    }

    public boolean tieneObjetosEquipados() {
        return !objetosEquipados.isEmpty();
    }

    public void desequiparTodos() {
        for (ObjetoMagico obj : new ArrayList<>(objetosEquipados)) {
            desequipar(obj);
        }
    }

    public int getManaActual() {
        return manaActual;
    }

    public int getManaMaximo() {
        return manaMaximo;
    }

    public void recuperarMana(int cantidad) {
        manaActual = Math.min(manaMaximo, manaActual + cantidad);
    }

>>>>>>> Stashed changes
    public abstract boolean puedeLanzarMagiaOscura();

    @Override
    public String toString() {
<<<<<<< Updated upstream
        return nombre + " (PV: " + puntosVida + "/" + puntosVidaMaximos + ", Magia: " + nivelMagia + ", Mana: " + manaActual + "/" + manaMaximo + ")";
=======
        return String.format("%s\t|PV: %d/%d,\tMagia: %d,\tMana: %d/%d|",
            nombre, puntosVida, puntosVidaMaximos, nivelMagia, manaActual, manaMaximo);
>>>>>>> Stashed changes
    }

    public String mostrarItems() {
        String eq = objetosEquipados.isEmpty() ? "" :
            objetosEquipados.stream().map(ObjetoMagico::getNombre).reduce((a, b) -> a + ", " + b).orElse("");
        String inv = inventario.isEmpty() ? "" :
            inventario.stream().map(ObjetoMagico::getNombre).reduce((a, b) -> a + ", " + b).orElse("");
        String res = "";
        if (!eq.isEmpty()) res += "eq:[" + eq + "]";
        if (!inv.isEmpty()) res += (res.isEmpty() ? "" : " ") + "inv:[" + inv + "]";
        return res;
    }
}
