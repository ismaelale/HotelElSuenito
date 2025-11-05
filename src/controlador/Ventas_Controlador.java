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
        
        
        //BUSQUEDA POR CAMPO DE TEXTO DE UN PRODUCTO QUE QUEREMOS BUSCAR POR EL NOMBRE
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
        
        //AL MOMENTO DE PRESIONAR ENTER, EN EL CAMPO DE TEXTO DE MONTO, 
//        NOS DARÁ EL RESULTADO DEL CAMBIO QUE TIENE QUE RECIBIR EL CLIENTE
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
                            ventaProductos.txtCambio.setText("");
                            return;
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
        
        
        
        //ACCIONES DE BOTONES 
        this.ventaProductos.btAgregar.addActionListener(this);
        this.ventaProductos.btEliminar.addActionListener(this);
        this.ventaProductos.btnAggVenta.addActionListener(this);
        this.ventaProductos.btCalcular.addActionListener(this);
        this.ventaProductos.btnSalir.addActionListener(this);
        this.ventaProductos.btLimpiarDatos.addActionListener(this);
        
    }
    
    public void actualizarTabla() {
        ProductosDao productosDao = new ProductosDao();
        productosDao.ActualizaTabla(ventaProductos.TablaProductosVentas); // Llenamos la tabla
    }
    
    @Override
    public void actionPerformed(ActionEvent evt){
        
        if(evt.getSource() == ventaProductos.btAgregar){
            
            int fila = ventaProductos.TablaProductosVentas.getSelectedRow();
            
            if(ventaProductos.cbMetodoPago.getSelectedIndex() == 0 || ventaProductos.txtCantidad.getText().isEmpty()){
                
                JOptionPane.showMessageDialog(null, "CAMPOS VACIOS CANTIDAD/METODO DE PAGO");
                
            }
            else{
                
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
                ventasdao.MostrarDatosProductos(ventaProductos.TablaProductosVentas);
            }
           
        }
        if(evt.getSource() == ventaProductos.btEliminar){
            
//            Ventas_Dao ventasdao = new Ventas_Dao();
            ventasdao.EliminarProductoLista(ventaProductos.TablaListaCompras);
            
        }
        
        //AGREGAR LA VENTA EN LA BASE DE DATOS 
        if(evt.getSource() == ventaProductos.btnAggVenta){
//            VentasModelo ventasmodelo = new VentasModelo();
            Date fechacompra = ventaProductos.dateFechaCompra.getDate();
            if(fechacompra == null){
                fechacompra = new Date();
            }
            
            String metodopago = String.valueOf(ventaProductos.cbMetodoPago.getSelectedItem());
            
//            Ventas_Dao ventasdao = new Ventas_Dao();
            if(!ventaProductos.txtIsv.getText().isEmpty() || !ventaProductos.txtMonto.getText().isEmpty() || !ventaProductos.txtTotal.getText().isEmpty() || 
                !ventaProductos.txtSubTotal.getText().isEmpty()){
                
                ventasdao.AñadirVenta(ventaProductos.TablaListaCompras, fechacompra, metodopago);
                ventasdao.Limpiar(ventaProductos.TablaListaCompras);
                ventaProductos.cbMetodoPago.setSelectedIndex(0);
                ventaProductos.txtBuscarProdcuto.setText("");
                ventaProductos.txtCantidad.setText("");
                ventaProductos.txtCambio.setText("");
                ventaProductos.txtIsv.setText("");
                ventaProductos.txtMonto.setText("");
                ventaProductos.txtSubTotal.setText("");
                ventaProductos.txtTotal.setText("");
                
            }else{
                JOptionPane.showMessageDialog(null, "Campos vacios");
            }
                
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
            ventasdao.Limpiar(ventaProductos.TablaListaCompras);
            ventaProductos.txtBuscarProdcuto.setText("");
            ventaProductos.txtCantidad.setText("");
            ventaProductos.cbMetodoPago.setSelectedIndex(0);
            
        }
        if(evt.getSource() == ventaProductos.btLimpiarDatos){
            ventasdao.Limpiar(ventaProductos.TablaListaCompras);
            ventaProductos.cbMetodoPago.setSelectedIndex(0);
            ventaProductos.txtBuscarProdcuto.setText("");
            ventaProductos.txtCantidad.setText("");
            ventaProductos.txtCambio.setText("");
            ventaProductos.txtIsv.setText("");
            ventaProductos.txtMonto.setText("");
            ventaProductos.txtSubTotal.setText("");
            ventaProductos.txtTotal.setText("");
            
        }
        
        
    }
    
}
