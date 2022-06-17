package com.diana;

public class Main {

    //funcion que me imprime de que siglo es un ano dado
    public static void siglo(String ultimosDigitos, String caso) {
        int salida = 0;
        if (ultimosDigitos.equals("00")) {
            salida = Integer.valueOf(caso.substring(0, 2));
        } else salida = Integer.valueOf(caso.substring(0, 2)) + 1;

        System.out.println(salida);
    }

    public static void main(String[] args) {
        int casosPrueba = 3;
        int caso1 = 1582;
        int caso2 = 2000;
        int caso3 = 2101;

        //pasamos los numeros a string
        String stringCaso1 = String.valueOf(caso1);
        String stringCaso2 = String.valueOf(caso2);
        String stringCaso3 = String.valueOf(caso3);

        //guardamos los ultimos digitos de cada caso
        String ultimosDigitosCaso1 = stringCaso1.substring(stringCaso1.length() - 2);
        String ultimosDigitosCaso2 = stringCaso2.substring(stringCaso2.length() - 2);
        String ultimosDigitosCaso3 = stringCaso3.substring(stringCaso3.length() - 2);

        //llamamos a la funcion siglo
        siglo(ultimosDigitosCaso1, stringCaso1);
        siglo(ultimosDigitosCaso2, stringCaso2);
        siglo(ultimosDigitosCaso3, stringCaso3);
    }
}
