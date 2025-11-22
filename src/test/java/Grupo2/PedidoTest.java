package Grupo2;

import Base.Pedido;
import Modelo.Producto;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PedidoTest {
    private Pedido pedido;
    private Producto producto1;
    private Producto producto2;
    private Producto productoInactivo;

    @BeforeEach
    public void iniciar() {
        pedido = new Pedido();
        producto1 = new Producto("Laptop", 1200.50, 10, "SKU001", "Tecnología", true, true);
        producto2 = new Producto("Mouse", 25.99, 50, "SKU002", "Tecnología", true, false);
        productoInactivo = new Producto("Producto Viejo", 100.0, 5, "SKU003", "General", false, true);
    }

    
    @Test
    public void agregarProducto_CantidadNoValida_DeberiaRetornarFalse() {
        assertFalse(pedido.agregarProducto(producto1, 0));
        assertFalse(pedido.agregarProducto(producto1, -5));
    }

    @Test
    public void agregarProducto_ProductoDuplicado_DeberiaRetornarFalse() {
        assertTrue(pedido.agregarProducto(producto1, 2));
        assertFalse(pedido.agregarProducto(producto1, 3)); 
    }

    @Test
    public void agregarProducto_AgregadoCorrecto_DeberiaRetornarTrueYAgregar() {
        assertTrue(pedido.agregarProducto(producto1, 3));
        assertEquals(1, pedido.getDetallesPedido().size());
        assertEquals("SKU001", pedido.getDetallesPedido().get(0).getSku());
    }

    @Test
    public void agregarProducto_RespetoDeAtributos_DeberiaPreservarAtributos() {
        assertTrue(pedido.agregarProducto(producto1, 5));
        
        Producto productoAgregado = pedido.getDetallesPedido().get(0);
        assertEquals("Laptop", productoAgregado.getNombre());
        assertEquals(1200.50, productoAgregado.getPrecio(), 0.001);
        assertEquals(5, productoAgregado.getCantidad());
        assertEquals("SKU001", productoAgregado.getSku());
        assertEquals("Tecnología", productoAgregado.getCategoria());
        assertTrue(productoAgregado.isEsActivo());
        assertTrue(productoAgregado.isDescuentoAplicable());
    }

    @Test
    public void agregarProducto_ProductoInactivo_DeberiaPermitirAgregado() {
        assertTrue(pedido.agregarProducto(productoInactivo, 2));
        assertFalse(pedido.getDetallesPedido().get(0).isEsActivo());
    }

    
    @Test
    public void validarStock_ListaVacia_DeberiaRetornarTrue() {
        assertTrue(pedido.validarStock());
    }

    @Test
    public void validarStock_TodosConStockValido_DeberiaRetornarTrue() {
        pedido.agregarProducto(producto1, 5);
        pedido.agregarProducto(producto2, 10);
        assertTrue(pedido.validarStock());
    }

    @Test
    public void validarStock_UnoConStockCero_DeberiaRetornarFalse() {
        Producto productoSinStock = new Producto("Sin Stock", 50.0, 0, "SKU004", "General", true, false);
        pedido.agregarProducto(producto1, 5);
        pedido.agregarProducto(productoSinStock, 0);
        assertFalse(pedido.validarStock());
    }

    @Test
    public void validarStock_CantidadNegativa_DeberiaSerImposiblePorValidacion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Producto("Producto Inválido", 10.0, -5, "SKU005", "General", true, false);
        });
    }

    @Test
    public void validarStock_ValoresLimite_DeberiaManejarCorrectamente() {
        Producto productoLimite1 = new Producto("Límite Bajo", 10.0, 1, "SKU006", "General", true, false);
        Producto productoLimite2 = new Producto("Límite Alto", 20.0, 999, "SKU007", "General", true, false);
        
        pedido.agregarProducto(productoLimite1, 1);
        pedido.agregarProducto(productoLimite2, 999);
        
        assertTrue(pedido.validarStock());
    }

    @Test
    public void listaProductosVaciaTest(){
        assertThrows(IllegalArgumentException.class, ()->{
            Pedido.calcularTotalPedido(List.of(), 10);
        });
    }
}