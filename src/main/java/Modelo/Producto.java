package Modelo;

import java.util.Objects;

public class Producto {
    private String nombre;
    private double precio;
    private int cantidad;
    private String sku;
    private String categoria;
    private boolean esActivo;
    private boolean descuentoAplicable;

    // Constructores
    public Producto(String nombre, double precio, int cantidad) {
        this(nombre, precio, cantidad, "SKU_DEFAULT", "General", true, false);
    }

    public Producto(String nombre, double precio, int cantidad, String sku, 
                   String categoria, boolean esActivo, boolean descuentoAplicable) {
        setNombre(nombre);
        setPrecio(precio);
        setCantidad(cantidad);
        setSku(sku);
        setCategoria(categoria);
        setEsActivo(esActivo);
        setDescuentoAplicable(descuentoAplicable);
    }

    // Getters y Setters con validaciones
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { 
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.precio = precio; 
    }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { 
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }
        this.cantidad = cantidad; 
    }

    public String getSku() { return sku; }
    public void setSku(String sku) { 
        if (sku == null || sku.trim().isEmpty()) {
            throw new IllegalArgumentException("El SKU no puede ser nulo o vacío");
        }
        this.sku = sku; 
    }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public boolean isEsActivo() { return esActivo; }
    public void setEsActivo(boolean esActivo) { this.esActivo = esActivo; }

    public boolean isDescuentoAplicable() { return descuentoAplicable; }
    public void setDescuentoAplicable(boolean descuentoAplicable) { 
        this.descuentoAplicable = descuentoAplicable; 
    }

    // equals y hashCode basados en SKU
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(sku, producto.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }

    // toString legible
    @Override
    public String toString() {
        return String.format("Producto{nombre='%s', precio=%.2f, cantidad=%d, sku='%s', categoria='%s', activo=%s, descuento=%s}",
                nombre, precio, cantidad, sku, categoria, esActivo, descuentoAplicable);
    }
}