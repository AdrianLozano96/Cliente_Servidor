package cliente_servidor_gallinas;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class Compartido implements Serializable {
    private boolean salir = false;
    private FileWriter fichero = null;
    private int huevos = 0; // Ahora solo voy a contar los de dos yemas, porque me apetece :)
    private int limite = 100;
    private int numCliente = 1; // Contador de clientes

    public Compartido(){
        this.salir= false;
        this.huevos = 0;
        try {
            this.fichero = new FileWriter("informe.txt");
        } catch (IOException ex) {
            System.err.println("Compartido->Error al cear fichero");
        }
    }
    public synchronized void setSalir(boolean salir){
        this.salir = salir;
    }

    public synchronized boolean getSalir(){
        return this.salir;
    }

    public synchronized void setHuevos(int huevos){
        this.huevos = huevos;
    }

    public synchronized int getHuevos(){
        return this.huevos;
    }

    public synchronized void cerrarFichero() throws IOException{
        fichero.close();
    }

    public synchronized void escribirFichero(String cad) throws IOException{
        fichero.write(cad + "\n");
    }

    public synchronized void procesarEntrada(Mensaje dato) throws IOException {
        // aumentamos los huevos porque es un paquete
        this.huevos = this.huevos+6;

        //escribimos en el fichero
        fichero = new FileWriter("informe.txt",true);
        this.escribirFichero(dato.toString());
        fichero.close();

        // Cojo las yemas
        this.huevos+=dato.getYemas();
        if(this.huevos>this.limite){
            this.salir=true;
        }
    }

    public synchronized void nuevoCliente(){
        this.numCliente++;
    }

    public synchronized int getCliente(){
        return this.numCliente;
    }

}
