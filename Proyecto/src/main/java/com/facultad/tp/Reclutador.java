package com.facultad.tp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Reclutador {

    private static Random rand = new Random();
    private static int contadorMagos = 0;
    private static int contadorMortifagos = 0;
    
    private static List<String> nombresUnicosMagos = new ArrayList<>();
    private static List<String> nombresUnicosMortifagos = new ArrayList<>();

    static {
        // Personajes icónicos de la Orden del Fénix y el Ejército de Dumbledore
        nombresUnicosMagos.add("Harry Potter");
        nombresUnicosMagos.add("Hermione ");
        nombresUnicosMagos.add("Ron Weasley");
        nombresUnicosMagos.add("Dumbledore");
        nombresUnicosMagos.add("McGonagall");
        nombresUnicosMagos.add("Remus Lupin");
        nombresUnicosMagos.add("Sirius Black");
        nombresUnicosMagos.add("Gandalf");
        nombresUnicosMagos.add("Ojo-loco Moody");
        nombresUnicosMagos.add("Neville");
        Collections.shuffle(nombresUnicosMagos);

        // Miembros destacados de los Mortífagos y aliados oscuros
        nombresUnicosMortifagos.add("Lord Voldemort");
        nombresUnicosMortifagos.add("Bellatrix");
        nombresUnicosMortifagos.add("Severus Snape");
        nombresUnicosMortifagos.add("Lucius Malfoy");
        nombresUnicosMortifagos.add("Draco Malfoy");
        nombresUnicosMortifagos.add("Barty CrouchJr");
        nombresUnicosMortifagos.add("Regulus Black");
        nombresUnicosMortifagos.add("Antonin Dolohov");
        Collections.shuffle(nombresUnicosMortifagos);
    }
    
    public static Mago crearMago() {
        contadorMagos++;
        
        return switch (rand.nextInt(11)) {
            // 10% de probabilidad: Héroe Único
            case 0,10 -> {
                if (!nombresUnicosMagos.isEmpty()) {
                    // Estadísticas balanceadas para un héroe legendario
                    yield new Auror(nombresUnicosMagos.remove(0), 180, 210, 220);
                }
                yield new Auror("Auror " + contadorMagos, 120 + rand.nextInt(31), 120 + rand.nextInt(81), 150);
            }
            
            // 90% restante: Miembros comunes del batallón
            case 1, 2, 3 -> new Auror("Auror " + contadorMagos, 120 + rand.nextInt(31), 120 + rand.nextInt(81), 150);
            case 4, 5, 6 -> new Estudiante("Estudiante " + contadorMagos, 40 + rand.nextInt(31), 80 + rand.nextInt(41), 100);
            case 7, 8, 9 -> new Profesor("Profesor " + contadorMagos, 80 + rand.nextInt(21), 130 + rand.nextInt(71), 125);
            
            default -> new Auror("Auror " + contadorMagos, 120 + rand.nextInt(31), 120 + rand.nextInt(81), 150);
        };
    }

    public static Mortifago crearMortifago() {
        contadorMortifagos++;
        
        return switch (rand.nextInt(10)) {
            // 10% de probabilidad: Villano Único
            case 0 -> {
                if (!nombresUnicosMortifagos.isEmpty()) {
                    // Estadísticas balanceadas para un comandante legendario
                    yield new Comandante(nombresUnicosMortifagos.remove(0), 170, 210, 230);
                }
                yield new Seguidor("Seguidor " + contadorMortifagos, 50 + rand.nextInt(31), 30 + rand.nextInt(51), 110);
            }
            
            // 90% restante: Fuerzas oscuras comunes
            case 1, 2, 3, 4, 5 -> new Seguidor("Seguidor " + contadorMortifagos, 50 + rand.nextInt(31), 30 + rand.nextInt(51), 110);
            case 6, 7, 8, 9    -> new Comandante("Comandante " + contadorMortifagos, 75 + rand.nextInt(26), 130 + rand.nextInt(71), 160);
            
            default -> new Seguidor("Seguidor " + contadorMortifagos, 50 + rand.nextInt(31), 90 + rand.nextInt(51), 110);
        };
    }
}