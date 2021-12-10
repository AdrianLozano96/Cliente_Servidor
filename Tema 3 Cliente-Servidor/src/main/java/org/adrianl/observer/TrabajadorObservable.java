package org.adrianl.observer;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

public class TrabajadorObservable extends Observable implements Runnable {

    private String nombre;
    private int edad;
    private String profesion;
    private boolean parar = false;
    private Observer miron;
    private int tiempo;

    public TrabajadorObservable() {
        nombre = "Paco";
        edad = 45;
        profesion = "Jefe de una Empresa Internacionalmente Importante";
    }
    public TrabajadorObservable(String nombre, int edad, String profesion) {
        this.nombre = nombre;
        this.edad = edad;
        this.profesion = profesion;
    }

    @Override
    public void run() {

        while(!parar) {
            edad++;
            tiempo++;
            notifyObservers();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(edad==67){
                parar=true;
                System.out.println("Felicidades "+getNombre()+" Es hora de Jubilarse. Has trabajado "+tiempo+" Años");
            }
        }

    }

    @Override
    public synchronized void addObserver(Observer o) {
        miron = o;
    }

    @Override
    public void notifyObservers() {
        if(miron!=null){
            miron.update(this,"actualizacion");
        }
    }

    public void notifyObserversNombre() {
        if(miron!=null){
            miron.update(this,"nombre");
        }
    }


    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
        notifyObserversNombre();
    }


    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }


    public String getProfesion() {
        return profesion;
    }
    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }


    @Override
    public String toString() {
        return "Trabajador con los datos: " +
                "nombre = '" + nombre + '\'' +
                ", edad = " + edad +
                ", profesion = '" + profesion + '\'';
    }


}
