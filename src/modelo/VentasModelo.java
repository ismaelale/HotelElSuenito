/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Ismael
 */
public class VentasModelo {
    
    private Date fecha_compra;
    private int stock_actual;
    private int cantidad_compra;
    private double precio_unitario;
    private String metodo_pago;
    
    public VentasModelo(Date fecha_compra, int stock_actual, int cantidad_compra, double precio_unitario, String metodo_pago){
        this.fecha_compra = fecha_compra;
        this.stock_actual = stock_actual;
        this.cantidad_compra = cantidad_compra;
        this.precio_unitario = precio_unitario;
        this.metodo_pago = metodo_pago;
    }

    
    public Date getFecha_compra() {
        return fecha_compra;
    }

    public int getStock_actual() {
        return stock_actual;
    }

    public int getCantidad_compra() {
        return cantidad_compra;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setFecha_compra(Date fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public void setStock_actual(int stock_actual) {
        this.stock_actual = stock_actual;
    }

    public void setCantidad_compra(int cantidad_compra) {
        this.cantidad_compra = cantidad_compra;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }
    
    
    
    
}
