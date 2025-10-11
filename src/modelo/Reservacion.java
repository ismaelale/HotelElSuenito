package modelo;

public class Reservacion {

    private int idReservacion;
    private int idCliente;
    private String nombreCliente;    
    private String tipoHabitacion;
    private int numHabitacion;
    private String fechaEntrada;
    private String fechaSalida;
    private String estadoReserva;

    public Reservacion() {
    }

    public Reservacion(int idReservacion, int idCliente, String nombreCliente, String tipoHabitacion,
            int numHabitacion, String fechaEntrada, String fechaSalida, String estadoReserva) {
        this.idReservacion = idReservacion;
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.tipoHabitacion = tipoHabitacion;
        this.numHabitacion = numHabitacion;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.estadoReserva = estadoReserva;
    }

    public int getIdReservacion() {
        return idReservacion;
    }

    public void setIdReservacion(int idReservacion) {
        this.idReservacion = idReservacion;
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

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public int getNumHabitacion() {
        return numHabitacion;
    }

    public void setNumHabitacion(int numHabitacion) {
        this.numHabitacion = numHabitacion;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getEstadoReserva() {
        return estadoReserva;
    }

    public void setEstadoReserva(String estadoReserva) {
        this.estadoReserva = estadoReserva;
    }
}
