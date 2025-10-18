/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import modelo.ProductosDao;
import modelo.VentasModelo;
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
        
        
        this.ventaProductos.btAgregar.addActionListener(this);
        
        
        
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent evt){
        
        if(evt.getSource() == ventaProductos.btAgregar){
           
            Date fechacompra = ventaProductos.dateFechaCompra.getDate();
            int cantidadcompra = Integer.parseInt(ventaProductos.txtCantidad.getText());
            String metodopago = String.valueOf(ventaProductos.cbMetodoPago.getSelectedItem());
            
            //MODELOS
            VentasModelo ventasmodelo = new VentasModelo();
            ventasmodelo.setFecha_compra(fechacompra);
            ventasmodelo.setCantidad_compra(cantidadcompra);
            ventasmodelo.setMetodo_pago(metodopago);
            
            
            Ventas_Dao ventasdao = new Ventas_Dao();
            ventasdao.AñadirProductoElegido(ventaProductos.TablaProductosVentas);
            
            
        }
        
    }
    
}
