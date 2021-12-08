package cliente_servidor_refrescos;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //1º Se indica la ip, el puerto y se crea el socket
        InetAddress direccion;
        Socket servidor;
        int puerto = 5555;
        System.out.println("¿Cuantos refrescos quieres?");
        Scanner sc = new Scanner(System.in);
        int numRefrescosPedir = sc.nextInt();

        try {
            direccion = InetAddress.getLocalHost(); // dirección local
            //direccion = InetAddress.getByName("172.17.209.114");
            //2º Se instancia el socket pasandole la ip y el puerto
            servidor = new Socket(direccion, puerto);//equivalente al send
            //servidor = new Socket("localhost", 5555);//equivalente al send
            System.out.println("Conectado a Servidor ...");

            //3º Se envia información
            //le envio el numero de refrescos que quiero
            DataOutputStream numRefrescos = new DataOutputStream(servidor.getOutputStream());
            numRefrescos.writeInt(numRefrescosPedir);
            //ObjectOutputStream enviarObjeto = new ObjectOutputStream (servidor.getOutputStream());
            //enviarObjetos.writeObject(claseSerializable);

            //4º Se obtiene, recibe información
            //recibo un array con las lista de refrescos
            ObjectInputStream listaRe = new ObjectInputStream(servidor.getInputStream());
            Maquina listaRefrescos = (Maquina) listaRe.readObject();    //Se guarda en la clase POJO Serializable con métodos synchronized
            //DataInputStream datos = new DtaInputStream(servidor.getInputStream());
            //System.out.println(datos.readUTF());

            //5º Se muestra la información recibida
            if (listaRefrescos.size() > 0) {
                for (int i = 0; i < listaRefrescos.size(); i++) {
                    System.out.println("Ha llegado un refresco de -> " + listaRefrescos.get(i).getNombre());
                }
            } else {
                System.out.println("Ya no hay más refrescos");
            }

            //6º Se cierra
            numRefrescos.close();
            listaRe.close();
            servidor.close();
            System.out.println("Desconectado ...");
        } catch (Exception e) {
            System.err.println("Servidor desconectado");
            //e.printStackTrace();
        }

    }
}
