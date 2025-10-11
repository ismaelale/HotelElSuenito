package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ConsultasDB;
import modelo.Usuario;
import vista.Administracion_de_usuarios;
import java.util.ArrayList;

public class UsuarioRegistroControlador implements ActionListener {

    private Administracion_de_usuarios vista;
    private ConsultasDB ctrl;
    private Usuario usuario;
    private Object[] datos = new Object[6];
    private DefaultTableModel modelo;

    public UsuarioRegistroControlador(Administracion_de_usuarios vista) {
        this.vista = vista;
        this.ctrl = new ConsultasDB();
        this.usuario = new Usuario();

        this.vista.btnCrear.addActionListener(this);
        this.vista.btnLeer.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnBuscarID.addActionListener(this);
        this.vista.btnSalir.addActionListener(this);
    }

    public void llenarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) vista.tblUsuarios.getModel();
        modelo.setRowCount(0);

        ArrayList<Usuario> lista = ctrl.leerTodosUsuarios();

        for (Usuario u : lista) {
            Object[] fila = new Object[6];
            fila[0] = u.getIdUsuario();
            fila[1] = u.getUsername();
            fila[2] = u.getNombreAsignado();
            fila[3] = u.getPassword();
            fila[4] = u.getNivelAcceso();
            fila[5] = u.getRol();
            modelo.addRow(fila);
        }

        vista.tblUsuarios.setModel(modelo);
    }

    public void limpiarCampos() {
        vista.jtfID.setText("0");
        vista.jtfUsername.setText("");
        vista.jtfNombre.setText("");
        vista.jtfPassword.setText("");
        vista.cboRol.setSelectedItem(null);
        vista.cboPermisos.setSelectedItem(null);
        vista.jtfUsername.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnCrear) {
            String username = vista.jtfUsername.getText().trim();
            String nombre = vista.jtfNombre.getText().trim();
            String password = vista.jtfPassword.getText().trim();
            String rol = (String) vista.cboRol.getSelectedItem();
            String nivelAccesoStr = (String) vista.cboPermisos.getSelectedItem();

            if (username.isEmpty() || nombre.isEmpty() || password.isEmpty() || rol == null || nivelAccesoStr == null) {
                JOptionPane.showMessageDialog(null, "LLENE TODOS LOS CAMPOS");
                return;
            }

            int nivelAcceso = Integer.parseInt(nivelAccesoStr);

            Usuario u = new Usuario();
            u.setUsername(username);
            u.setNombreAsignado(nombre);
            u.setPassword(password);
            u.setRol(rol);
            u.setNivelAcceso(nivelAcceso);

            if (ctrl.insertarUsuario(u)) {
                JOptionPane.showMessageDialog(null, "USUARIO REGISTRADO CORRECTAMENTE");
                limpiarCampos();
                llenarTabla();
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL REGISTRAR USUARIO");
            }
        }

        if (e.getSource() == vista.btnLeer) {
            limpiarCampos();
            llenarTabla();
        }

        if (e.getSource() == vista.btnActualizar) {
            try {
                int id = Integer.parseInt(vista.jtfID.getText().trim());
                String username = vista.jtfUsername.getText().trim();
                String nombre = vista.jtfNombre.getText().trim();
                String password = vista.jtfPassword.getText().trim();
                String rol = (String) vista.cboRol.getSelectedItem();
                String nivelAccesoStr = (String) vista.cboPermisos.getSelectedItem();

                if (id <= 0 || username.isEmpty() || nombre.isEmpty() || password.isEmpty() || rol == null || nivelAccesoStr == null) {
                    JOptionPane.showMessageDialog(null, "TODOS LOS CAMPOS SON OBLIGATORIOS PARA ACTUALIZAR");
                    return;
                }

                int nivelAcceso = Integer.parseInt(nivelAccesoStr);

                Usuario u = new Usuario();
                u.setIdUsuario(id);
                u.setUsername(username);
                u.setNombreAsignado(nombre);
                u.setPassword(password);
                u.setRol(rol);
                u.setNivelAcceso(nivelAcceso);

                if (ctrl.actualizarUsuario(u)) {
                    JOptionPane.showMessageDialog(null, "USUARIO ACTUALIZADO CORRECTAMENTE");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR USUARIO");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "ID NO VALIDO");
            }
        }

        if (e.getSource() == vista.btnEliminar) {
            try {
                int id = Integer.parseInt(vista.jtfID.getText().trim());
                if (id <= 0) {
                    JOptionPane.showMessageDialog(null, "SELECCIONE UN USUARIO VALIDO PARA ELIMINAR");
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(null, "DESEA ELIMINAR ESTE USUARIO?", "CONFIRMAR", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    usuario.setIdUsuario(id);
                    if (ctrl.eliminarUsuario(usuario)) {
                        JOptionPane.showMessageDialog(null, "USUARIO ELIMINADO");
                        limpiarCampos();
                        llenarTabla();
                    } else {
                        JOptionPane.showMessageDialog(null, "NO SE PUDO ELIMINAR EL USUARIO");
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "ID NO VALIDO");
            }
        }

        if (e.getSource() == vista.btnLimpiar) {
            limpiarCampos();
        }

        if (e.getSource() == vista.btnSalir) {
            limpiarCampos();
            vista.dispose();
        }

        if (e.getSource() == vista.btnBuscarID) {
            String idTexto = vista.jtfID.getText().trim();

            if (idTexto.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese un ID de usuario");
                return;
            }

            int id;
            try {
                id = Integer.parseInt(idTexto);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "ID invalido");
                return;
            }

            Usuario usuario = ctrl.buscarUsuarioPorID(id);

            if (usuario != null) {
                vista.jtfNombre.setText(usuario.getNombreAsignado());
                vista.jtfUsername.setText(usuario.getUsername());
                vista.jtfPassword.setText(usuario.getPassword());

                vista.cboRol.setSelectedItem(usuario.getRol());
                vista.cboPermisos.setSelectedItem(String.valueOf(usuario.getNivelAcceso()));

            } else {
                JOptionPane.showMessageDialog(null, "Usuario no encontrado");
            }
        }
    }
}
