/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grupo2;

import Modelo.Producto;
import java.util.List;

/**
 *
 * @author user
 */
public class ServicioGrupo2 {
    public static boolean verificarLimite(double total){
        return total<=5000;
    }
    
    public static boolean validarDescuento(double descuento){
        return descuento>=0 && descuento<=50;
    }
    
    public static double calcularIGV(double total){
        return total*1.18;
    }
    
    public static boolean validarCliente(String nombre) {
        return nombre != null && !nombre.trim().isEmpty();
    }
    public static boolean verificarStock(List<Producto> productos){
        return productos.stream()
            .allMatch(p -> p.getCantidad() > 0);
    }
}
