package com.facultad.tp.batallas;

import java.util.Random;
import java.util.Scanner;

import com.facultad.tp.Batallon;
import com.facultad.tp.Reclutador;

public class BatallaMagosVsMortifagos {

    public static void main(String[] args) {
    	Scanner teclado = new Scanner(System.in);
    	System.out.println("======================================================================");
        System.out.println("                     BATALLA: MAGOS vs MORTIFAGOS");
        System.out.println("======================================================================");

        Batallon batallonMagos = new Batallon();
        Batallon batallonMortifagos = new Batallon();

        for (int i = 0; i < 3; i++) {
            batallonMagos.agregarPersonaje(Reclutador.crearMago());
            batallonMortifagos.agregarPersonaje(Reclutador.crearMortifago());
        }

        System.out.println("\n[ BATALLONES INICIALES ]");
        System.out.println("Magos:");
        batallonMagos.mostrarEstado();
        System.out.println("\nMortifagos:");
        batallonMortifagos.mostrarEstado();

        Random rand = new Random();
        int ronda = 0;
        
        System.out.println("\n¿Te gustan estos batallones? Ejecuta el juego nuevamente para cambiarlos");
        System.out.println("\nPresiona enter para comenzar la pelea...");
        teclado.nextLine();

        while (batallonMagos.tienePersonajesSaludables() && batallonMortifagos.tienePersonajesSaludables()) {
            ronda++;
            System.out.println("======================================================================");
            System.out.println("                                RONDA " + ronda + "                                ");
            System.out.println("======================================================================");

            if (rand.nextBoolean()) {
            	System.out.println("\n> TURNO DE LOS MAGOS");
                System.out.println("----------------------------------------------------------------------");
                batallonMagos.atacar(batallonMortifagos);
                if (batallonMortifagos.tienePersonajesSaludables()) {
                	System.out.println("\n> TURNO DE LOS MORTIFAGOS");
                	System.out.println("----------------------------------------------------------------------");
                    batallonMortifagos.atacar(batallonMagos);
                }
            } else {
            	System.out.println("\n> TURNO DE LOS MORTIFAGOS");
            	System.out.println("----------------------------------------------------------------------");
                batallonMortifagos.atacar(batallonMagos);
                if (batallonMagos.tienePersonajesSaludables()) {
                	System.out.println("\n> TURNO DE LOS MAGOS");
                	System.out.println("----------------------------------------------------------------------");
                    batallonMagos.atacar(batallonMortifagos);
                }
            }

            System.out.println("\n---------------------------- Estado actual ---------------------------");
            System.out.println("Magos (" + batallonMagos.tamaño() + "):");
            batallonMagos.mostrarEstado();
            System.out.println("Mortifagos (" + batallonMortifagos.tamaño() + "):");
            batallonMortifagos.mostrarEstado();
            System.out.println("----------------------------------------------------------------------");
            
            //System.out.print("Presiona enter para pasar la ronda... ");
            //teclado.nextLine();
            
            if (ronda >= 50) {
                System.out.println("\nLa batalla ha llegado a su limite de rondas.");
                break;
            }
        }

        System.out.println("======================================================================");
        System.out.println("                           RESULTADO FINAL");
        if (batallonMagos.tienePersonajesSaludables()) {
            System.out.println("                  ¡Los magos han ganado la batalla!");
            imprimirEscudoHogwarts();
        } else if (batallonMortifagos.tienePersonajesSaludables()) {
            System.out.println("                  ¡Los mortifagos han ganado la batalla!");
            imprimirVoldemort();
        } else {
            System.out.println("                  ¡Empate! Todos han caido.");
        }
        System.out.println("======================================================================");
    }
    
