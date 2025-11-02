/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import modelo.Productos;
import modelo.ProductosDao;
import modelo.ProductosVendidosM;
import vista.ProductosVendidos;

/**
 *
 * @author Ismael
 */
public class ProductosVendidos_Contrrolador {
   
    ProductosDao productosdao = new ProductosDao();
    private ProductosVendidos productosvendidosVista;
    
    public ProductosVendidos_Contrrolador(ProductosVendidos productosvendidosVista){
        this.productosvendidosVista = productosvendidosVista;
        
        
    }
    
    public void MostrarProductos(){
        
        ArrayList<Productos> lista = productosdao.obtenerproductos();
        productosvendidosVista.cbProductos.removeAllItems();
        productosvendidosVista.cbProductos.addItem("Seleccione un producto...");
        for(Productos p : lista){
            productosvendidosVista.cbProductos.addItem(p.getNombreProducto());
        }
        
    }
    
}
