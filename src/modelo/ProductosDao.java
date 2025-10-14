/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Ismael
 */
public class ProductosDao extends Conexion{
    
    private Productos productos;
    
    public void AggProductos(Productos productos){

        Connection con = (Connection) getConnection();
        
        String consulta = "insert into productos(Nombre_Producto, Stock_Inicial, Stock_Min, Stock_Max, Descripcion, Precio_Compra, Precio_Venta) values (?,?,?,?,?,?,?)";
        
        try{
            PreparedStatement ps = con.prepareStatement(consulta);
            ps.setString(1, productos.getNombreProducto());
            ps.setInt(2, productos.getStockInicial());
            ps.setInt(3, productos.getStockMin());
            ps.setInt(4, productos.getStockMax());
            ps.setString(5, productos.getDescripcion());
            ps.setDouble(6, productos.getPrecioCompra());
            ps.setDouble(7, productos.getPrecioVenta());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "PRODUCTO AGREGADO");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "PRODUCTO NO AGREGADO");
            System.out.println(e);
        }

    }
    
    public void ActualizaTabla(JTable tabla) {
    // Obtenemos el modelo de la tabla
        Connection con = (Connection) getConnection();
    
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        String consulta = "select *from productos";
        
        try{
            PreparedStatement ps = con.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                modelo.addRow(new Object[]{
                rs.getInt("IDProducto"),
                rs.getString("Nombre_Producto"),
                rs.getInt("Stock_Inicial"),
                rs.getInt("Stock_Min"),
                rs.getInt("Stock_Max"),
                rs.getString("Descripcion"),
                rs.getDouble("Precio_Compra"),
                rs.getDouble("Precio_Venta")
                
                });
                
            }
            
            tabla.setModel(modelo);
            
        }catch(SQLException e){
            
        }
    
    }
 
    public void UpdateProductos(Productos products){
        
        Connection con = (Connection) getConnection();
        String consulta = "update productos set Nombre_Producto = ?, Stock_Inicial = ?, Stock_Min = ?, Stock_Max = ?, Descripcion = ?, Precio_Compra = ?, Precio_Venta = ? where IDProducto = ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(consulta);
            ps.setString(1, products.getNombreProducto());
            ps.setInt(2, products.getStockInicial());
            ps.setInt(3, products.getStockMin());
            ps.setInt(4, products.getStockMax());
            ps.setString(5, products.getDescripcion());
            ps.setDouble(6, products.getPrecioCompra());
            ps.setDouble(7, products.getPrecioVenta());
            ps.setInt(8, products.getIDProducto());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "PRODUCTO ACTUALIZADO");
            
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Producto no Actualizado");
            System.out.println(e);
        }
    }
    
    public void Eliminar(Productos productos){
        
        Connection con = (Connection) getConnection();
        String consulta = "delete from productos where IDProducto = ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(consulta);
            ps.setInt(1, productos.getIDProducto());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Producto Eliminado");
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Producto No eliminado");
        }
        
        
    }
    
}
