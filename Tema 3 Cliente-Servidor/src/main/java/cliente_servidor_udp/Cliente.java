package cliente_servidor_udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Cliente {
    // Los argumentos proporcionan el mensaje y el nombre del servidor
    public static void main(String args[]) {
        String men= "Hola Soy un cliente UDP";
        try {
            //1º Crear el Socket con DataGramSocket
            DatagramSocket socketUDP = new DatagramSocket();
            //2º Petición a enviar, ip desde la que nos conectamos, puerto al que establecer la conexión
            byte[] mensaje = men.getBytes();
            InetAddress hostServidor = InetAddress.getByName("localhost");
            int puertoServidor = 6789;
            //3º Petición a enviar con DatagramPacket pasandole (mensaje a enviar, longitud del mensaje, ip, puerto)
            DatagramPacket peticion =
                    new DatagramPacket(mensaje, men.length(), hostServidor, // Construimos un datagrama para enviar el mensaje al servidor
                            puertoServidor);
            //4º Se envia el paquete por el socket
            socketUDP.send(peticion);   // Enviamos el datagrama
            //5º Se prepara la respuesta del servidor con un datagra que tendrá (byte[], longitudud del byte[])
            byte[] bufer = new byte[1000];
            DatagramPacket respuesta =
                    new DatagramPacket(bufer, bufer.length);    // Construimos el DatagramPacket que contendrá la respuesta
            //6º Se obtiene la respuesta del servidor recibiendo el DatagramPacket por el socket
            socketUDP.receive(respuesta);
            //7º Enviamos la respuesta del servidor a la salida estandar
            System.out.println("Respuesta: " + new String(respuesta.getData()).trim());
            //8º Cerramos el socket
            socketUDP.close();
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }
}
