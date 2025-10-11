package modelo;

import java.util.ArrayList;

public class CompraHab_POO {

    private int idCompra;
    private int idCliente;
    private String nombreCliente;
    private String fechaIngreso;
    private String fechaSalida;  
    private String metodoPago;
    private double subtotal;
    private double impuesto;
    private double total;
    private double efectivoRecibido;
    private double cambio;

    
    private ArrayList<HabitacionSeleccionada> habitacionesSeleccionadas;

    
    public CompraHab_POO() {
        habitacionesSeleccionadas = new ArrayList<>();
    }

    
    public CompraHab_POO(int idCompra, int idCliente, String nombreCliente,
                         String tipoHabitacion, String numHabitacion, double precio,
                         String fechaIngreso, String fechaSalida) {
        this.idCompra = idCompra;
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.habitacionesSeleccionadas = new ArrayList<>();
        this.habitacionesSeleccionadas.add(new HabitacionSeleccionada(tipoHabitacion, numHabitacion, precio));
    }

   

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getEfectivoRecibido() {
        return efectivoRecibido;
    }

    public void setEfectivoRecibido(double efectivoRecibido) {
        this.efectivoRecibido = efectivoRecibido;
    }

    public double getCambio() {
        return cambio;
    }

    public void setCambio(double cambio) {
        this.cambio = cambio;
    }

    public ArrayList<HabitacionSeleccionada> getHabitacionesSeleccionadas() {
        return habitacionesSeleccionadas;
    }

    public void setHabitacionesSeleccionadas(ArrayList<HabitacionSeleccionada> habitacionesSeleccionadas) {
        this.habitacionesSeleccionadas = habitacionesSeleccionadas;
    }

   

    public String getTipoHabitacion() {
        if (!habitacionesSeleccionadas.isEmpty()) {
            return habitacionesSeleccionadas.get(0).getTipo();
        }
        return null;
    }

    public String getNumHabitacion() {
        if (!habitacionesSeleccionadas.isEmpty()) {
            return habitacionesSeleccionadas.get(0).getNumero();
        }
        return null;
    }

    public double getPrecio() {
        if (!habitacionesSeleccionadas.isEmpty()) {
            return habitacionesSeleccionadas.get(0).getPrecio();
        }
        return 0.0;
    }

    
    public static class HabitacionSeleccionada {
        private String tipo;
        private String numero;
        private double precio;

        public HabitacionSeleccionada(String tipo, String numero, double precio) {
            this.tipo = tipo;
            this.numero = numero;
            this.precio = precio;
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public String getNumero() {
            return numero;
        }

        public void setNumero(String numero) {
            this.numero = numero;
        }

        public double getPrecio() {
            return precio;
        }

        public void setPrecio(double precio) {
            this.precio = precio;
        }
    }
}
