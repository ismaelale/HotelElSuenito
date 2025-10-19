/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Ismael
 */
public class CalculoFactura {
    
    private double subtotal;
    private double isv;
    private double total;
    
    public CalculoFactura(){
        
    }
    
    public CalculoFactura(double subtotal, double isv, double total){
        
        this.subtotal = subtotal;
        this.isv = isv;
        this.total = total;
        
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIsv() {
        return isv;
    }

    public void setIsv(double isv) {
        this.isv = isv;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
    
}
