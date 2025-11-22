/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Grupo2;

import Base.Pedido;
import Modelo.Producto;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author user
 */
public class PedidoTest {
    private List<Producto> productos=new ArrayList<>();
    private Producto paneton, leche, sinPrecio;
   
    @BeforeEach
    public void iniciar(){
        paneton=new Producto("panetón", 20, 2);
        leche=new Producto("leche", 5, 1);
        sinPrecio=new Producto("Sin Precio", 0, 1);
        productos.add(paneton);
        productos.add(leche);
    }
    
    @Test
    public void listaProductosVaciaTest(){
        assertThrows(IllegalArgumentException.class, ()->{
            Pedido.calcularTotalPedido(List.of(), 10);
        });
    }
    
    @Test
    public void subtotalCeroTest(){
        assertThrows(IllegalArgumentException.class, ()->{
            Pedido.calcularTotalPedido(List.of(sinPrecio), 10);
        });
    }
    
    @Test
    public void descuentoValidoTest(){
        double esperado=40.5;
        double actual=Pedido.calcularTotalPedido(productos, 10);
        assertEquals(esperado, actual);
    }
    
    @Test
    public void descuentoExtremoTest(){
        double esperado=45;
        double actual=Pedido.calcularTotalPedido(productos, 0);
        assertEquals(esperado, actual);
    }
}
