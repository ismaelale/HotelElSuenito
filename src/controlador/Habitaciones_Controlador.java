package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.HabitacionesModel;
import modelo.ConsultasDB;
import vista.Habitaciones;

public class Habitaciones_Controlador implements ActionListener {

    private HabitacionesModel hab;
    private Habitaciones form;
    private ConsultasDB conBD;
    private Object datos[] = new Object[5];
    DefaultTableModel modelo;
    private String dirImagen;
    private String cambio = "No";

    public Habitaciones_Controlador(HabitacionesModel hab, Habitaciones form, ConsultasDB conBD) {
        this.hab = hab;
        this.form = form;
        this.conBD = conBD;

        this.form.btnCrear.addActionListener(this);
        this.form.btnLeer.addActionListener(this);
        this.form.btnLimpiar.addActionListener(this);
        this.form.btnActualizar.addActionListener(this);
        this.form.btnBuscarID.addActionListener(this);
        this.form.btnEliminar.addActionListener(this);
        this.form.btnSalir.addActionListener(this);
        this.form.btnFoto.addActionListener(this);
        form.lblFoto.setIcon(new ImageIcon("src/imgs/sinPerfil.jpg"));
    }

    public void llenarTabla() {
        modelo = (DefaultTableModel) form.tblDatos.getModel();
        var listaHabitaciones = conBD.leerTodos();
        for (var h : listaHabitaciones) {
            datos[0] = h.getId_hab();
            datos[1] = h.getTipo();
            datos[2] = h.getNum_hab();
            datos[3] = h.getPrecio();
            datos[4] = h.getEstado();
            modelo.addRow(datos);
        }
        form.tblDatos.setModel(modelo);
    }

    public void limpiar() {
        form.jtfIdCliente.setText("0");
        form.jCboTipo.setSelectedItem("Seleccione");
        form.jtfNumHab.setText("");
        form.jtfPrecio.setText("");
        form.lblFoto.setIcon(new ImageIcon("src/imgs/sinPerfil.jpg"));
    }

    public void actualizarTabla() {
        int fila = form.tblDatos.getRowCount();
        for (int i = fila - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }
        llenarTabla();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == form.btnCrear) {
            if (form.jCboTipo.getSelectedItem() == null || form.jtfNumHab.getText().isEmpty() || form.jtfPrecio.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos para registrar una habitación", "ERROR", 0);
            } else {
                hab.setId_hab(Integer.parseInt(form.jtfIdCliente.getText().trim()));
                hab.setTipo(form.jCboTipo.getSelectedItem().toString());
                hab.setNum_hab(Integer.parseInt(form.jtfNumHab.getText().trim()));
                hab.setPrecio(Integer.parseInt(form.jtfPrecio.getText().trim()));
                hab.setEstado("DISPONIBLE");

                if (conBD.crearHabitaciones(hab)) {
                    JOptionPane.showMessageDialog(null, "Habitación creada correctamente", "ÉXITO", 1);
                    limpiar();
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo crear la habitación", "ERROR", 0);
                }
            }
        }

        if (e.getSource() == form.btnLeer) {
            llenarTabla();
        }

        if (e.getSource() == form.btnLimpiar) {
            limpiar();
        }

        if (e.getSource() == form.btnBuscarID) {
            if (form.jtfIdCliente.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese el código de la habitación", "ERROR", 0);
            } else {
                hab.setId_hab(Integer.parseInt(form.jtfIdCliente.getText().trim()));
                if (conBD.buscarHabitaciones(hab)) {
                    JOptionPane.showMessageDialog(null, "Habitación encontrada", "ÉXITO", 1);
                    form.jCboTipo.setSelectedItem(hab.getTipo());
                    form.jtfNumHab.setText(String.valueOf(hab.getNum_hab()));
                    form.jtfPrecio.setText(String.valueOf(hab.getPrecio()));
                    form.lblFoto.setIcon(form.tamanioImagen(hab.getFoto()));
                    dirImagen = hab.getFoto();
                } else {
                    JOptionPane.showMessageDialog(null, "Habitación no encontrada", "ERROR", 0);
                }
            }
        }

        if (e.getSource() == form.btnActualizar) {
            if (form.jCboTipo.getSelectedItem() == null || form.jtfNumHab.getText().isEmpty() || form.jtfPrecio.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos para actualizar una habitación", "ERROR", 0);
            } else {
                hab.setId_hab(Integer.parseInt(form.jtfIdCliente.getText().trim()));
                hab.setTipo(form.jCboTipo.getSelectedItem().toString());
                hab.setNum_hab(Integer.parseInt(form.jtfNumHab.getText().trim()));
                hab.setPrecio(Double.parseDouble(form.jtfPrecio.getText().trim()));

                if (cambio.equals("No")) {
                    hab.setFoto(dirImagen);
                }
                
                if (conBD.actualizarHabitacion(hab)) {
                    JOptionPane.showMessageDialog(null, "HABITACION ACTUALIZADO CORRECTAMENTE", "ACTUALIZACION EXITOSA", 1);
                    limpiar();
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "NO SE PUDIERON ACTUALIZAR LOS DATOS DEL CLIENTE", "ERROR", 0);
                }
            }
        }

        if (e.getSource() == form.btnEliminar) {
            if (form.jtfIdCliente.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese el código de la habitación que desea eliminar", "ERROR", 0);
            } else {
                hab.setId_hab(Integer.parseInt(form.jtfIdCliente.getText()));
                int confirm = JOptionPane.showConfirmDialog(null, "¿Desea eliminar esta habitación?", "CONFIRMACIÓN", JOptionPane.YES_NO_OPTION, 2);
                if (confirm == 0) {
                    if (conBD.eliminarHabitacion(hab)) {
                        JOptionPane.showMessageDialog(null, "Habitación eliminada correctamente", "ELIMINADO", 1);
                        limpiar();
                        actualizarTabla();
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo eliminar la habitación", "ERROR", 0);
                    }
                }
            }
        }

        if (e.getSource() == form.btnSalir) {
            form.dispose();
        }

        if (e.getSource() == form.btnFoto) {
            form.buscarImagen();
            hab.setFoto(form.direccionImagen);
            cambio = "Si";
        }
    }
}