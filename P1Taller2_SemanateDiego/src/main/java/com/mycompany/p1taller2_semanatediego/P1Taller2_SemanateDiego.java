package com.mycompany.p1taller2_semanatediego;

import java.util.Scanner;

public class P1Taller2_SemanateDiego {
    //Parte 1
    /*public static class BadNames { //ERROR LOGICO SE COLOCO STATIC A CLASE PARA QUE FUNCIONE EL PROGRAMA
        // Arreglo donde guardaremos hasta 10 números
        private int[] n = new int[10];// ¿qué es “n”?
        //Corregido
        //private int [] numeros = new int [10];  
        private int i = 0;// índice actual… ¿“i” de qué?
        //Corregido
        //private int indiceActual = 0;
        // Método “a”: agrega un número al arreglo
        public void a(int v) {
            n[i++] = v; // “v” tampoco dice mucho
        }
        //Corregido
        /*public void a(int indiceAumento) {
            numeros[indiceActual++] = indiceAumento;                  
        }*/
        // Método “b”: calcula la suma
        /*public int b() {
            int s = 0;  // “s” -> ¿suma? ¿salario?
            for (int j = 0; j < i; j++) {
                s += n[j];
            }
            return s;
        }*/
        //Corregido
        /*public int b() {
            int suma = 0;                   
            for (int j = 0; j < indiceActual; j++) {
                suma += numeros[j];
            }
            return suma;
        }*/
        // Método “c”: promedio de los números guardados
        /*public double c() {
            return i == 0 ? 0 : (double) b() / i;
        }*/
        //Corregido
        /*public double c() {
            return indiceActual == 0 ? 0 : (double) b() / indiceActual;
        }*/    
    //}

    // Pequeña interfaz de consola para ejecutar y probar
    /*public static void main(String[] args) { // ERROR LOGICO EL MAIN SE MOVIDO FUERA de BadNames 
        BadNames x = new BadNames(); // ¿por qué “x”?
        //Corregido
        //BadNames operacion = new BadNames();
        Scanner sc = new Scanner(System.in);

        System.out.print("Cuantos numeros ingresara? ");
        int t = sc.nextInt();// “t” -> ¿total?
        //Corregido
        //int total = sc.nextInt();

        for (int k = 0; k < t; k++) { // “k” -> contador genérico
            System.out.print("Numero: ");
            x.a(sc.nextInt());
        }
        //Corregido
            /*for (int k = 0; k < total; k++) {              
                System.out.print("Número: ");
                operacion.a(sc.nextInt());
            }*/
        //System.out.println("Suma = " + x.b());
        //Corregido
        //System.out.println("Suma = " + operacion.b());
        //System.out.println("Promedio = " + x.c());
        //Corregido
        //System.out.println("Promedio = " + operacion.c());
        //sc.close();
    //}  
    //Parte 2
    
    public static class OperadorNumerico {

        private int[] numeros = new int[10];
        private int cantidadIngresada = 0;

        public void agregarNumero(int numero) {
            numeros[cantidadIngresada++] = numero;
        }

        public int calcularSuma() {
            int suma = 0;
            for (int indice = 0; indice < cantidadIngresada; indice++) {
                suma += numeros[indice];
            }
            return suma;
        }

        public double calcularPromedio() {
            return cantidadIngresada == 0 ? 0 : (double) calcularSuma() / cantidadIngresada;
        }
    }

    public static void main(String[] args) {
        OperadorNumerico operador = new OperadorNumerico();
        Scanner scanner = new Scanner(System.in);

        int totalNumeros = solicitarCantidadNumeros(scanner);
        leerNumeros(operador, scanner, totalNumeros);
        mostrarResultados(operador);

        scanner.close();
    }

    private static int solicitarCantidadNumeros(Scanner scanner) {
        System.out.print("Cuantos numeros ingresara? ");
        return scanner.nextInt();
    }

    private static void leerNumeros(OperadorNumerico operador, Scanner scanner, int total) {
        for (int contador = 0; contador < total; contador++) {
            System.out.print("Numero: ");
            operador.agregarNumero(scanner.nextInt());
        }
    }

    private static void mostrarResultados(OperadorNumerico operador) {
        System.out.println("Suma = " + operador.calcularSuma());
        System.out.println("Promedio = " + operador.calcularPromedio());
    }

}


