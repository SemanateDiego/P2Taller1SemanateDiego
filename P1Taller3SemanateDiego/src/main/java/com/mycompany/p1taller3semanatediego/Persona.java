package com.mycompany.p1taller3semanatediego;

public class Persona {
    private String nombre;
    private String ropa;

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    public void alistarse() {
        System.out.println(nombre + " se esta alistando para ir a la universidad.");
    }

    public void escogerRopa(String ropa) {
        this.ropa = ropa;
        System.out.println(nombre + " ha escogido la ropa: " + ropa);
    }

    public void ducharse() {
        System.out.println(nombre + " se esta duchando.");
    }

    public void secarse() {
        System.out.println(nombre + " se esta secando.");
    }

    public void vestirse() {
        System.out.println(nombre + " se ha vestido con: " + ropa);
    }

    public void desayunar(Cocina cocina) {
        cocina.prepararSandwich();
        cocina.servirJugo();
    }

    public void irALaUniversidad(Bicicleta bicicleta) {
        bicicleta.conducir(nombre);
    }
}

