package Base;

import Modelo.Producto;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<Producto> detallesPedido;

    public Pedido() {
        this.detallesPedido = new ArrayList<>();
    }

    public boolean agregarProducto(Producto producto, int cantidad) {
        if (cantidad <= 0) {
            System.err.println("Error: La cantidad a agregar debe ser positiva.");
            return false;
        }

        boolean productoYaExiste = detallesPedido.stream()
                .anyMatch(p -> p.equals(producto));
        
        if (productoYaExiste) {
            return false;
        } else {
            Producto productoConCantidad = new Producto(
                producto.getNombre(), 
                producto.getPrecio(), 
                cantidad,
                producto.getSku(),
                producto.getCategoria(),
                producto.isEsActivo(),
                producto.isDescuentoAplicable()
            );
            detallesPedido.add(productoConCantidad);
            return true;
        }
    }

    public boolean validarStock() {
        if (detallesPedido.isEmpty()) {
            return true;
        }
        
        for (Producto producto : detallesPedido) {
            if (producto.getCantidad() <= 0) {
                return false;
            }
        }
        return true;
    }

    public List<Producto> getDetallesPedido() {
        return new ArrayList<>(detallesPedido);
    }

    public static double calcularTotalPedido(List<Producto> productos, double descuento) {
        if (productos == null || productos.isEmpty()) {
            throw new IllegalArgumentException("Error: no hay productos en el pedido");
        }
        double subtotal = productos.stream()
            .mapToDouble(p -> p.getPrecio() * p.getCantidad())
            .sum();
        if (subtotal <= 0) {
            throw new IllegalArgumentException("Error: monto inválido");
        }
        return subtotal - (subtotal * (descuento / 100));           
    }
    
    public static double calcularTotalPedidoIntegrado(List<Producto> productos, double descuento, String cliente) {
        double total;
        
        if(!Grupo2.ServicioGrupo2.validarDescuento(descuento)){
            throw new IllegalArgumentException("Error: Descuento fuera del límite");
        }
        
        if(!Grupo2.ServicioGrupo2.validarCliente(cliente)){
            throw new IllegalArgumentException("Error: Cliente vacío");
        }
        
        if(!Grupo2.ServicioGrupo2.verificarStock(productos)){
            throw new IllegalArgumentException("Error: Producto con cantidad inválida");
        }
        
        try {
            total = calcularTotalPedido(productos, descuento);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error en función base");            
        }
        
        return Grupo2.ServicioGrupo2.calcularIGV(total);
    }
}