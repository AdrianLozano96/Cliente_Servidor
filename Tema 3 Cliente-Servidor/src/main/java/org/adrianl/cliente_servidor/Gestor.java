package org.adrianl.cliente_servidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Gestor extends Thread{

    Socket socket;
    ProductoCS productoEnviar;
    ProductoCS productoRecibir = new ProductoCS("Otro producto más", 76);;
/*
    public Gestor (Socket s, ProductoCS p){
        socket = s;
        productoRecibir = p;
        //productoEnviar = new ProductoCS();
    }

 */

    public Gestor (Socket s){
        socket = s;
    }



    @Override
    public void run() {
        ObjectOutputStream salir = null;
        ObjectInputStream entrar = null;
        try{
            socket.setSoLinger(true, 10);

            entrar = new ObjectInputStream(socket.getInputStream());
            productoEnviar = (ProductoCS) entrar.readObject();
            System.out.println("Se recibe "+productoEnviar.toString());

            productoEnviar.setNombre("Nuevo Productooo");

            salir = new ObjectOutputStream(socket.getOutputStream());
            salir.writeObject(productoRecibir);
            salir.writeObject(productoEnviar);

            salir.close();
            entrar.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
