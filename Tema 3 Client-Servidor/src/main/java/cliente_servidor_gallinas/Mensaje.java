package cliente_servidor_gallinas;

import java.io.Serializable;
import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

public class Mensaje implements Serializable {
    private int ID;
    private int paquete;
    private int yemas;
    private long instante;
    private String cad;
    private int n;

    public Mensaje(int ID, int paquete){
        this.ID = ID;
        this.paquete = paquete;
        this.yemas = ThreadLocalRandom.current().nextInt(1, 4);
        this.instante = Instant.now().getEpochSecond();

    }

    // Voy a contar los huevos de dos yemas porque me aburre hacer dos veces lo mismo
    public int getYemas(){
        return yemas;
    }

    @Override
    public String toString() {
        return "{"+ID+" - "+paquete+" - "+yemas+" - "+instante+"}";
    }
}