package edu.upvictoria.fpoo;

import java.util.ArrayList;

public class Alumno {
    private int numeroMaterias=0, numeroReprobadas=0;
    private final String matricula, nombre;
    private final ArrayList<Float> calificaciones;

    public Alumno(String matricula, String nombre) {
        calificaciones = new ArrayList<>();
        this.matricula = matricula;
        this.nombre = nombre;
    }

    public String getMatricula(){
        return matricula;
    }

    public int getNumeroReprobadas() {
        return numeroReprobadas;
    }

    public void cargarMateria(Float calificacion){
        calificaciones.add(calificacion);
        if(calificacion<70) {
            numeroReprobadas++;
        }
        numeroMaterias++;
    }

    public String calcularPromedio(){
        float suma=0;
        for (float cal:calificaciones) {
            suma+=cal;
        }
        float promedioGeneral = suma / numeroMaterias;
        return String.format("%.2f", promedioGeneral);
    }

    public String toString() {
        return matricula + " "+nombre+" "+calcularPromedio()+" "+numeroReprobadas;
    }
}
