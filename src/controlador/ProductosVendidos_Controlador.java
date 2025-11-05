/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import modelo.Productos;
import modelo.ProductosDao;
import vista.ProductosVendidos;

/**
 *
 * @author Ismael
 */
public class ProductosVendidos_Controlador implements ActionListener{
    
    private ProductosDao productosdao;
    private ProductosVendidos productosVendidos;
    
    public ProductosVendidos_Controlador(ProductosVendidos productosVendidos){
        
        this.productosVendidos = productosVendidos;
        
        
        
        this.productosVendidos.btnSalir.addActionListener(this);
    }
    
//    public void cargarProductos(){
//        
//        productosVendidos.cbFiltrarDatos.addItem("Seleccione una opción...");
//        productosVendidos.cbFiltrarDatos.addItem("Total de Productos Vendidos Segun la Fecha");
//        productosVendidos.cbFiltrarDatos.addItem("Total de Productos Vendidos segun el Mes");
//        productosVendidos.cbFiltrarDatos.addItem("Total de Productos Vendidos segun el Año");
//        
//    }
     
    
    
    
    @Override
    public void actionPerformed(ActionEvent evt){
        if(evt.getSource() == productosVendidos.btnSalir){
            productosVendidos.dispose();
        }
        
    }
}


