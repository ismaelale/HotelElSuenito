/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Ismael
 */
public class Productos {
    
    private String NombreProducto;
    private int StockInicial;
    private String Descripcion;
    private double PrecioCompra;
    private double PrecioVenta;
    
    public Productos(String NombreProducto, int StockInicial, String Descripcion, double PrecioCompra, double PrecioVenta){
     
        this.NombreProducto = NombreProducto;
        this.StockInicial = StockInicial;
        this.Descripcion = Descripcion;
        this.PrecioCompra = PrecioCompra;
        this.PrecioVenta = PrecioVenta;
     
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public int getStockInicial() {
        return StockInicial;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public double getPrecioCompra() {
        return PrecioCompra;
    }

    public double getPrecioVenta() {
        return PrecioVenta;
    }

    public void setStockInicial(int StockInicial) {
        this.StockInicial = StockInicial;
    }

    public void setPrecioCompra(double PrecioCompra) {
        this.PrecioCompra = PrecioCompra;
    }

    public void setPrecioVenta(double PrecioVenta) {
        this.PrecioVenta = PrecioVenta;
    }
    
    
    
    
}
