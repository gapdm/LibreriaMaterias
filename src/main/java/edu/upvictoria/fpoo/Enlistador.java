package edu.upvictoria.fpoo;

import java.io.File;
import java.util.*;

public class Enlistador {
    public static Leer leer = new Leer();
    public static File doc;
    public static ArrayList<String> contenidoGlobal,contenidoTemporal;
    public void acomodar(String direccion, String regexSeparator) {
        Alumno alumno;
        HashMap<String,Alumno> alumnos = new HashMap<>();
        ArrayList<String[]> contenidoSpliteado = new ArrayList<>();
        leerIndex(direccion);
        for (String line : contenidoGlobal) {
            contenidoSpliteado.add(line.split(regexSeparator));
        }
        for (String[] arrayTemp : contenidoSpliteado) {
            String[] calificaciones = Arrays.copyOfRange(arrayTemp, 2, arrayTemp.length);
            float promedio=0, suma = 0;
            for (String calificacion : calificaciones) {
                if(Integer.parseInt(calificacion)>=70){
                    suma += Integer.parseInt(calificacion);
                }else{
                    promedio = 60;
                }
            }
            if(promedio == 0){
                promedio = suma / calificaciones.length;
            }
            if (alumnos.isEmpty()) {
                alumno = new Alumno(arrayTemp[0], arrayTemp[1]);
                alumno.cargarMateria(promedio);
                alumnos.put(alumno.getMatricula(), alumno);
            }else{
                alumno = new Alumno(arrayTemp[0], arrayTemp[1]);
                if(alumnos.containsKey(alumno.getMatricula())){
                    alumno = alumnos.get(alumno.getMatricula());
                    alumno.cargarMateria(promedio);
                }else{
                    alumno.cargarMateria(promedio);
                    alumnos.put(alumno.getMatricula(), alumno);
                }
            }
        }
        List<Alumno> alumnosOrdenados = new ArrayList<>(alumnos.values());
        alumnosOrdenados.sort(Comparator.comparing(Alumno::calcularPromedio));
        Collections.reverse(alumnosOrdenados);
        alumnosOrdenados.sort(Comparator.comparing(Alumno::getNumeroReprobadas));
        for (Alumno aluTmp: alumnosOrdenados) {
            System.out.println(aluTmp);
        }
    }

    private static void leerIndex(String direccion){
        try{
            doc = new File(direccion);
            if(doc.isDirectory()){
                leer.leerCarpeta(doc);
            }else{
                leer.leerContenidoArchivo(doc);
            }
            contenidoTemporal = leer.obtenerContenido();
            for (String line: contenidoTemporal) {
                File doc2 = new File(line);
                leer.clearContenido();
                if(doc2.exists() && !doc2.isDirectory()){
                    insertarContenido(leer, doc2);
                }else if(doc2.isDirectory()){
                    leer.leerCarpeta(doc2);
                    if(contenidoGlobal == null){
                        contenidoGlobal=leer.obtenerContenido();
                    }else{
                        contenidoGlobal.addAll(leer.obtenerContenido());
                    }
                }else if(contenidoTemporal.size()>1){
                    contenidoGlobal = contenidoTemporal;
                }else{
                    System.err.println("El contenido de el archivo no es valido:");
                    System.out.println(contenidoTemporal);
                    System.exit(1);
                }
            }
        }catch(NullPointerException e){
            e.printStackTrace();
        }
    }

    private static void insertarContenido(Leer leer, File doc2) {
        leer.leerContenidoArchivo(doc2);
        contenidoTemporal = leer.obtenerContenido();
        contenidoGlobal = new ArrayList<>();
        for (String direccion: contenidoTemporal) {
            File doc3 = new File(direccion);
            leer.leerContenidoArchivo(doc3);
            if(contenidoGlobal.isEmpty()){
                contenidoGlobal=leer.obtenerContenido();
            }else{
                contenidoGlobal.addAll(leer.obtenerContenido());
            }
        }
    }
}