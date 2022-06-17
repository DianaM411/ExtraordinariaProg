package com.diana;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        //declaramos variables
        int totalLED = 0;
        int totalDESCARGA = 0;
        int totalLEDDESCARGA = 0;

        try {
            String pwd = System.getProperty("user.dir");//ruta directorio actual de trabajo
            String rutaAbsolutaFarolas = pwd + File.separator + "farolas.csv";
            String rutaAbsolutaDistritos = pwd + File.separator + "Distritos.csv";

            //los ficheros que voy a leer
            File ficheroFarolas = new File(rutaAbsolutaFarolas);
            File ficheroDistritos = new File(rutaAbsolutaDistritos);

            FileInputStream fis = new FileInputStream(ficheroFarolas);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = null;
            int count = 0;


            while ((line = br.readLine()) != null) { // read through file line by line
                if (count != 0) { //menos la primera fila
                    String data = line;
                    String parte[] = data.split(";");//dividimos cada linea en partes separadas por ;
                    //sumamos todos los tipos de bombillas
                    if (parte[1].equals("LED")) {
                        totalLED += 1;
                    } else if (parte[1].equals("DESCARGA")) {
                        totalDESCARGA += 1;
                    } else if (parte[1].equals("LED-DESCARGA")) {
                        totalLEDDESCARGA += 1;
                    }

                }
                count++; // count increments as you read lines
            }
            br.close(); //cerramos BufferedReader

            //calculamos media de farolos en Madrid por distrito
            int totalMadrid = totalLED + totalDESCARGA + totalLEDDESCARGA;
            double mediaMadrid = totalMadrid / 21;

            //imprimo para verificar
            System.out.println("LED: " + totalLED);
            System.out.println("DESCARGA: " + totalDESCARGA);
            System.out.println("LED-DESCARGA: " + totalLEDDESCARGA);

            System.out.println("--------------------------");
            if (totalLED > totalDESCARGA) {
                if (totalLED > totalLEDDESCARGA) {
                    System.out.println("Hay mas de tipo LED: " + totalLED);
                } else {
                    System.out.println("Hay mas de tipo LED-DESCARGA: " + totalLEDDESCARGA);
                }
            } else if (totalDESCARGA > totalLEDDESCARGA) {
                System.out.println("Hay mas de tipo DESCARGA: " + totalDESCARGA);
            } else {
                System.out.println("Hay mas de tipo LED-DESCARGA: " + totalLEDDESCARGA);
            }

            System.out.println("--------------------------");
            System.out.println("Farolas de media en Madrid :" + mediaMadrid);
            System.out.println("--------------------------");
            System.out.println("Total farolas por cada distrito");

        } catch (Exception ex) {//manejamos excepciones
            ex.printStackTrace();
        }
    }
}
