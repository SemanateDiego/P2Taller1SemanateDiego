package com.mycompany.p1taller3semanatediego;

public class P1Taller3SemanateDiego {

    public static void main(String[] args) {
        // Crear objetos
        Persona estudiante = new Persona("Diego");
        Cocina cocina = new Cocina();
        Bicicleta bicicleta = new Bicicleta("Trek");

        // Simulación de la rutina
        estudiante.alistarse();
        estudiante.escogerRopa("jeans y camiseta");
        estudiante.ducharse();
        estudiante.secarse();
        estudiante.vestirse();
        estudiante.desayunar(cocina);
        estudiante.irALaUniversidad(bicicleta);
    }
}
