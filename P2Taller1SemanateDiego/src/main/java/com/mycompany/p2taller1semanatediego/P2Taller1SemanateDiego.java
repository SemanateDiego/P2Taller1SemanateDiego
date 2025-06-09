package com.mycompany.p2taller1semanatediego;

import java.util.InputMismatchException;
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
        Persona[] personas = new Persona[x];

        // Generar datos
        for (int i = 0; i < x; i++) {
            String nombre = "Persona" + (i + 1);
            String Id = "" + (i+1);
            int edad = rand.nextInt(90) + 10; // Edad entre 10 y 99
            personas[i] = new Persona(nombre, edad, Id);
        }

        // Mostrar sin ordenar
        System.out.println("\nPersonas sin ordenar:");
        mostrar(personas);

        // Menú de ordenamiento
        int opcionOrdenamiento = mostrarMenuOrdenamiento(sc);
        Persona[] copia = personas.clone();
        long tiempo1 = System.nanoTime();

        switch (opcionOrdenamiento) {
            case 1 -> burbuja(copia);
            case 2 -> seleccion(copia);
            case 3 -> insercion(copia);
            case 4 -> shellSort(copia);
            case 5 -> mergeSort(copia, 0, copia.length - 1);
            case 6 -> quickSort(copia, 0, copia.length - 1);
        }

        long tiempo2 = System.nanoTime();
        System.out.println("\nPersonas ordenadas (" + nombreMetodo(opcionOrdenamiento) +
                ") en " + (tiempo2 - tiempo1) + " ns:");
        mostrar(copia);

        // Guardar en CSV
        guardarEnCSV("personas_ordenadas", copia);

        // Búsqueda
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
    }

    public static void mostrar(Persona[] arr) {
        for (Persona p : arr) System.out.println(p);
    }

    public static int mostrarMenuOrdenamiento(Scanner sc) {
        System.out.println("\nSeleccione un metodo de ordenamiento:");
        System.out.println("1. Burbuja");
        System.out.println("2. Seleccion");
        System.out.println("3. Insercion");
        System.out.println("4. Shell Sort");
        System.out.println("5. Merge Sort");
        System.out.println("6. Quick Sort");

        int opcion;
        while (true) {
            System.out.print("Opcion (1-6): ");
            try {
                opcion = sc.nextInt();
                if (opcion >= 1 && opcion <= 6) break;
                System.out.println("Por favor, elija una opcion valida (1-6).");
            } catch (InputMismatchException e) {
                System.out.println("Entrada invalida. Intente de nuevo.");
                sc.next();
            }
        }
        return opcion;
    }

    public static int mostrarMenuBusqueda(Scanner sc) {
        System.out.println("\nSeleccione un metodo de busqueda:");
        System.out.println("1. Busqueda Lineal");
        System.out.println("2. Busqueda Binaria");

        int opcion;
        while (true) {
            System.out.print("Opcion (1-2): ");
            try {
                opcion = sc.nextInt();
                if (opcion == 1 || opcion == 2) break;
                System.out.println("Por favor, elija una opcion valida (1 o 2).");
            } catch (InputMismatchException e) {
                System.out.println("Entrada invalida. Intente de nuevo.");
                sc.next();
            }
        }
        return opcion;
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

    // Métodos de ordenamiento
    public static void burbuja(Persona[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j].getEdad() > arr[j + 1].getEdad()) {
                    Persona temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    }

    public static void seleccion(Persona[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++)
                if (arr[j].getEdad() < arr[min].getEdad())
                    min = j;
            Persona temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }

    public static void insercion(Persona[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Persona key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].getEdad() > key.getEdad()) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void shellSort(Persona[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2)
            for (int i = gap; i < n; i++) {
                Persona temp = arr[i];
                int j = i;
                while (j >= gap && arr[j - gap].getEdad() > temp.getEdad()) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
    }

    public static void mergeSort(Persona[] arr, int izq, int der) {
        if (izq < der) {
            int mid = (izq + der) / 2;
            mergeSort(arr, izq, mid);
            mergeSort(arr, mid + 1, der);
            merge(arr, izq, mid, der);
        }
    }

    public static void merge(Persona[] arr, int izq, int mid, int der) {
        Persona[] aux = new Persona[der - izq + 1];
        int i = izq, j = mid + 1, k = 0;
        while (i <= mid && j <= der)
            aux[k++] = arr[i].getEdad() <= arr[j].getEdad() ? arr[i++] : arr[j++];
        while (i <= mid) aux[k++] = arr[i++];
        while (j <= der) aux[k++] = arr[j++];
        System.arraycopy(aux, 0, arr, izq, aux.length);
    }

    public static void quickSort(Persona[] arr, int izq, int der) {
        if (izq < der) {
            int pi = particion(arr, izq, der);
            quickSort(arr, izq, pi - 1);
            quickSort(arr, pi + 1, der);
        }
    }

    public static int particion(Persona[] arr, int izq, int der) {
        int pivot = arr[der].getEdad();
        int i = izq - 1;
        for (int j = izq; j < der; j++) {
            if (arr[j].getEdad() <= pivot) {
                i++;
                Persona temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Persona temp = arr[i + 1];
        arr[i + 1] = arr[der];
        arr[der] = temp;
        return i + 1;
    }

    // Métodos de búsqueda
    public static void busquedaLineal(Persona[] arr, int edad) {
        boolean encontrado = false;
        for (Persona p : arr)
            if (p.getEdad() == edad) {
                System.out.println(p);
                encontrado = true;
            }
        if (!encontrado) System.out.println("Edad no encontrada.");
    }

    public static Persona busquedaBinaria(Persona[] arr, int edad) {
        int izq = 0, der = arr.length - 1;
        while (izq <= der) {
            int mid = (izq + der) / 2;
            if (arr[mid].getEdad() == edad) return arr[mid];
            else if (arr[mid].getEdad() < edad) izq = mid + 1;
            else der = mid - 1;
        }
        return null;
    }

    // Guardar CSV
    public static void guardarEnCSV(String baseNombreArchivo, Persona[] personas) {
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
}

