/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Grupo2;

import Base.Pedido;
import Modelo.Producto;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author user
 */
public class IntegracionTest {
    private List<Producto> productos=new ArrayList<>(), productosInvalidos=new ArrayList<>();
    private Producto paneton;
    private Producto leche;
   
    @BeforeEach
    public void iniciar(){
        paneton=new Producto("panetón", 20, 2);
        leche=new Producto("leche", 5, 1);
        productos.add(paneton);
        productos.add(leche);
    }
    
    @Test
    public void integracionCorrectaTest() {
        double total = Pedido.calcularTotalPedidoIntegrado(productos, 10, "Dante");
        boolean resultado = ServicioGrupo2.verificarLimite(total);
        assertTrue(resultado);
    }

    @Test
    public void errorFuncionBaseTest() {
        assertThrows(IllegalArgumentException.class, ()->{
            Pedido.calcularTotalPedidoIntegrado(productosInvalidos, 10, "Dante");
        });
    }
    
    @Test
    public void errorFuncionSecundariaTest() {
        assertThrows(IllegalArgumentException.class, ()->{
            Pedido.calcularTotalPedidoIntegrado(productos, 10, "");
        });
    }
    
    @Test
    public void valorLimiteDescuentoTest() {
        assertDoesNotThrow(()->{
            Pedido.calcularTotalPedidoIntegrado(productos, 50, "Dante");
        });
    }
    
    @Test
    public void combinacionValidacionesTest() {
        assertThrows(IllegalArgumentException.class, () ->{
            Pedido.calcularTotalPedidoIntegrado(productosInvalidos, -1, "");
        });
    }
}
