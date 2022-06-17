package com.diana;

public class Main {

    public static void main(String[] args) {
        int casosPrueba = 3;
        int caso1 = 1582;
        int caso2 = 2000;
        int caso3 = 2101;

        int salida1 = Integer.valueOf(String.valueOf(caso1).substring(0, 2)) + 1;
        int salida2 = Integer.valueOf(String.valueOf(caso2).substring(0, 2)) + 1;
        int salida3 = Integer.valueOf(String.valueOf(caso3).substring(0, 2)) + 1;

        System.out.println(salida1);
        System.out.println(salida2);
        System.out.println(salida3);
    }
}
