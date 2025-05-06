package com.mycompany.p1taller3semanatediego;

public class Bicicleta {
    private String marca;

    public Bicicleta(String marca) {
        this.marca = marca;
    }

    public void conducir(String persona) {
        System.out.println(persona + " esta conduciendo la bicicleta " + marca + " hacia la universidad.");
    }
}

