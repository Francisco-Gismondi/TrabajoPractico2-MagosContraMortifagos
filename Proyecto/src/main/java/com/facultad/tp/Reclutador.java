package com.facultad.tp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.facultad.tp.objetos.FabricaObjetos;
import com.facultad.tp.objetos.ObjetoMagico;

public class Reclutador {

    private static Random rand = new Random();
    private static int contadorMagos = 0;
    private static int contadorMortifagos = 0;
    
    private static List<String> nombresUnicosMagos = new ArrayList<>();
    private static List<String> nombresUnicosMortifagos = new ArrayList<>();

    static {
        // Personajes buenos de las peliculas
        nombresUnicosMagos.add("Harry Potter");
        nombresUnicosMagos.add("Hermione ");
        nombresUnicosMagos.add("Ron Weasley");
        nombresUnicosMagos.add("Dumbledore");
        nombresUnicosMagos.add("McGonagall");
        nombresUnicosMagos.add("Remus Lupin");
        nombresUnicosMagos.add("Sirius Black");
        nombresUnicosMagos.add("Gandalf");
        nombresUnicosMagos.add("Ojoloco Moody");
        nombresUnicosMagos.add("Neville");
        Collections.shuffle(nombresUnicosMagos);

        // Mortifagos y villanos
        nombresUnicosMortifagos.add("Voldemort");
        nombresUnicosMortifagos.add("Bellatrix");
        nombresUnicosMortifagos.add("Severus Snape");
        nombresUnicosMortifagos.add("Lucius Malfoy");
        nombresUnicosMortifagos.add("Draco Malfoy");
        nombresUnicosMortifagos.add("BartyCrouchJr");
        nombresUnicosMortifagos.add("Regulus Black");
        nombresUnicosMortifagos.add("Antonin D.");
        Collections.shuffle(nombresUnicosMortifagos);
    }
    
    private static void asignarObjetos(Personaje p) {
        // 40% de probabilidad de tener un objeto equipado (0 o 1 en un dado de 10)
        if (rand.nextInt(10) < 4) { 
            ObjetoMagico obj = FabricaObjetos.crearObjetoAleatorio();
            p.agregarObjeto(obj);
            p.equipar(obj);
        }
    }

    public static Mago crearMago() {
        contadorMagos++;
        Mago mago = null; // La declaramos al principio

        switch (rand.nextInt(11)) {
            case 0:
            case 10:
                if (!nombresUnicosMagos.isEmpty()) {
                    mago = new Auror(nombresUnicosMagos.remove(0), 180, 210, 220);
                } else {
                    mago = new Auror("Auror " + contadorMagos, 120 + rand.nextInt(31), 120 + rand.nextInt(81), 150);
                }
                break;

            case 1: case 2: case 3:
                mago = new Auror("Auror " + contadorMagos, 120 + rand.nextInt(31), 120 + rand.nextInt(81), 150);
                break;
            case 4: case 5: case 6:
                mago = new Estudiante("Estudiante " + contadorMagos, 40 + rand.nextInt(31), 80 + rand.nextInt(41), 100);
                break;
            case 7: case 8: case 9:
                mago = new Profesor("Profesor " + contadorMagos, 80 + rand.nextInt(21), 130 + rand.nextInt(71), 125);
                break;
            default:
                mago = new Auror("Auror " + contadorMagos, 120 + rand.nextInt(31), 120 + rand.nextInt(81), 150);
                break;
        }

        asignarObjetos(mago);
        return mago;
    }

    public static Mortifago crearMortifago() {
        contadorMortifagos++;
        
        Mortifago mortifago = switch (rand.nextInt(10)) {
            // 10% de probabilidad: Villano Único
            case 0 -> {
                if (!nombresUnicosMortifagos.isEmpty()) {
                    yield new Comandante(nombresUnicosMortifagos.remove(0), 170, 210, 230);
                }
                yield new Seguidor("Seguidor " + contadorMortifagos, 50 + rand.nextInt(31), 30 + rand.nextInt(51), 110);
            }
            
            // 90% restante: Fuerzas oscuras comunes
            case 1, 2, 3, 4, 5 -> new Seguidor("Seguidor " + contadorMortifagos, 50 + rand.nextInt(31), 30 + rand.nextInt(51), 110);
            case 6, 7, 8, 9    -> new Comandante("Comandante " + contadorMortifagos, 75 + rand.nextInt(26), 130 + rand.nextInt(71), 160);
            
            default -> new Seguidor("Seguidor " + contadorMortifagos, 50 + rand.nextInt(31), 90 + rand.nextInt(51), 110);
        };
        asignarObjetos(mortifago);
        return mortifago;
    }
}