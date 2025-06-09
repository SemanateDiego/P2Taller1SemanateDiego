package com.mycompany.p2taller1semanatediego;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class P2Taller1SemanateDiego {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int x;
        while (true) {
            System.out.print("Cuantas personas desea ingresar?: ");
            try {
                x = sc.nextInt();
                if (x > 0) break;
                System.out.println("Ingrese un numero entero positivo.");
            } catch (InputMismatchException e) {
                System.out.println("Entrada invalida. Ingrese un numero entero positivo.");
                sc.next();
            }
        }

        sc.nextLine(); // Limpiar buffer
        ArrayList<Persona> personas = new ArrayList<>();
        HashSet<Persona> personaSet = new HashSet<>();

        for (int i = 0; i < x; i++) {
            String nombre = "Persona" + (i + 1);
            String id = "" + (i + 1);
            int edad = rand.nextInt(90) + 10;
            Persona p = new Persona(nombre, edad, id);
            personas.add(p);
            personaSet.add(p);
        }

        System.out.println("\nPersonas sin ordenar:");
        mostrar(personas);

        int opcionOrdenamiento = mostrarMenuOrdenamiento(sc);
        ArrayList<Persona> copia = new ArrayList<>(personas);
        long tiempo1 = System.nanoTime();

        switch (opcionOrdenamiento) {
            case 1 -> burbuja(copia);
            case 2 -> seleccion(copia);
            case 3 -> insercion(copia);
            case 4 -> shellSort(copia);
            case 5 -> mergeSort(copia, 0, copia.size() - 1);
            case 6 -> quickSort(copia, 0, copia.size() - 1);
        }

        long tiempo2 = System.nanoTime();
        System.out.println("\nPersonas ordenadas (" + nombreMetodo(opcionOrdenamiento) +
                ") en " + (tiempo2 - tiempo1) + " ns:");
        mostrar(copia);

        int opcionBusqueda = mostrarMenuBusqueda(sc);
        int edadBuscar;
        while (true) {
            System.out.print("\nIngrese edad para buscar: ");
            try {
                edadBuscar = sc.nextInt();
                if (edadBuscar > 0) break;
                System.out.println("Ingrese una edad valida (entero positivo).");
            } catch (InputMismatchException e) {
                System.out.println("Entrada invalida. Ingrese una edad valida (entero positivo).");
                sc.next();
            }
        }

        if (opcionBusqueda == 1) {
            System.out.println("\nResultados de busqueda lineal:");
            busquedaLineal(personas, edadBuscar);
        } else {
            System.out.println("\nResultado de busqueda binaria:");
            Persona resultado = busquedaBinaria(copia, edadBuscar);
            if (resultado != null) {
                System.out.println("Primera coincidencia: " + resultado);
            } else {
                System.out.println("Edad no encontrada.");
            }
        }

        guardarEnCSV("personas_ordenadas", copia);
    }
    //Metodos que trabajan con el ArrayList
    
    public static void mostrar(List<Persona> lista) {
        for (Persona p : lista) System.out.println(p);
    }

    public static void burbuja(List<Persona> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (list.get(j).getEdad() > list.get(j + 1).getEdad()) {
                    Collections.swap(list, j, j + 1);
                }
    }

    public static void seleccion(List<Persona> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++)
                if (list.get(j).getEdad() < list.get(min).getEdad())
                    min = j;
            Collections.swap(list, i, min);
        }
    }

    public static void insercion(List<Persona> list) {
        for (int i = 1; i < list.size(); i++) {
            Persona key = list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j).getEdad() > key.getEdad()) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    public static void shellSort(List<Persona> list) {
        int n = list.size();
        for (int gap = n / 2; gap > 0; gap /= 2)
            for (int i = gap; i < n; i++) {
                Persona temp = list.get(i);
                int j = i;
                while (j >= gap && list.get(j - gap).getEdad() > temp.getEdad()) {
                    list.set(j, list.get(j - gap));
                    j -= gap;
                }
                list.set(j, temp);
            }
    }

    public static void mergeSort(List<Persona> list, int izq, int der) {
        if (izq < der) {
            int mid = (izq + der) / 2;
            mergeSort(list, izq, mid);
            mergeSort(list, mid + 1, der);
            merge(list, izq, mid, der);
        }
    }

    public static void merge(List<Persona> list, int izq, int mid, int der) {
        List<Persona> aux = new ArrayList<>();
        int i = izq, j = mid + 1;
        while (i <= mid && j <= der)
            aux.add(list.get(i).getEdad() <= list.get(j).getEdad() ? list.get(i++) : list.get(j++));
        while (i <= mid) aux.add(list.get(i++));
        while (j <= der) aux.add(list.get(j++));
        for (int k = 0; k < aux.size(); k++)
            list.set(izq + k, aux.get(k));
    }

    public static void quickSort(List<Persona> list, int izq, int der) {
        if (izq < der) {
            int pi = particion(list, izq, der);
            quickSort(list, izq, pi - 1);
            quickSort(list, pi + 1, der);
        }
    }

    public static int particion(List<Persona> list, int izq, int der) {
        int pivot = list.get(der).getEdad();
        int i = izq - 1;
        for (int j = izq; j < der; j++) {
            if (list.get(j).getEdad() <= pivot) {
                i++;
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, i + 1, der);
        return i + 1;
    }

    public static void busquedaLineal(List<Persona> list, int edad) {
        boolean encontrado = false;
        for (Persona p : list)
            if (p.getEdad() == edad) {
                System.out.println(p);
                encontrado = true;
            }
        if (!encontrado) System.out.println("Edad no encontrada.");
    }

    public static Persona busquedaBinaria(List<Persona> list, int edad) {
        int izq = 0, der = list.size() - 1;
        while (izq <= der) {
            int mid = (izq + der) / 2;
            if (list.get(mid).getEdad() == edad) return list.get(mid);
            else if (list.get(mid).getEdad() < edad) izq = mid + 1;
            else der = mid - 1;
        }
        return null;
    }

    public static void guardarEnCSV(String baseNombreArchivo, List<Persona> personas) {
        String timestamp = java.time.LocalDateTime.now().toString().replace(":", "-");
        String nombreArchivo = baseNombreArchivo + "_" + timestamp + ".csv";
        try (java.io.PrintWriter writer = new java.io.PrintWriter(nombreArchivo)) {
            writer.println("Nombre,Edad,ID");
            for (Persona p : personas) {
                writer.println(p.getNombre() + "," + p.getEdad() + "," + p.getID());
            }
            System.out.println("Datos guardados en el archivo: " + nombreArchivo);
        } catch (java.io.IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    public static int mostrarMenuOrdenamiento(Scanner sc) {
        System.out.println("\nSeleccione un metodo de ordenamiento:");
        System.out.println("1. Burbuja");
        System.out.println("2. Seleccion");
        System.out.println("3. Insercion");
        System.out.println("4. Shell Sort");
        System.out.println("5. Merge Sort");
        System.out.println("6. Quick Sort");
        while (true) {
            try {
                System.out.print("Opcion (1-6): ");
                int opcion = sc.nextInt();
                if (opcion >= 1 && opcion <= 6) return opcion;
            } catch (InputMismatchException e) {
                sc.next();
            }
            System.out.println("Entrada inválida. Intente nuevamente.");
        }
    }

    public static int mostrarMenuBusqueda(Scanner sc) {
        System.out.println("\nSeleccione un metodo de busqueda:");
        System.out.println("1. Busqueda Lineal");
        System.out.println("2. Busqueda Binaria");
        while (true) {
            try {
                System.out.print("Opcion (1-2): ");
                int opcion = sc.nextInt();
                if (opcion == 1 || opcion == 2) return opcion;
            } catch (InputMismatchException e) {
                sc.next();
            }
            System.out.println("Entrada inválida. Intente nuevamente.");
        }
    }

    public static String nombreMetodo(int opcion) {
        return switch (opcion) {
            case 1 -> "Burbuja";
            case 2 -> "Seleccion";
            case 3 -> "Insercion";
            case 4 -> "Shell";
            case 5 -> "MergeSort";
            case 6 -> "QuickSort";
            default -> "Desconocido";
        };
    }
}


