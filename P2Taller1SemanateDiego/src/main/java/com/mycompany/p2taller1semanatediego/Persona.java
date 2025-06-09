package com.mycompany.p2taller1semanatediego;

public class Persona {
    private String nombre;
    private int edad;
    private String ID;

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
        return "Nombre: " + nombre + ", Edad: " + edad + ", ID: " + ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona)) return false;
        Persona persona = (Persona) o;
        return ID != null && ID.equals(persona.ID);
    }

    @Override
    public int hashCode() {
        return ID != null ? ID.hashCode() : 0;
    }
}


