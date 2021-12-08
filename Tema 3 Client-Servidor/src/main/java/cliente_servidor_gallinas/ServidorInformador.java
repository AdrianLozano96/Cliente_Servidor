package cliente_servidor_gallinas;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

class ServidorInformador extends Thread {
    private int puerto = 5555;
    private ServerSocket servidor= null;
    Socket cliente = null;
    int numClientes = 0;
    Compartido comp;

    public ServidorInformador(Compartido compartido){
        this.comp = compartido;
    }

    @Override
    public void run(){
        try {

            servidor = new ServerSocket(puerto);


            while (!comp.getSalir()) {
                // Se acepata una conexion con un cliente. Esta llamada se queda
                // bloqueada hasta que se arranque el cliente.
                System.out.println("Servidor->Listo. Esperando cliente...");
                cliente = servidor.accept();
                GestionClientes gc = new GestionClientes(cliente, comp);
                gc.start();
            }
            // Se cierra el socket encargado de aceptar clientes.
            // Se cierra el socket con el cliente.
            cliente.close();
            servidor.close();
            System.out.println("Servidor->Cerrando la conexión");
            // Se cierra el fichero

        } catch (IOException ex) {
            Logger.getLogger(ServidorInformador.class.getName()).log(Level.SEVERE, null, ex);
            //} catch (InterruptedException ex) {
            //    Logger.getLogger(ServidorInformador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
