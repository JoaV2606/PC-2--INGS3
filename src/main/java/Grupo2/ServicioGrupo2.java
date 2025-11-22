
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
    
    public static boolean validarDescuentoAplicable(Producto p, double porcentaje) {
    return p.isDescuentoAplicable() && porcentaje >= 0 && porcentaje <= 50;
}
}

