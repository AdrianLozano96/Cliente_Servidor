package org.adrianl.observer;

/**
 * Hello world!
 *
 */
public class AppObserver
{
    public static void main( String[] args ) throws InterruptedException {

        System.out.println( "Observer Observable" );
        EmpresaObserver empresa = new EmpresaObserver("PSP.SA");
        TrabajadorObservable trabajador1 = new TrabajadorObservable("Jose",38,"repartidor");
        TrabajadorObservable trabajador2 = new TrabajadorObservable();
        TrabajadorObservable trabajador3 = new TrabajadorObservable("Manolo",28,"piloto");
        trabajador1.addObserver(empresa);
        trabajador2.addObserver(empresa);
        trabajador3.addObserver(empresa);
        Thread hilo1 = new Thread(trabajador1);
        Thread hilo2 = new Thread(trabajador2);
        Thread hilo3 = new Thread(trabajador3);
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo1.join();
        hilo2.join();
        hilo3.join();
        System.out.println("Fin del programa");

    }
}
