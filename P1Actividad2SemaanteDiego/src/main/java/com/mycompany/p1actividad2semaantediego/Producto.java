package com.mycompany.p1actividad2semaantediego;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Producto extends Categoria {
    private String codigo;
    private String nombreProducto;
    private double precio;
    //Constructor
    public Producto(String codigo, String nombreProducto, double precio, int id, String nombre) {
        super(id, nombre);
        setCodigo(codigo);              
        setNombreProducto(nombreProducto);
        setPrecio(precio);
    }


    // Getters y setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo != null && !codigo.trim().isEmpty()) {
            if (codigo.matches("[A-Za-z0-9]+")) {
                this.codigo = codigo;
            } else {
                System.out.println("Error: El codigo solo debe contener letras y números.");
                this.codigo = "CodigoInvalido";
            }
        } else {
            System.out.println("Error: El codigo no puede estar vacio.");
            this.codigo = "CodigoInvalido";
        }
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        if (nombreProducto != null && !nombreProducto.trim().isEmpty()) {
            if (nombreProducto.matches("[A-Za-zÁÉÍÓÚáéíóúñÑ\s]+")) {
                this.nombreProducto = nombreProducto;
            } else {
                System.out.println("Error: El nombre no puede contener numeros ni caracteres invalidos.");
                this.nombreProducto = "NombreProductoInvalido";
            }
        } else {
            System.out.println("Error: El nombre del producto no puede estar vacio.");
            this.nombreProducto = "NombreProductoInvalido";
        }
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio > 0) {
            this.precio = precio;
        } else {
            System.out.println("Error: El precio debe ser mayor que 0.");
            this.precio = 0.01;
        }
    }

    public void mostrarResumen() {
        System.out.println("Codigo del producto: " + codigo);
        System.out.println("Nombre del producto: " + nombreProducto);
        System.out.println("Precio: $" + precio);
        System.out.println("Categoria: " + getNombre() + " (ID: " + getId() + ")");
    }
    

    // Guardar en archivo CSV
    public void guardarEnCSV(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        boolean archivoExiste = archivo.exists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            if (!archivoExiste) {
                // Escribir encabezados
                writer.write("Codigo,NombreProducto,Precio,CategoriaID,CategoriaNombre");
                writer.newLine();
            }

            // Escribir datos del producto
            writer.write(getCodigo() + "," + getNombreProducto() + "," + getPrecio() + "," + getId() + "," + getNombre());
            writer.newLine();

            System.out.println("Producto guardado en el archivo CSV correctamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar en el archivo CSV: " + e.getMessage());
        }
    }
}
//arreglos y listas
//estudiar para esta semana 
//agregar leer y excepciones


    

