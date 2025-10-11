package modelo;

public class Acceso {
    private int idAcceso;
    private int nivel;

    public Acceso(int idAcceso, int nivel) {
        this.idAcceso = idAcceso;
        this.nivel = nivel;
    }

    public int getIdAcceso() {
        return idAcceso;
    }

    public void setIdAcceso(int idAcceso) {
        this.idAcceso = idAcceso;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return "Nivel " + nivel;
    }
}