    public static void imprimirVoldemort() {
        String art = """
                                        ⣤⠶⠟⠛⠛⠛⠶⢦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                      ⢠⡾⠋⠀⠀⠀⠀⠀⠀⠀⠀⠉⢷⡀⠀⠀⠀⠀⠀⠀⠀⠀
                                     ⢠⣿⠁⠀⠀⡀⠀⠀⠀⠀⠀⠀⠀⣾⣷⠀⠀⠀⠀⠀⠀⠀⠀
                                     ⣾⠟⢉⣀⣀⡈⠃⠀⠀⠒⣉⣀⡀⠈⢻⡄⠀⠀⠀⠀⠀⠀⠀
                                     ⡏⠀⣴⣶⣿⣿⣷⠂⠀⣾⣿⣿⣿⣆⢠⡇⠀⠀⠀⠀⠀⠀⠀
                                    ⢸⣿⠀⠻⠿⢿⡿⠃⣰⣆⠙⣿⡿⠿⠋⠸⣧⠀⠀⠀⠀⠀⠀⠀
                                    ⣿⠁⠀⠀⠀⠈⠀⠠⣿⠿⠀⠀⣀⣀⣀⠀⣿⣦⠀⠀⠀⠀⠀⠀
                                   ⣼⣿⣶⣾⣿⣿⡟⢀⠀⠀⠀⢀⢀⢻⣿⣿⣿⣿⢻⡇⠀⠀⠀⠀⠀
                                   ⣇⢸⡇⠻⢿⣿⠇⡜⢸⠀⡇⢸⠘⣼⣿⠿⠉⠙⣿⡇⠀⠀⠀⠀⠀
                                   ⣿⣾⣿⣦⠀⣿⣷⣷⣾⣤⣷⣾⣿⣿⢋⣴⣶⣶⣿⡀⠀⠀⠀⠀⠀
                                 ⣠⠞⠙⠻⣿⣿⣷⣿⣿⣿⠿⠿⠻⠟⠋⠀⣼⣿⣿⡿⣻⣿⣦⡀⠀⠀⠀
                       ⠀       ⢀⡼⢿⡦⣠⣴⢿⣿⣿⣿⣏⣿⣤⠀⠀⢰⡔⣶⣾⣿⠏⠀⠀⠀⣿⣿⣄⠀⠀
                              ⠀⡾⢡⣏⢰⣯⠃⣼⣿⡿⢿⡟⢿⡋⠀⠀⢸⣽⣿⣿⡿⣇⠀⠀⠀⢈⡿⡿⣆⠀
                             ⢸⡿⠿⠏⢰⠇⢸⡿⠋⠀⠀⣿⡟⠀⠀⠀ ⠈⡟⠀⠉⠳⣿⣿⠆⠀⢿⣙⡇⣿⡄
                             ⣿⠀⠀⠀⢸⢰⣿⠁⠀⠀⠀⢸⣧⣤⠀ ⠀⡼⡇⠀⠀⠀⢹⣾⠀⠀⠈⢻⡇ ⢹⡇
                             ⣿⣿⣷⠀⢸⣸⣿⠀⠀⠀⠀⠀⢷⣿⠀⠀ ⠳⡇⠀⠀⠀⢸⡟⠀⠀⠀⡸⠀ ⢼⡇
                             ⢿⣟⠋⠀⢸⡿⣿⠀⠀⠀⠀⠀⠘⣿⢲⣦⠀ ⢹⡀⠀⠀⡼⠀⣤⣤⣴⠃⢠⣾⠇
                             ⠸⡏⣿⠆⠘⢷⣼⣷⣄⠀⠀⠀⠀⠹⣿⡇⠀ ⠘⢵⣤⡾⢁⡤⣡⠞⠁⣸⠟⠀
                             ⠀⠹⣧⠀⢀⣀⠀⠸⣯⣽⣷⣦⣄⡀⢻⣷⣦⣄⣤⠙⣷⡼⠞⠁⣀⢄⣾⠏⠀⠀
                             ⠀⠀⠈⠻⣟⠙⢧⣀⣀⠀⠘⢳⣾⣿⣿⣿⣿⣮⡻⣤⡌⠛⢶⣵⣵⡿⠁⠀⠀⠀
                             ⠀⠀⠀⠀⠈⠳⢶⣤⣿⣦⠖⡉⠕⠊⢉⣿⣿⣿⣷⣾⣧⣖⣦⠙⢿⣄⠀⠀⠀⠀
                             ⠀⠀⠀⠀⠀⠀⠀⣸⠟⠠⠈⠀⢀⣰⣿⣿⣿⣿⣿⣟⢿⣿⣿⣠⡴⢮⢳⣄⠀⠀
                             ⠀⠀⠀⠀⠀⠀⣼⡫⠂⠀⠀⠀⣶⡿⣿⣿⣿⠁⠘⢿⣆⠙⣿⣿⣅⢺⡇⠛⢷⡄
                       ⠀⠀⠀⠀      ⠀⢰⣿⠁⠀⠀⠀⣼⠏⠀⠈⢿⣿⠀⠀⢀⣿⠀⢸⣿⢿⣿⣷⢺⡆⣿
                       ⠀⠀      ⠀⠀⠀⢸⡇⠀⠀⠀⢰⣿⠀⠀⠀⠈⣏⢻⡇⢷⣾⡇⢸⣿⠃⠘⢿⣼⣧⣿
                       ⠀⠀      ⠀⠀⠀⢸⣇⠀⠀⠀⢉⢿⣆⠀⠀⠀⣿⡟⠀⠀⢹⣷⣿⡏⠀⠀⢽⣿⣿⠏
                       ⠀⠀      ⠀⠀⠀⠀⢻⡆⠀⠀⠀⠸⢻⢿⣶⣶⣿⢻⡆⢀⣼⣿⠏⠀⠀⣠⣹⣿⠋⠀
                       ⠀⠀     ⠀⠀⠀⠀⠀⠻⣷⣀⣀⠀⠈⠈⠘⣿⡇⡿⠃⣸⢸⣿⢀⣠⣦⡿⠛⠁⠀⠀
                       ⠀⠀⠀⠀⠀⠀⠀     ⠀⠀⠙⠳⠶⣤⣴⣾⣿⣠⡇⠐⣇⣿⣿⠿⠛⠉⠀⠀⠀⠀⠀
                       ⠀⠀⠀⠀⠀⠀⠀   ⠀ ⠀⠀⠀⠀⠀⠀⢉⣿⠁⠀⠀⣹⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀
                       ⠀⠀⠀⠀⠀⠀⠀   ⠀ ⠀⠀⠀⠀⠀⠀⣼⣧⡀⠀⣀⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                       ⠀⠀⠀⠀⠀⠀⠀   ⠀ ⠀⠀⠀⠀⠀⣰⢧⡟⠁⣾⢻⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                          ⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⠀⠀⢠⣿⠞⠃⢠⣆⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                          ⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⠀⠀⣾⠃⠀⠀⠀⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                          ⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⠀⣸⣿⡄⠀⡀⣸⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                          ⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⢀⡏⣼⠋⣸⢹⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                          ⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⣸⣷⠿⠀⢫⣾⠁⠀⠀⠀⡠⠚⢉⣉⠓⣦⠀⠀⠀⠀
                          ⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⣿⠀⠀⠀⢰⡟⠀⠀⢀⣼⢱⣿⣾⣿⣷⣼⡄⠀⠀⠀
                          ⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⢀⣿⣆⠀⢠⣾⠇⠀⣼⡥⢃⣾⣿⣿⣿⡟⠈⠻⠀⠀⠀
                          ⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⠸⡇⡿⠂⢸⣸⠀⢰⣏⠔⠛⠛⢻⣿⣿⣧⢠⡄⠀⠀⠀
                          ⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⣷⡇⠀⠘⢿⡆⠸⣇⠀⠀⠀⠀⠈⠉⠉⣩⠇⠀⠀⠀
                          ⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⢹⣷⣦⡄⠸⣷⡀⢹⣷⠄⠀⠀⢠⣤⠾⠃⠀⠀⠀⠀
                          ⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⠀⢻⡍⠳⠤⠾⠿⠛⠁⠀⠀⣨⡿⠁⠀⠀⠀⠀⠀⠀
                       ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀     ⠻⣄⠀⠀⠀⠀⢀⠀⢤⡾⠁⠀⠀⠀⠀⠀⠀⠀
                       ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀    ⠈⠓⠦⣴⣴⣶⠶⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀
        """;
        System.out.println(art);
    }
    public static void imprimirEscudoHogwarts() {
        String art = """
                                ⣀⡀⠠⠤⠀⠐⠒⠂⢀⢈⣉⡉⠉⣉⠉⠉⣉⡉⠉⠀⠐⠒⠂⠀⠤⠄⢀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                         ⣀⠤⠐⠂⠈⣡⡤⠤⣤⡀⢠⣾⠛⠉⠙⠀⢹⡇⢠⣿⡄⢀⣽⠃⣸⠟⢿⡀⢹⣿⠛⣶⡄ ⢈⣁⣐⠂⠤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⡀⠄⢂⣡⠄⠐⣿⠀⢸⣿⠀⠀ ⢹⣷⣿⡀⠈⣻⠇⠀⣿⡟⠀⢻⣾⠃⣰⡿⠷⠿⣧⠀⢸⡿⢾⡟⠁⠉⢹⣿⠛⠇⣤⠬⣔⡠⢀⠀⠀⠀⠀⠀
                 ⠀⠀⠀⠀⠌⠀⠀⠈⢻⣦⡶⠻⣧⠘⢿⣦⣀⣼⠟⠀⠙⠻⠶⠛⠀⠀⠉⠀⠀⠀⠃⠘⠛⠃⠀⠀⠛⠃⠾⡇⠈⢿⡄⠀⣸⡇⠀⡀⢿⣦⡘⠃⠀⠡⠀⠀⠀⠀
                 ⠀⠀⠀⠀⡨⠂⠀⠀⠀⢿⣆⠀⠿⠓⠀⠉⢉⣁⠀⠤⠄⠀⠒⠒⠀⠀⠈⠉⠉⠉⠉⠁⠀⠀⠒⠒⠀⠠⠤⠀⣀⡀⠻⠈⠉⠃⠚⢷⣤⣿⠇⠀⠐⢅⠀⠀⠀⠀
                 ⠀⠀⠔⠁⠀⠀⠑⢄⠀⢉⡀⠤⠐⠂⠈⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠁⠐⠂⠤⢀⡀⠀⡠⠊⠀⠀⠀⠢⠀⠀
                 ⢀⠊⣀⣀⣀⠀⠀⠀⠙⠒⠒⢒⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⡒⠒⠐⠋⠀⠀⠀⣀⣀⣀⠑⡀
                 ⠉⠀⠀⠀⢰⠀⠀⠀⣀⠄⠂⠁⠀⠀⡠⠊⠑⠒⠢⢤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡤⠔⠒⠂⠑⢄⠀⠀⠈⠐⠠⣀⠀⠀⠀⡆⠀⠀⠀⠉
                 ⠀⠀⠀⠀⠀⢀⠄⠊⠀⠀⠀⠀⣠⠎⠀⠀⠀⠀⠀ ⠀⠈⠑⠦⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠴⠊⠁⠀⠀⠀⠀⠀⠀⠱⣄⠀⠀⠀⠀⠑⠠⡀⠀⠀⠀⠀⠀
                 ⠀⠀⠀⠀⠐⠁⠀⠀⠀⠀⢀⠞⠁⠀⠀⢀⣶⣶⣦⠀⠀⠀⠀ ⠈⠑⠂⠠⡀⢀⠄⠐⠊⠁⣠⣴⣶⣶⣄⠀⠀⠀⠀⠀⠈⠣⡀⠀⠀⠀⠀⠈⠂⠀⠀⠀⠀
                 ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠧⢄⡀⠀⠀⠘⣿⡇⣸⠀⣠⣶⣿⣿⣦⡀⠀⠀⠙⠃⠀⢀⣴⣿⣿⠛⠉⠉⣿⣿⡆⠀⠀⠀⠀⢀⡠⠼⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀
                 ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠲⣄⠀⠙⢷⡿⠀⣿⣿⣿⣿⣿⣿⠂⠀⠀⠀⢀⠞⠛⠉⠁⠀⢀⣴⣿⡿⠁⠀⠀⣠⠖⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                 ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢆⠀⣸⠇⠠⣿⣿⣿⣿⡏⠁⣠⣤⣤⠀⡎⠀⢀⣤⣶⣾⣿⠿⠋⠀⠀⠀⡰⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                 ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⣿⠀⢰⣿⣿⣿⣿⣿⠿⠟⠀⠀⠈⠀⢠⣿⡿⠋⠉⠀⠀⠀⠀⠀ ⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                 ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠘⣷⣿⣿⣿⣿⡿⠛⠳⠦⢤⣀⣀⡀⠿⠟⠓⢄⠀⠀⣠⣶⣿⣿⣷⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                 ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⠀⠀⢹⣿⣿⣯⠘⡄⠲⣶⣶⠖⠀⠠⣶⣦⡶⠂⢠⣿⣿⣿⣿⣿⣿⠟⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                 ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠇⠀⣶⠿⠛⣿⡇⠀⢃⠀⢸⣿⡀⠀⠀⢸⣿⡇⠀⡜⠙⢿⣿⣿⣿⣿⠀⠸⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                 ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡾⠤⠤⠿⠷⠤⠼⠷⠤⢼⠀⢸⣿⠟⠛⠛⢻⣿⠁⠀⡧⠤⠤⠤⠿⠯⠤⠤⠤⢷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                 ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠞⠀⠀⣠⣤⣤⣀⣀⠀⠀⡘⠀⠈⣿⠀⠀⠀ ⢸⣿⠀⢳⠶⣾⣿⣷⡄⠀ ⢠⣾⣿⣧⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                 ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⢊⠀⠀⢿⣿⣿⣿⣿⣿⠟⠡⡁⠒⠛⠒⠀⠀ ⠾⠛⠒⠀⢈⣄⣽⣿⣿⣧⣀⣸⣿⣿⣿⣷⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                 ⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⠋⠀⠸⣷⡀⢸⣿⣿⣿⣧⣀⠀⠀⠑⠄⠤⠄⠐⠒⠒⠂⢠⣤⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠙⠦⡀⠀⠀⠀⠀⠀⠀⠀⠀
                 ⠀⠀⠀⠀⠀⠀⠀⣔⠁⠀⠀⠀⠀⢻⣿⣿⣿⣿⣿⣿⣿⣿⣶⣤⡀⠀⠀⠀⠀⠀⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀ ⠈⣢⠀⠀⠀⠀⠀⠀⠀
                 ⠀⠀⠀⠀⠀⠀⠀⠈⠢⡀⠀⠀⠀⠀⠉⣹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⢀⠔⠁⠀⠀⠀⠀⠀⠀⠀
                 ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢄⠀⠀⢀⣾⡿⠋⠀⠀⢹⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⢹⣿⣿⣿⣿⣿⣿⣿⣿⠇⠈⠉⠻⢿⣿⡇⡠⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀
                 ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠳⡄⠈⠁⢀⡀⠿⠷⠿⠿⠛⣿⣿⠿⣿⡄⠀⠀⠀⠀⢸⣿⣿⣿⠹⣿⠟⠛⣇⠀⢀⡀⠀⠀⢩⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀
                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢦⡴⠋⠙⠦⡀⠀⣴⡾⠿⠟⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠁⠀⣿⣀⠀⣿⠿⠋⠙⢦⡔⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠑⠢⠤⣀⠀⠀⠀⠀⠀⠀ ⠀⠀⠀⠛⠀⠀⣀⠿⠟⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠢⣄⠀⠀⠀⠀⠀⠀⣠⠔⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢣⡀⠀⠀⢀⡜⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠱⠤⠤⠎⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        """;
        System.out.println(art);
    }
}

