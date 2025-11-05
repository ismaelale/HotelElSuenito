/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import modelo.Productos;
import modelo.ProductosDao;
import modelo.ProductosVendidos_DAO;
import modelo.Ventas_Dao;
import vista.Gestion_Productos;
import vista.ProductosVendidos;
import vista.Venta_Productos;

/**
 *
 * @author Ismael
 */

public class Productos_Controlador implements ActionListener{
    
   private Gestion_Productos GestionProductos;
   private Venta_Productos VentaProductos;
   private ProductosVendidos productosVendidos;
   
   ProductosDao productosdao = new ProductosDao();
   public Productos_Controlador(Gestion_Productos GestionProductos, Venta_Productos VentaProductos, ProductosVendidos productosVendidos){
       this.GestionProductos = GestionProductos;
       this.VentaProductos = VentaProductos;
       this.productosVendidos = productosVendidos;
       
       this.GestionProductos.btnRegistrar.addActionListener(this);
       this.GestionProductos.btsalir.addActionListener(this);
       this.GestionProductos.btnActualizar.addActionListener(this);
       this.GestionProductos.btnEliminar.addActionListener(this);
       this.GestionProductos.btnBusquedaProductos.addActionListener(this);
       this.GestionProductos.btnVender.addActionListener(this);
       this.GestionProductos.btProductosVendidos.addActionListener(this);
       
       
       this.GestionProductos.TablaProductos.addMouseListener(new MouseAdapter(){
          public void mouseClicked(MouseEvent e){
              llenarCamposDesdeTabla();
          } 
           
       });
       
       this.GestionProductos.txtBuscarProducto.addKeyListener(new KeyAdapter(){
           public void keyReleased(KeyEvent evt){
               String nombreproducto = GestionProductos.txtBuscarProducto.getText();
               productosdao.BusquedaProductos(nombreproducto, GestionProductos.TablaProductos);
           }
       
       });
       
   }
   
    public void actualizarTabla() {
        ProductosDao productosDao = new ProductosDao();
        productosDao.ActualizaTabla(GestionProductos.TablaProductos); // Llenamos la tabla
    }
   
    
    
    private void llenarCamposDesdeTabla() {
    int fila = GestionProductos.TablaProductos.getSelectedRow();
    if(fila != -1) { // Si se ha seleccionado una fila
        GestionProductos.txtIdProducto.setText(GestionProductos.TablaProductos.getValueAt(fila, 0).toString());
        GestionProductos.txtNombreProducto.setText(GestionProductos.TablaProductos.getValueAt(fila, 1).toString());
        GestionProductos.txtStockInicial.setText(GestionProductos.TablaProductos.getValueAt(fila, 2).toString());
        GestionProductos.txtStockMin.setText(GestionProductos.TablaProductos.getValueAt(fila, 3).toString());
        GestionProductos.txtStockMax.setText(GestionProductos.TablaProductos.getValueAt(fila, 4).toString());
        GestionProductos.txtDescripcion.setText(GestionProductos.TablaProductos.getValueAt(fila, 5).toString());
        GestionProductos.txtPrecioCompra.setText(GestionProductos.TablaProductos.getValueAt(fila, 6).toString());
        GestionProductos.txtPrecioVenta.setText(GestionProductos.TablaProductos.getValueAt(fila, 7).toString());
    }
    
}
   
