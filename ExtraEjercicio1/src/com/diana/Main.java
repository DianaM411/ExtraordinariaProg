package com.diana;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        //declaramos variables
        int totalLED = 0;
        int totalDESCARGA = 0;
        int totalLEDDESCARGA = 0;
        ArrayList<String> listaCodDistritos = new ArrayList<String>();
        HashMap<String, Integer> mapCodigoTotal = new LinkedHashMap<String, Integer>();
        HashMap<String, String> mapCodigoNombre = new LinkedHashMap<String, String>();

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

                    //guardamos todos los codigos de distrito en un Arraylist
                    listaCodDistritos.add(parte[8]);
                }
                count++; // count increments as you read lines
            }
            br.close(); //cerramos BufferedReader

            FileInputStream fis2 = new FileInputStream(ficheroDistritos);
            BufferedReader br2 = new BufferedReader(new InputStreamReader(fis2));


            while ((line = br2.readLine()) != null) { // read through file line by line
                String data = line;
                String parte[] = data.split(";");//dividimos cada linea en partes separadas por ;
                mapCodigoNombre.put(parte[4], parte[5]);
                count++; // count increments as you read lines
            }
            br2.close(); //cerramos BufferedReader

            //imprimo por pantalla
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
            //calculamos media de farolos en Madrid por distrito
            int totalMadrid = totalLED + totalDESCARGA + totalLEDDESCARGA;
            double mediaMadrid = totalMadrid / 21;

            System.out.println("--------------------------");
            System.out.println("Farolas de media en Madrid :" + mediaMadrid);

            System.out.println("--------------------------");
            //calculamos cuantas farolas hay en cada distrito
            for (String codigo : listaCodDistritos) {
                Integer numeroTotal = mapCodigoTotal.get(codigo);

                if (numeroTotal == null) {
                    numeroTotal = 1;
                } else {
                    numeroTotal++;
                }

                mapCodigoTotal.put(codigo, numeroTotal);
            }

            // imprimo el HashMap nombreDistrito - numeroTotalFarolas
            for (String i : mapCodigoTotal.keySet()) {
                for (String s : mapCodigoNombre.keySet()) {//reiterando los 2 hasmaps que tengo, saco nombre + numeroFarolas
                    if (i.equals(s))
                        System.out.println("Distrito: " + mapCodigoNombre.get(s) + ", numero farolas: " + mapCodigoTotal.get(i));
                }
            }
        } catch (Exception ex) {//manejamos excepciones
            ex.printStackTrace();
        }
    }
}
