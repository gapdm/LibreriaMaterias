package edu.upvictoria.fpoo;

import java.io.*;
import java.util.ArrayList;

public class Leer {
    private ArrayList<String> contenido;

    public Leer() {
        contenido = new ArrayList<>();
    }

    public void leerContenidoArchivo(File archivo) {
        String line;
        try {
            FileReader fr = new FileReader(archivo.getPath());
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                contenido.add(line);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void leerCarpeta(File carpeta) {
        try{
            for (File archivo : carpeta.listFiles()) {
                if (archivo.isDirectory()) {
                    leerCarpeta(archivo);
                } else {
                    leerContenidoArchivo(archivo);
                }
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public ArrayList<String> obtenerContenido() {
        return contenido;
    }

    public void clearContenido(){
        contenido = new ArrayList<>();
    }

}
