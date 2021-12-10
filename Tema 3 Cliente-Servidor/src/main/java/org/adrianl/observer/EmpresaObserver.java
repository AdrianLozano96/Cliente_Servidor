package org.adrianl.observer;

import java.util.Observable;
import java.util.Observer;

public class EmpresaObserver extends Thread implements Observer {

    public EmpresaObserver(String nombre) {
        super(nombre);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg.equals("actualizacion")){
            System.out.println(((TrabajadorObservable)o).toString());
        }
        if(arg.equals("nombre")){
            System.out.println("El empledado se ha cambiado el nombre por el de "+((TrabajadorObservable)o).getNombre());
        }

    }

}
