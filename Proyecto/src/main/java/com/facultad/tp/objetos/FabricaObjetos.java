package com.facultad.tp.objetos;

import java.util.Random;

public class FabricaObjetos {
    private static final Random rand = new Random();

    public static ObjetoMagico crearObjetoAleatorio() {
        return switch (rand.nextInt(3)) {
            case 0 -> new VaritaMagica();
            case 1 -> new CapaInvisibilidad();
            default -> new AmuletoMana();
        };
    }
}
