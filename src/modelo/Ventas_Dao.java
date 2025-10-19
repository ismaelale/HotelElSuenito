/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.awt.event.ActionEvent;
import modelo.VentasModelo;
import vista.Venta_Productos;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

/**
 *
 * @author Ismael
 */
public class Ventas_Dao extends Conexion{
    
    private Productos productos;
    
    
    
    public void MostrarDatosProductos(JTable tabla){
        
        DefaultTableModel modelotabla = (DefaultTableModel) tabla.getModel();
        modelotabla.setRowCount(0);
        
        String consulta = "select *from productos";
        
        try(Connection con = (Connection) getConnection()){
            
            PreparedStatement ps = con.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                modelotabla.addRow(new Object[]{
                rs.getInt("IDProducto"),
                rs.getString("Nombre_Producto"),
                rs.getInt("Stock_Inicial"),
                rs.getDouble("Precio_Venta")
                });
            }
            
            tabla.setModel(modelotabla);
            
        }catch(SQLException e){
            System.out.println(e);
            
        }
        
    }
    
    public void AñadirListaCompras(JTable tabla, JTable tablalista, int cantidadcompra){
        
        VentasModelo ventasmodelo = new VentasModelo();
        Venta_Productos ventaProductos = new Venta_Productos();
        
        
        try(Connection con = (Connection)getConnection()){
            
            int filaseleccionada = tabla.getSelectedRow();
            DefaultTableModel modelolista = (DefaultTableModel) tablalista.getModel();
            
            if(filaseleccionada != -1){
                
                String nombreproducto = tabla.getValueAt(filaseleccionada, 1).toString();
                int idproducto = Integer.parseInt(tabla.getValueAt(filaseleccionada, 0).toString());
                double preciounitario = Double.parseDouble(tabla.getValueAt(filaseleccionada, 3).toString());
                
                int stockactual = Integer.parseInt(tabla.getValueAt(filaseleccionada, 2).toString());
                
                double total = preciounitario * cantidadcompra;
                
                if(stockactual > cantidadcompra){
                    modelolista.addRow(new Object[]{cantidadcompra, nombreproducto, idproducto, preciounitario, total});
                    tablalista.getModel();
                }else{
                    JOptionPane.showMessageDialog(null, "Stock Insuficiente");
                }
                
                 
            }
            
        }catch(SQLException e){
            System.out.println(e);
        }
        
        
    }
    
    public void AñadirVenta(JTable tabla, Date fechacompra, int cantidadcompra, String metodopago){
        
        VentasModelo ventasmodelo = new VentasModelo();
        Venta_Productos ventaproductos = new Venta_Productos();
        
        try(Connection con = (Connection) getConnection()){
            
            int filaseleccionada = tabla.getSelectedRow();
            
            if(filaseleccionada != -1){
                int idproducto = Integer.parseInt(tabla.getValueAt(filaseleccionada, 0).toString());
                int stockactual = Integer.parseInt(tabla.getValueAt(filaseleccionada, 2).toString());
                double preciounitario = Double.parseDouble(tabla.getValueAt(filaseleccionada, 3).toString());
                
                ventasmodelo.setIdproducto(idproducto);
                ventasmodelo.setFecha_compra(fechacompra);
                ventasmodelo.setStock_actual(stockactual);
                ventasmodelo.setCantidad_compra(cantidadcompra);
                ventasmodelo.setPrecio_unitario(preciounitario);
                ventasmodelo.setMetodo_pago(metodopago);
                
                String consulta = "call VALIDACION_VENTAS(?,?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(consulta);

                ps.setInt(1, ventasmodelo.getIdproducto());
                ps.setDate(2, new java.sql.Date(ventasmodelo.getFecha_compra().getTime()));
                ps.setInt(3, ventasmodelo.getStock_actual());
                ps.setInt(4, ventasmodelo.getCantidad_compra());
                ps.setDouble(5, ventasmodelo.getPrecio_unitario());
                ps.setString(6, ventasmodelo.getMetodo_pago());
                
                ResultSet rs = ps.executeQuery();
                
                if(rs.next()){
                    String mensaje = rs.getString(1);
                    JOptionPane.showMessageDialog(null, mensaje);
                }
                
            } 
            else{
                JOptionPane.showMessageDialog(null, "Seleccione");
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        
    }
    
    
    
    public void EliminarProductoLista(JTable tabla){
        
        int filaseleccionada = tabla.getSelectedRow();
        if(filaseleccionada == -1){
            JOptionPane.showMessageDialog(null, "Seleccione una fila en la lista");
        }else{
            TableModel m = tabla.getModel();
            ((DefaultTableModel)m).removeRow(filaseleccionada);
        }
        
    }
    
    public CalculoFactura calculos(JTable tablalistacompras){
        
        double subtotal = 0.0, isv = 0.0, total = 0.0;
        
        DefaultTableModel modelolista = (DefaultTableModel) tablalistacompras.getModel();
        
        for(int i = 0; i < modelolista.getRowCount(); i++){
            total += Double.parseDouble(modelolista.getValueAt(i, 4).toString());
        }
        
        
        subtotal = total / (1 + 0.15);
        isv = total - subtotal;
        
        
        
        return new CalculoFactura(subtotal, isv, total);
        
    }
    
    
    
}
