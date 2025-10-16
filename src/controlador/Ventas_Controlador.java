/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import modelo.ProductosDao;
import modelo.Ventas_Dao;
import vista.Venta_Productos;

/**
 *
 * @author Ismael
 */
public class Ventas_Controlador implements ActionListener{
    
    
    private Venta_Productos ventaProductos;
    
    public Ventas_Controlador(Venta_Productos ventaProductos){
        this.ventaProductos = ventaProductos;
        
        
        
        this.ventaProductos.txtBuscarProdcuto.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent evt){
                
                ProductosDao productosDao = new ProductosDao();
                String nombreproducto = ventaProductos.txtBuscarProdcuto.getText();
                productosDao.BusquedaProductos(nombreproducto, ventaProductos.TablaProductosVentas);
                
            }
        
        
        
        });
        
        
        
        
        
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent evt){
        
    }
    
}
