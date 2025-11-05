/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Ismael
 */
public class ProductosVendidos_DAO extends Conexion{
    
    public void VistaProductosVendidos(JTable tablaproductosvendidos){
        
        try(Connection con = (Connection)getConnection()){
            
            DefaultTableModel modelo = (DefaultTableModel) tablaproductosvendidos.getModel();
            modelo.setRowCount(0);
            String consulta = "select *from TotalProductosVendidos";
            PreparedStatement ps = con.prepareStatement(consulta);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                modelo.addRow(new Object[]{
                rs.getString("Nombre_Producto"),
                rs.getDate("Fecha_Compra"),
                rs.getInt("Cantidad_Comprar"),
                rs.getDouble("Total"),
                rs.getString("Metodo_Pago")
                
                });
               
            }
            tablaproductosvendidos.setModel(modelo);
            
        }catch(SQLException e){
            System.out.println(e);
        }
        
    }
    
}
