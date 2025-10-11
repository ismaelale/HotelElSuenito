package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Clientes_POO;
import modelo.ConsultasDB;
import vista.Gestion_Clientes;

public class Cliente_Controlador implements ActionListener {

    private Clientes_POO cli;
    private Gestion_Clientes form;
    private ConsultasDB conBD;
    private Object[] datos = new Object[4];
    private DefaultTableModel modelo;

    public Cliente_Controlador(Clientes_POO cli, Gestion_Clientes form, ConsultasDB conBD) {
        this.cli = cli;
        this.form = form;
        this.conBD = conBD;

        this.form.btnCrear.addActionListener(this);
        this.form.btnLeer.addActionListener(this);
        this.form.btnActualizar.addActionListener(this);
        this.form.btnEliminar.addActionListener(this);
        this.form.btnLimpiar.addActionListener(this);
        this.form.btnSalir.addActionListener(this);
        this.form.btnBuscarID.addActionListener(this);

    }

    public void llenarTabla() {
        modelo = (DefaultTableModel) form.tblDatos.getModel();
        modelo.setRowCount(0); 
        for (Clientes_POO cliente : conBD.leerTodosClientes()) {
            datos[0] = cliente.getIdCliente();
            datos[1] = cliente.getNombreCompleto();
            datos[2] = cliente.getDni();
            datos[3] = cliente.getTelefono();
            modelo.addRow(datos);
        }
        form.tblDatos.setModel(modelo);
    }

    public void limpiar() {
        form.jftIdCliente.setText("0");
        form.jtfNombre.setText("");
        form.jtfDni.setText("");
        form.jftTelefono.setText("");
        form.jtfNombre.requestFocus();
        
 
    }
   

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == form.btnCrear) {
            if (form.jtfNombre.getText().isEmpty() || form.jtfDni.getText().isEmpty() || form.jftTelefono.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "DEBE LLENAR TODOS LOS CAMPOS PARA REGISTRAR UN CLIENTE", "ERROR", 0);
            } else {
                cli.setIdCliente(Integer.parseInt(form.jftIdCliente.getText()));
                cli.setNombreCompleto(form.jtfNombre.getText());
                cli.setDni(form.jtfDni.getText());
                cli.setTelefono(form.jftTelefono.getText());

                if (conBD.crear(cli)) {
                    JOptionPane.showMessageDialog(null, "CLIENTE REGISTRADO CON ÉXITO", "CREACIÓN EXITOSA", 1);
                    limpiar();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR AL CREAR EL CLIENTE", "ERROR", 0);
                }
            }
        }

        if (e.getSource() == form.btnLeer) {
            limpiar();
            llenarTabla();
        }

        if (e.getSource() == form.btnActualizar) {
            if (form.jtfNombre.getText().isEmpty() || form.jtfDni.getText().isEmpty() || form.jftTelefono.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "DEBE COMPLETAR TODOS LOS CAMPOS PARA ACTUALIZAR", "ERROR", 0);
            } else {
                cli.setIdCliente(Integer.parseInt(form.jftIdCliente.getText()));
                cli.setNombreCompleto(form.jtfNombre.getText());
                cli.setDni(form.jtfDni.getText());
                cli.setTelefono(form.jftTelefono.getText());

                if (conBD.actualizarCliente(cli)) {
                    JOptionPane.showMessageDialog(null, "CLIENTE ACTUALIZADO CORRECTAMENTE", "ACTUALIZACIÓN EXITOSA", 1);
                    limpiar();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "NO SE PUDO ACTUALIZAR EL CLIENTE", "ERROR", 0);
                }
            }
        }

        if (e.getSource() == form.btnEliminar) {
            int id = Integer.parseInt(form.jftIdCliente.getText());
            if (id <= 0) {
                JOptionPane.showMessageDialog(null, "SELECCIONE UN CLIENTE VÁLIDO PARA ELIMINAR", "ERROR", 0);
            } else {
                int confirm = JOptionPane.showConfirmDialog(null, "¿DESEA ELIMINAR ESTE CLIENTE?", "CONFIRMAR", JOptionPane.YES_NO_OPTION);
                if (confirm == 0) {
                    cli.setIdCliente(id);
                    if (conBD.eliminarCliente(cli)) {
                        JOptionPane.showMessageDialog(null, "CLIENTE ELIMINADO", "ÉXITO", 1);
                        limpiar();
                        llenarTabla();
                    } else {
                        JOptionPane.showMessageDialog(null, "NO SE PUDO ELIMINAR EL CLIENTE", "ERROR", 0);
                    }
                }
            }
        }

        if (e.getSource() == form.btnLimpiar) {
            limpiar();
        }

        if (e.getSource() == form.btnSalir) {
            limpiar();
            form.dispose();
        }
        if (e.getSource() == form.btnBuscarID) {
    String idTexto = form.jftIdCliente.getText().trim();

    if (idTexto.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Ingrese un ID de cliente para buscar", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }

    int idCliente;
    try {
        idCliente = Integer.parseInt(idTexto);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "ID inválido. Debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    Clientes_POO clienteBuscado = conBD.buscarClientePorId(idCliente);

    if (clienteBuscado != null) {
        form.jtfNombre.setText(clienteBuscado.getNombreCompleto());
        form.jtfDni.setText(clienteBuscado.getDni());
        form.jftTelefono.setText(clienteBuscado.getTelefono());
    } else {
        JOptionPane.showMessageDialog(null, "Cliente no encontrado", "Información", JOptionPane.INFORMATION_MESSAGE);
        limpiar();
    }
}
        }
}
        
