package org.adrianl.cliente_servidor;

import java.net.ServerSocket;
import java.net.Socket;

public class TiendaServidor {
    public static void main(String[] args) {
        ServerSocket servidor;
        Socket cliente;
        int puerto = 7777;

        System.out.println("Servidor en funcionamiento");
        try{
            servidor = new ServerSocket(puerto);
            do{
                System.out.println("Conectando ...");
                cliente = servidor.accept();

                //ProductoCS producto2 = new ProductoCS("Otro producto más", 76);
                //Gestor gestion = new Gestor(cliente, producto2);
                Gestor gestion = new Gestor(cliente);
                gestion.start();

                servidor.close();
                System.out.println("Fin de la conexión");
            }while(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
