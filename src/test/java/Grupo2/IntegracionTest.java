package Grupo2;

import Base.Pedido;
import Modelo.Producto;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class IntegracionTest {
    private Pedido pedido;
    private Producto productoConDescuento;
    private Producto productoSinDescuento;

    @BeforeEach
    public void iniciar() {
        pedido = new Pedido();
        productoConDescuento = new Producto("Laptop", 1000.0, 5, "SKU001", "Tecnología", true, true);
        productoSinDescuento = new Producto("Mouse", 50.0, 10, "SKU002", "Tecnología", true, false);
    }

    @Test
    public void flujoExitoso_ProductosValidosConDescuentoAplicable() {
        assertTrue(pedido.agregarProducto(productoConDescuento, 2));
        assertTrue(pedido.agregarProducto(productoSinDescuento, 3));
        assertTrue(pedido.validarStock());
        assertTrue(ServicioGrupo2.validarDescuentoAplicable(productoConDescuento, 10));
    }

    @Test
    public void errorPorDuplicado_ProductoConMismoSKU() {
        assertTrue(pedido.agregarProducto(productoConDescuento, 2));
        assertFalse(pedido.agregarProducto(productoConDescuento, 3)); // Mismo SKU
        
        assertTrue(pedido.validarStock());
        
        List<Producto> productos = List.of(productoConDescuento);
        double total = Pedido.calcularTotalPedidoIntegrado(productos, 10, "Cliente Test");
        assertTrue(total > 0);
    }

    @Test
    public void stockInvalido_ServicioOperaSobreProductosValidos() {
        Producto productoSinStock = new Producto("Sin Stock", 100.0, 0, "SKU003", "General", true, true);
        assertTrue(pedido.agregarProducto(productoSinStock, 0));
        assertFalse(pedido.validarStock()); // Stock inválido
        
        List<Producto> productosInvalidos = List.of(productoSinStock);
        assertThrows(IllegalArgumentException.class, () -> {
            Pedido.calcularTotalPedidoIntegrado(productosInvalidos, 10, "Cliente Test");
        });
    }

    @Test
    public void integracionCorrectaTest() {
        List<Producto> productos = List.of(productoConDescuento, productoSinDescuento);
        double total = Pedido.calcularTotalPedidoIntegrado(productos, 10, "Dante");
        boolean resultado = ServicioGrupo2.verificarLimite(total);
        assertTrue(resultado);
    }
}