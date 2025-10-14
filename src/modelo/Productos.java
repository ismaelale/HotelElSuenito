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
    
    private int IDProducto;
    private String NombreProducto;
    private int StockInicial;
    private int StockMin;
    private int StockMax;
    private String Descripcion;
    private double PrecioCompra;
    private double PrecioVenta;
    
    public Productos(){}
    
    public Productos(int IDProducto, String NombreProducto, int StockInicial, int StockMin, int StockMax, String Descripcion, double PrecioCompra, double PrecioVenta){
     
        this.IDProducto = IDProducto;
        this.NombreProducto = NombreProducto;
        this.StockInicial = StockInicial;
        this.StockMin = StockMin;
        this.StockMax = StockMax;
        this.Descripcion = Descripcion;
        this.PrecioCompra = PrecioCompra;
        this.PrecioVenta = PrecioVenta;
     
    }
    
    public int getIDProducto(){
        return IDProducto;
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

    public int getStockMin() {
        return StockMin;
    }

    public int getStockMax() {
        return StockMax;
    }

    public void setIDProducto(int IDProducto){
        this.IDProducto = IDProducto;
    }
    public void setNombreProducto(String NombreProducto) {
        this.NombreProducto = NombreProducto;
    }

    public void setStockInicial(int StockInicial) {
        this.StockInicial = StockInicial;
    }

    public void setStockMin(int StockMin) {
        this.StockMin = StockMin;
    }

    public void setStockMax(int StockMax) {
        this.StockMax = StockMax;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public void setPrecioCompra(double PrecioCompra) {
        this.PrecioCompra = PrecioCompra;
    }

    public void setPrecioVenta(double PrecioVenta) {
        this.PrecioVenta = PrecioVenta;
    }

    
   
    
}
