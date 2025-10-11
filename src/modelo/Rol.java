package modelo;

public class Rol {
    private int idRol;
    private String descripcionRol;

    public Rol(int idRol, String descripcionRol) {
        this.idRol = idRol;
        this.descripcionRol = descripcionRol;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getDescripcionRol() {
        return descripcionRol;
    }

    public void setDescripcionRol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
    }

    @Override
    public String toString() {
        return descripcionRol;
    }
}
