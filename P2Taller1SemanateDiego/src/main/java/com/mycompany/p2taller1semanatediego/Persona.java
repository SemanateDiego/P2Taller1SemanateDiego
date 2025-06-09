package com.mycompany.p2taller1semanatediego;

public class Persona {
    private String nombre,ID;
    private int edad;

    public Persona(String nombre, int edad, String ID) {
        this.nombre = nombre;
        this.edad = edad;
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getID() {
        return ID;
    }
    
    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Edad: " + edad + ", ID:" + ID;
    }
}

