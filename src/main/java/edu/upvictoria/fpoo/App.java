package edu.upvictoria.fpoo;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Enlistador enlistar = new Enlistador();
        String direccion, separador;
        if(args.length>=1){
            enlistar.acomodar(args[0],",");
        }else{
            System.out.print("Ingrese la direccion de la carpeta a leer o un documento index:");
            direccion = scanner.next();
            System.out.print("Ingrese el separador de los documentos:");
            separador = scanner.next();
            enlistar.acomodar(direccion,separador);
        }

    }
}