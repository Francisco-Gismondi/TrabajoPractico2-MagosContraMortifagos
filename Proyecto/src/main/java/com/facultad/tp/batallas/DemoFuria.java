package com.facultad.tp.batallas;

import java.util.*;

import com.facultad.tp.*;

import estados.EstadoNormal;

public class DemoFuria {

	public static void main(String[] args) {
		System.out.println("=== DEMO CONTROLADA: FURIA Y SOBRECARGA ===");

		Batallon mortifagos = new Batallon();

		Comandante jefe = new Comandante("Lord Voldemort", 100, 200, 100);
		Seguidor carnada = new Seguidor("Mortífago Herido", 20, 1, 100);
		Auror auror = new Auror("Alastor Moody", 90, 150, 100);

		mortifagos.agregarPersonaje(jefe);
		mortifagos.agregarPersonaje(carnada);

		Hechizo expelliarmus = FabricaHechizos.crearHechizo("expelliarmus");
		Hechizo avadaKedavra = FabricaHechizos.crearHechizo("avadaKedavra");

		System.out.println("\n--- INICIA EL COMBATE DIRECTO ---");

		auror.lanzarHechizo(expelliarmus, carnada);

		mortifagos.limpiarEliminados();		
		jefe.lanzarHechizo(avadaKedavra, auror);
		jefe.setEstado(new EstadoNormal());
		jefe.lanzarHechizo(avadaKedavra, auror);
		System.out.println("\n=== FIN DE LA DEMO ===");
	}
}
