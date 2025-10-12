/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Productos;
import vista.Gestion_Productos;
import vista.Inventario_Productos;

/**
 *
 * @author Ismael
 */
public class Productos_Controlador implements ActionListener{
    
   private Gestion_Productos GestionProductos;
   private Inventario_Productos InventarioProductos;
   
   
   public Productos_Controlador(Gestion_Productos GestionProductos, Inventario_Productos InventarioProductos){
       this.GestionProductos = GestionProductos;
       this.InventarioProductos = InventarioProductos;
       
       
       
       this.GestionProductos.btnInventario.addActionListener(this);
   }
   
   @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == GestionProductos.btnInventario){
            InventarioProductos.setVisible(true);
            GestionProductos.dispose();
        }
        
    }
    
    




    
}//FINAL DE LA CLASE 

