package ElSuenito;

import vista.Inicio_de_sesion;
import controlador.LoginControlador;
import vista.Administracion_de_usuarios;
import controlador.UsuarioRegistroControlador;
import javax.swing.SwingUtilities;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Inicio_de_sesion vistaLogin = new Inicio_de_sesion();
            LoginControlador controlador = new LoginControlador(vistaLogin);
            
            vistaLogin.setLocationRelativeTo(null);
            vistaLogin.setResizable(false);
            vistaLogin.setVisible(true);
            
            
           
        });
    }
}
