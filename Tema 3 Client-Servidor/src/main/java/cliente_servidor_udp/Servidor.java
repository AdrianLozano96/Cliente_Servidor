package cliente_servidor_udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Servidor {
    public static void main (String args[]) {
        try {
            //1º Crear el Socket con DataGramSocket y pasarle el purto al que conectarse
            DatagramSocket socketUDP = new DatagramSocket(6789);
            //2º Peticiones a recibir
            byte[] bufer = new byte[1000];
            while (true) {
                //3º Construimos el DatagramPacket para recibir peticiones
                DatagramPacket peticion =
                        new DatagramPacket(bufer, bufer.length);
                //4º Leemos una petición del DatagramSocket
                socketUDP.receive(peticion);
                System.out.print("Datagrama recibido del host: " +
                        peticion.getAddress());
                System.out.println(" desde el puerto remoto: " +
                        peticion.getPort());
                //5º Construimos el DatagramPacket para enviar la respuesta
                DatagramPacket respuesta =
                        new DatagramPacket(peticion.getData(), peticion.getLength(),
                                peticion.getAddress(), peticion.getPort());
                //6º Enviamos la respuesta, que es un eco
                socketUDP.send(respuesta);
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }

}

