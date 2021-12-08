/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observer;

import java.util.GregorianCalendar;

/**
 *
 * @author link
 */
public class PatronObserver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
		System.out.println("Producto Observable");
        Producto producto = new Producto();
		producto.setNombre("Patatas");
		producto.setDescripcion("Patatas fritas");
		producto.setPrecio(1.20f);
		producto.setStock(0);

		System.out.println("Cliente Observer");
		Cliente cliente = new Cliente();
		cliente.setCodigo("CLI0001");
		cliente.setNombre("Un");
		cliente.setApellidos("tipo");
		cliente.setEmail("un@tipo.com");
		cliente.setFechaNacimiento(new GregorianCalendar().getTime());
		System.out.println("Se añade el observer a la observable");
		/*
		 * Se añaden observadores a la clase observable
		 */
		producto.addObserver(cliente);
		/*
		 * Un cambio en la clase observada hará "reaccionar" a la clase observadora
		 */
		producto.setStock(23);
		producto.setPrecio(5.60f);
    }
    
}
