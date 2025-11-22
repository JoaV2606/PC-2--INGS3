/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Grupo2;

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
public class ServicioGrupo2Test {
    private List<Producto> productos=new ArrayList<>();
    private Producto pValido;
    private Producto pSinStock;
   
    @BeforeEach
    public void iniciar(){
        pValido=new Producto("panetón", 20, 2);
        pSinStock=new Producto("leche", 5, 0);
        productos.add(pValido);
        productos.add(pSinStock);
    }
    
    @Test
    public void montoFueraDelLimiteTest(){
        assertFalse(ServicioGrupo2.verificarLimite(5200));
    }
    
    @Test
    public void descuentoFueraDelLimiteTest(){
        assertFalse(ServicioGrupo2.validarDescuento(55));
    }
    
    @Test
    public void aplicarIGVTest(){
        double esperado=236;
        double actual=ServicioGrupo2.calcularIGV(200);
        assertEquals(esperado, actual);
    }
    
    @Test
    public void clienteVacioTest(){
        assertFalse(ServicioGrupo2.validarCliente(""));
    }
  
    @Test
    public void listaConCantidadProductosInvalidaTest(){
        assertFalse(ServicioGrupo2.verificarStock(productos));
    }
}
