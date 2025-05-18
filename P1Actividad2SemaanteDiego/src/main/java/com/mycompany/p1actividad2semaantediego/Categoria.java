package com.mycompany.p1actividad2semaantediego;

public class Categoria {
    private int id;
    private String nombre;

    // Constructor usando setters para activar validaciones
    public Categoria(int id, String nombre) {
        setId(id);           
        setNombre(nombre);   
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            System.out.println("Error: El ID debe ser mayor que 0.");
            this.id = 1; // Valor por defecto mínimo válido
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            if (nombre.matches("[A-Za-zÁÉÍÓÚáéíóúñÑ\\s]+")) {
                this.nombre = nombre;
            } else {
                System.out.println("Error: El nombre de la categoria contiene caracteres no validos.");
                this.nombre = "NombreCategoriaInvalido";
            }
        } else {
            System.out.println("Error: El nombre de la categoria no puede estar vacio.");
            this.nombre = "NombreCategoriaInvalido";
        }
    }
}
