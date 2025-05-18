package com.mycompany.p1actividad2semaantediego;

import java.util.Scanner;

public class P1Actividad2SemaanteDiego {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int idCat;
        do {
            System.out.print("Ingrese el ID de la categoria (mayor que 0): ");
            idCat = sc.nextInt();
            if (idCat <= 0) {
                System.out.println("Error: El ID debe ser mayor que 0.");
            }
        } while (idCat <= 0);
        sc.nextLine(); // limpiar buffer

        String nombreCat;
        do {
            System.out.print("Ingrese el nombre de la categoria: ");
            nombreCat = sc.nextLine();
            if (nombreCat.trim().isEmpty() || !nombreCat.matches("[A-Za-zÁÉÍÓÚáéíóúñÑ\\s]+")) {
                System.out.println("Error: Nombre invalido. Solo letras y espacios.");
                nombreCat = "";
            }
        } while (nombreCat.isEmpty());

        String codigo;
        do {
            System.out.print("Ingrese el codigo del producto: ");
            codigo = sc.nextLine();
            if (codigo.trim().isEmpty() || !codigo.matches("[A-Za-z0-9]+")) {
                System.out.println("Error: El codigo solo debe contener letras y numeros.");
                codigo = "";
            }
        } while (codigo.isEmpty());

        String nombreProd;
        do {
            System.out.print("Ingrese el nombre del producto: ");
            nombreProd = sc.nextLine();
            if (nombreProd.trim().isEmpty() || !nombreProd.matches("[A-Za-zÁÉÍÓÚáéíóúñÑ\\s]+")) {
                System.out.println("Error: El nombre no puede contener numeros ni caracteres invalidos.");
                nombreProd = "";
            }
        } while (nombreProd.isEmpty());

        double precio;
        do {
            System.out.print("Ingrese el precio del producto (mayor a 0): ");
            precio = sc.nextDouble();
            if (precio <= 0) {
                System.out.println("Error: El precio debe ser mayor que 0.");
            }
        } while (precio <= 0);

        // Crear el producto que hereda de Categoria
        Producto producto = new Producto(codigo, nombreProd, precio, idCat, nombreCat);

        System.out.println("\n=== RESUMEN DEL PRODUCTO ===");
        producto.mostrarResumen();

        // Guardar en CSV
        producto.guardarEnCSV("productos.csv");

        sc.close();
    }
    
}

