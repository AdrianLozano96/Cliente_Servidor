package cliente_servidor_gallinas;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDron extends Thread {
    private final int puerto = 5555;
    private final String dir = "localhost";
    private int ID = 0;
    private int numPaquete = 1;

    public void run(){
        ObjectInputStream bufferObjetosEntrada = null;
        ObjectOutputStream bufferObjetosSalida = null;
        try {
            // Me conecto al servidor
            Socket servidor = new Socket(dir, puerto);
            System.out.println("Cliente->Conectado al servidor...");

            // Obtengo el ID
            bufferObjetosEntrada = new ObjectInputStream(servidor.getInputStream());
            ID = (int) bufferObjetosEntrada.readObject();
            System.out.println("Cliente["+ID+"]->Recibido mi ID del Servidor: "+ ID);

            boolean salida = false;
            // Ciclamos en bucle continuamente
            while(!salida){
                // Creo la salida y mando el paquete
                bufferObjetosSalida = new ObjectOutputStream(servidor.getOutputStream());
                Mensaje datoSalida = new Mensaje(ID,numPaquete);
                bufferObjetosSalida.writeObject(datoSalida);
                System.out.println("Cliente["+ID+"]->Enviado al Servidor: " + datoSalida.toString());

                //Se obtiene si debemos salir
                bufferObjetosEntrada = new ObjectInputStream(servidor.getInputStream());
                salida = (boolean) bufferObjetosEntrada.readObject();
                System.out.println("Cliente["+ID+"]->Recibido del Servidor salida: "+salida);

                this.numPaquete++;

                // Me echo a dormir 2 segundos
                Thread.sleep(200);
            }

            // Me desconecto
            servidor.close();
            System.out.println("Cliente["+ID+"]->Desconectado");
        } catch (IOException | ClassNotFoundException | InterruptedException ex) {
            Logger.getLogger(ClienteDron.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
