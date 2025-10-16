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

/**
 *
 * @author Ismael
 */
public class Ventas_Dao extends Conexion{
    
    private Productos productos;
    private VentasModelo ventasmodelo;
    
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
    
    
    
}
