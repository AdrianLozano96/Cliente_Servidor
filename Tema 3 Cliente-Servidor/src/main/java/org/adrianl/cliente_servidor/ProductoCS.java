package org.adrianl.cliente_servidor;

import java.io.Serializable;

public class ProductoCS implements Serializable {

    private String nombre;
    private int precio;

    public ProductoCS() {
    }

    public ProductoCS(String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto con los datos: " +
                " nombre = '" + nombre + '\'' +
                ", precio = " + precio;
    }
}
