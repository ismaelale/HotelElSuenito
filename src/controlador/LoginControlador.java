package controlador;

import Controlador.Principal_Controlador;
import Controlador.Reservacion_Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Usuario;
import modelo.ConsultasDB;
import modelo.Clientes_POO;
import vista.Inicio_de_sesion;
import vista.Principal;
import vista.Gestion_Clientes;
import vista.Gestion_De_Habitaciones;
import modelo.CompraHab_POO;
import vista.Habitaciones;
import vista.Administracion_de_usuarios;
import modelo.HabitacionesModel;
import vista.ADMINS;
import vista.Reservaciones;
import Reportes.BotonesReportes;
import modelo.Reservacion;
import vista.AcercaDeNosotros;
import vista.Gestion_Productos;
import vista.Inventario_Productos;

public class LoginControlador implements ActionListener {

    private Inicio_de_sesion vista;
    private ConsultasDB ctrl;

    public LoginControlador(Inicio_de_sesion vista) {
        this.vista = vista;
        this.ctrl = new ConsultasDB();
        this.vista.btnLogin.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnLogin) {
            String usuario = vista.jtfUsuario.getText();
            String clave = new String(vista.jpContrasena.getPassword());

            Usuario u = ctrl.validarLogin(usuario, clave);

            if (u != null) {
                JOptionPane.showMessageDialog(vista, "Bienvenido " + u.getNombreAsignado());
                vista.dispose();

                
                Principal principal = new Principal(u.getNombreAsignado());
                Gestion_Clientes clientes = new Gestion_Clientes(u.getNombreAsignado());
                Clientes_POO clientePOO = new Clientes_POO();
                Cliente_Controlador clientesCtrl = new Cliente_Controlador(clientePOO, clientes, ctrl);

                Habitaciones habitaciones = new Habitaciones(u.getNombreAsignado());
                HabitacionesModel habModel = new HabitacionesModel();
                Habitaciones_Controlador habCtrl = new Habitaciones_Controlador(habModel, habitaciones, ctrl);

                Gestion_De_Habitaciones gestionHab = new Gestion_De_Habitaciones(u.getNombreAsignado());
                Compra_Controlador compraCtrl = new Compra_Controlador(gestionHab);

                Administracion_de_usuarios admins = new Administracion_de_usuarios(u.getNombreAsignado());
                UsuarioRegistroControlador usuariosCtrl = new UsuarioRegistroControlador(admins);

                ADMINS ad = new ADMINS(u.getNombreAsignado());
                ADMINS_Controlador adCtrl = new ADMINS_Controlador(principal, admins, ad, habitaciones);

                Reservaciones reser = new Reservaciones(u.getNombreAsignado());
                Reservacion reserPOO = new Reservacion();
                Reservacion_Controlador reserCtrl = new Reservacion_Controlador(reserPOO, reser, ctrl);
                
                Gestion_Productos productos = new Gestion_Productos();
                
               int nivelAcceso = u.getNivelAcceso();

                
                BotonesReportes btnRep= new BotonesReportes(reser, true);
                
                AcercaDeNosotros conocenos = new AcercaDeNosotros();
                
                Principal_Controlador principalCtrl = new Principal_Controlador(principal, clientes, gestionHab, habitaciones, admins, ad, reser, nivelAcceso, btnRep, conocenos, productos);

                principalCtrl.iniciar();
                gestionHab.setVisible(false);
                reser.setVisible(false);
                principal.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(vista, "Usuario o contraseña incorrectos");
                vista.jtfUsuario.setText("");
                vista.jpContrasena.setText("");
            }
        }
    }
}
