package org.adrianl.cliente_servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException  {

        InetAddress direccion;
        Socket servidor;
        int puerto = 7777;
        String dato =" Na, es un dato normal";


        try{
            direccion = InetAddress.getLocalHost();
            servidor = new Socket(direccion,puerto);
            System.out.println("Cliente conectado: "+servidor.getInetAddress()+" y del puerto "+servidor.getPort());
            ProductoCS producto1 = new ProductoCS("Un producto",57);
            //Envio
            ObjectOutputStream enviarObjeto = new ObjectOutputStream(servidor.getOutputStream());
            enviarObjeto.writeObject(producto1);
            System.out.println("El cliente ha enviado al servidor: "+producto1.toString());
            //Recibo
            ObjectInputStream reciboObjeto = new ObjectInputStream(servidor.getInputStream());
            ProductoCS nuevoProducto = (ProductoCS) reciboObjeto.readObject();
            System.out.println("El cliente ha recibido informacion: "+nuevoProducto.toString());

            System.out.println("Objeto Antiguo: "+producto1.toString()+"\n"+"Producto Nuevo: "+nuevoProducto.toString());

            enviarObjeto.close();
            reciboObjeto.close();
            servidor.close();
            System.out.println("El cliente se marcho");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
