package modelo;

public class Usuario {

    private int idUsuario;
    private String username;
    private String nombreAsignado;
    private String password;
    private String estado;
    private String rol;          
    private int nivelAcceso;     

    public Usuario() {
    }

    public Usuario(int idUsuario, String username, String nombreAsignado, String password, String estado, String rol, int nivelAcceso) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.nombreAsignado = nombreAsignado;
        this.password = password;
        this.estado = estado;
        this.rol = rol;
        this.nivelAcceso = nivelAcceso;
    }

    

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombreAsignado() {
        return nombreAsignado;
    }

    public void setNombreAsignado(String nombreAsignado) {
        this.nombreAsignado = nombreAsignado;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getNivelAcceso() {
        return nivelAcceso;
    }

    public void setNivelAcceso(int nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }

    @Override
    public String toString() {
        return nombreAsignado + " (" + username + ") - Rol: " + rol + ", Nivel: " + nivelAcceso;
    }
}
