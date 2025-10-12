/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.Inventario_Productos;

/**
 *
 * @author Ismael
 */
public class Inventario_Controlador implements ActionListener{
    
    private Inventario_Productos inventario;
    
    public Inventario_Controlador(Inventario_Productos inventario){
        
        this.inventario = inventario;
        
        
        this.inventario.btnBuscar.addActionListener(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        
        
    }
    
    
}