   @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == GestionProductos.btnRegistrar){
            
            try{
                if(!GestionProductos.txtNombreProducto.getText().isEmpty() &&
                   !GestionProductos.txtStockInicial.getText().isEmpty() &&
                   !GestionProductos.txtStockMax.getText().isEmpty() &&
                   !GestionProductos.txtStockMin.getText().isEmpty() &&
                   !GestionProductos.txtPrecioCompra.getText().isEmpty() &&
                   !GestionProductos.txtPrecioVenta.getText().isEmpty()){
                    
                    //datos de la vista
                    String nombre = GestionProductos.txtNombreProducto.getText();
                    int stockinicial = Integer.parseInt(GestionProductos.txtStockInicial.getText());
                    int stockmin = Integer.parseInt(GestionProductos.txtStockMin.getText());
                    int stockmax = Integer.parseInt(GestionProductos.txtStockMax.getText());
                    String descr = GestionProductos.txtDescripcion.getText();
                    double precioCompra = Double.parseDouble(GestionProductos.txtPrecioCompra.getText());
                    double precioventa = Double.parseDouble(GestionProductos.txtPrecioVenta.getText());

                    //Passarlos al modelo
                    Productos products = new Productos();
                    products.setNombreProducto(nombre);
                    products.setStockInicial(stockinicial);
                    products.setStockMin(stockmin);
                    products.setStockMax(stockmax);
                    products.setDescripcion(descr);
                    products.setPrecioCompra(precioCompra);
                    products.setPrecioVenta(precioventa);

                    

                    productosdao.AggProductos(products);
                    actualizarTabla();
                      
                    GestionProductos.txtNombreProducto.setText("");
                    GestionProductos.txtStockInicial.setText("");
                    GestionProductos.txtStockMin.setText("");
                    GestionProductos.txtStockMax.setText("");
                    GestionProductos.txtDescripcion.setText("");
                    GestionProductos.txtPrecioCompra.setText("");
                    GestionProductos.txtPrecioVenta.setText("");
                    GestionProductos.txtIdProducto.setText("");
                
                
                }else{
                    JOptionPane.showMessageDialog(null, "Ingrese datos en todos los campos");
                }
            }catch(Exception ex){
                System.out.println(ex);
            }
            
        }
        if(e.getSource() == GestionProductos.btnActualizar){
            
            try{
                
                if(!GestionProductos.txtNombreProducto.getText().isEmpty() &&
                    !GestionProductos.txtStockInicial.getText().isEmpty() &&
                    !GestionProductos.txtStockMax.getText().isEmpty() &&
                    !GestionProductos.txtStockMin.getText().isEmpty() &&
                    !GestionProductos.txtPrecioCompra.getText().isEmpty() &&
                    !GestionProductos.txtPrecioVenta.getText().isEmpty()){
                    
                    //datos de la vista
                    int idproducto = Integer.parseInt(GestionProductos.txtIdProducto.getText());
                    String nombre = GestionProductos.txtNombreProducto.getText();
                    int stockinicial = Integer.parseInt(GestionProductos.txtStockInicial.getText());
                    int stockmin = Integer.parseInt(GestionProductos.txtStockMin.getText());
                    int stockmax = Integer.parseInt(GestionProductos.txtStockMax.getText());
                    String descr = GestionProductos.txtDescripcion.getText();
                    double precioCompra = Double.parseDouble(GestionProductos.txtPrecioCompra.getText());
                    double precioventa = Double.parseDouble(GestionProductos.txtPrecioVenta.getText());

                    //Passarlos al modelo
                    Productos products = new Productos();

                    products.setIDProducto(idproducto);
                    products.setNombreProducto(nombre);
                    products.setStockInicial(stockinicial);
                    products.setStockMin(stockmin);
                    products.setStockMax(stockmax);
                    products.setDescripcion(descr);
                    products.setPrecioCompra(precioCompra);
                    products.setPrecioVenta(precioventa);

                    productosdao.UpdateProductos(products);
                    actualizarTabla();
                    
                    GestionProductos.txtNombreProducto.setText("");
                    GestionProductos.txtStockInicial.setText("");
                    GestionProductos.txtStockMin.setText("");
                    GestionProductos.txtStockMax.setText("");
                    GestionProductos.txtDescripcion.setText("");
                    GestionProductos.txtPrecioCompra.setText("");
                    GestionProductos.txtPrecioVenta.setText("");
                    GestionProductos.txtIdProducto.setText("");
                
                    
                }else{
                    JOptionPane.showMessageDialog(null, "Seleccione una fila para Actualizar");
                }
                
            }catch(Exception ex){
                
            }
            
        }
        if(e.getSource() == GestionProductos.btnEliminar){
            //datos de la vista
            
            if(!GestionProductos.txtNombreProducto.getText().isEmpty() &&
                    !GestionProductos.txtStockInicial.getText().isEmpty() &&
                    !GestionProductos.txtStockMax.getText().isEmpty() &&
                    !GestionProductos.txtStockMin.getText().isEmpty() &&
                    !GestionProductos.txtPrecioCompra.getText().isEmpty() &&
                    !GestionProductos.txtPrecioVenta.getText().isEmpty()){
            
                int idproducto = Integer.parseInt(GestionProductos.txtIdProducto.getText());

                //Passarlos al modelo
                Productos products = new Productos();
                products.setIDProducto(idproducto);

                productosdao.Eliminar(products);
                actualizarTabla();

                GestionProductos.txtNombreProducto.setText("");
                GestionProductos.txtStockInicial.setText("");
                GestionProductos.txtStockMin.setText("");
                GestionProductos.txtStockMax.setText("");
                GestionProductos.txtDescripcion.setText("");
                GestionProductos.txtPrecioCompra.setText("");
                GestionProductos.txtPrecioVenta.setText("");
                GestionProductos.txtIdProducto.setText("");
            }else{
                JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA");
            }
                
            
        }
        
        if(e.getSource() == GestionProductos.btnBusquedaProductos){
            String nombre = GestionProductos.txtBuscarProducto.getText();
            Productos productos = new Productos();
            
            productosdao.BusquedaProductos(nombre, GestionProductos.TablaProductos);
            
            
        }
        if(e.getSource() == GestionProductos.btnVender){
            Ventas_Dao ventasdao = new Ventas_Dao();
            ventasdao.MostrarDatosProductos(VentaProductos.TablaProductosVentas);
            VentaProductos.setVisible(true);
            
        }
        
        
        if(e.getSource() == GestionProductos.btsalir){
            GestionProductos.dispose();
        }
        if(e.getSource() == GestionProductos.btProductosVendidos){
            ProductosVendidos_DAO productosvendidosdao = new ProductosVendidos_DAO();
            productosvendidosdao.VistaProductosVendidos(productosVendidos.tbProductosVendidos);
            productosVendidos.setVisible(true);
            productosVendidos.setLocationRelativeTo(null);
            
        }
        
        
    }
    
    
    
    




    
}//FINAL DE LA CLASE 

