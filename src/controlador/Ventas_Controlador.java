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
import javax.swing.JOptionPane;
import modelo.CalculoFactura;
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
    
    Ventas_Dao ventasdao = new Ventas_Dao();
    
    
    public Ventas_Controlador(Venta_Productos ventaProductos){
        this.ventaProductos = ventaProductos;
        
        
        this.ventaProductos.txtBuscarProdcuto.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent evt){
                
                ProductosDao productosDao = new ProductosDao();
                String nombreproducto = ventaProductos.txtBuscarProdcuto.getText();
                productosDao.BusquedaProductos(nombreproducto, ventaProductos.TablaProductosVentas);
                
            }
        });
        
        //Seleccion de combo box si es tarjeta o efectivo
        this.ventaProductos.cbMetodoPago.addActionListener(new ActionListener(){            
            public void actionPerformed(ActionEvent e){
                String metodo = (String) ventaProductos.cbMetodoPago.getSelectedItem();
                if(metodo.equals("Efectivo")){
                    ventaProductos.txtMonto.setEnabled(true);
                    ventaProductos.txtCambio.setEnabled(true);
                }
                else{
                    ventaProductos.txtMonto.setEnabled(false);
                    ventaProductos.txtCambio.setEnabled(false);
                }
            }
        });
        
        this.ventaProductos.txtMonto.addKeyListener(new KeyAdapter(){
        
            public void keyPressed(KeyEvent evt){
                if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                
                    String metodo = (String) ventaProductos.cbMetodoPago.getSelectedItem();
                    if(metodo.equals("Efectivo")){
                        ventaProductos.txtMonto.setEnabled(true);
                        ventaProductos.txtCambio.setEnabled(true);

                        double total = Double.parseDouble(ventaProductos.txtTotal.getText());
                        double monto = Double.parseDouble(ventaProductos.txtMonto.getText());


                        double cambio = monto - total;

                        if(cambio < 0){
                            JOptionPane.showMessageDialog(null, "Monto Insuficiente");
                        }

                        ventaProductos.txtCambio.setText(String.format("%.2f", cambio));

                    }
                    else{
                        ventaProductos.txtMonto.setEnabled(false);
                        ventaProductos.txtCambio.setEnabled(false);
                        ventaProductos.txtMonto.setText("");
                        ventaProductos.txtCambio.setText("");
                    }
                    
                }
                
            }
        });
        
        
        
        
        this.ventaProductos.btAgregar.addActionListener(this);
        this.ventaProductos.btEliminar.addActionListener(this);
        this.ventaProductos.btnAggVenta.addActionListener(this);
        this.ventaProductos.btCalcular.addActionListener(this);
        this.ventaProductos.btnSalir.addActionListener(this);
        
    }
    
    public void actualizarTabla() {
        ProductosDao productosDao = new ProductosDao();
        productosDao.ActualizaTabla(ventaProductos.TablaProductosVentas); // Llenamos la tabla
    }
    
    @Override
    public void actionPerformed(ActionEvent evt){
        
        if(evt.getSource() == ventaProductos.btAgregar){
           
            //VALIDACION DE CANTIDAD A COMPRAR
            int cantidadcompra = 0;
            try{
                cantidadcompra = Integer.parseInt(ventaProductos.txtCantidad.getText());
                if(cantidadcompra < 0){
                    cantidadcompra = 1;
                }
            }catch(NumberFormatException e){
                System.out.println(e);
            }
            
            
            //Ventas_Dao ventasdao = new Ventas_Dao();
            ventasdao.AñadirListaCompras(ventaProductos.TablaProductosVentas, ventaProductos.TablaListaCompras, cantidadcompra);
            
            ventaProductos.txtBuscarProdcuto.setText("");
            ventaProductos.txtCantidad.setText("");
            ventaProductos.cbMetodoPago.setSelectedIndex(0);
            
        }
        if(evt.getSource() == ventaProductos.btEliminar){
            
//            Ventas_Dao ventasdao = new Ventas_Dao();
            ventasdao.EliminarProductoLista(ventaProductos.TablaListaCompras);
            
        }
        if(evt.getSource() == ventaProductos.btnAggVenta){
//            VentasModelo ventasmodelo = new VentasModelo();
            Date fechacompra = ventaProductos.dateFechaCompra.getDate();
            if(fechacompra == null){
                fechacompra = new Date();
            }
            
            int cantidadcompra = 0;
            try{
                cantidadcompra = Integer.parseInt(ventaProductos.txtCantidad.getText());
                if(cantidadcompra < 0){
                    cantidadcompra = 1;
                }
            }catch(NumberFormatException nb){
                System.out.println(nb);
            }
            
            String metodopago = String.valueOf(ventaProductos.cbMetodoPago.getSelectedItem());
            
//            Ventas_Dao ventasdao = new Ventas_Dao();
            
            ventasdao.AñadirVenta(ventaProductos.TablaProductosVentas, fechacompra, cantidadcompra, metodopago);
        }
        
        //BOTON PARA CALCULAR EL SUBTOTAL, IMPUESTO Y TOTAL DE LOS PRODUCTOS QUE ESTAN EN LA LISTA
        if(evt.getSource() == ventaProductos.btCalcular){
            
            CalculoFactura calculo = ventasdao.calculos(ventaProductos.TablaListaCompras);
            
            ventaProductos.txtSubTotal.setText(String.format("%.2f", calculo.getSubtotal()));
            ventaProductos.txtIsv.setText(String.format("%.2f", calculo.getIsv()));
            ventaProductos.txtTotal.setText(String.format("%.2f", calculo.getTotal()));

            
        }
        
        if(evt.getSource() == ventaProductos.btnSalir){
            ventaProductos.dispose();
            ventasdao.MostrarDatosProductos(ventaProductos.TablaProductosVentas);
            
        }
        
        
    }
    
}
