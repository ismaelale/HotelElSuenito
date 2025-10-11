package Controlador;

import modelo.ConsultasDB;
import modelo.Reservacion;
import vista.Reservaciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Reservacion_Controlador implements ActionListener {

    private ConsultasDB conBD;
    private Reservaciones form;
    private Reservacion reservacion;
    private DefaultTableModel modelo = new DefaultTableModel();
    private Object[] datos = new Object[8];

    public Reservacion_Controlador(Reservacion reservacion, Reservaciones form, ConsultasDB conBD) {
        this.reservacion = reservacion;
        this.form = form;
        this.conBD = conBD;

        this.form.btnCrear.addActionListener(this);
        this.form.btnLeer.addActionListener(this);
        this.form.btnEliminar.addActionListener(this);
        this.form.btnLimpiar.addActionListener(this);
        this.form.btnBuscarID.addActionListener(this);
        this.form.btnBuscarIdC.addActionListener(this);
        this.form.btnRegresar.addActionListener(this);

        cargarTiposHabitacion();

        this.form.cboTipoHab.addActionListener(e -> {
            String tipo = (String) form.cboTipoHab.getSelectedItem();
            if (tipo != null) {
                List<Integer> numeros = conBD.obtenerNumerosHabitacionesPorTipo(tipo);
                DefaultComboBoxModel<Integer> modeloNums = new DefaultComboBoxModel<>();
                for (Integer n : numeros) {
                    modeloNums.addElement(n);
                }
                form.cboNumHab.setModel(modeloNums);
                form.cboNumHab.setSelectedIndex(-1);
            } else {
                form.cboNumHab.setModel(new DefaultComboBoxModel<>());
            }
        });
    }

    private void cargarTiposHabitacion() {
        DefaultComboBoxModel<String> modeloTipos = new DefaultComboBoxModel<>();
        List<String> tipos = conBD.obtenerTiposHabitacionDisponibles();
        for (String tipo : tipos) {
            modeloTipos.addElement(tipo);
        }
        form.cboTipoHab.setModel(modeloTipos);
        form.cboTipoHab.setSelectedIndex(-1);
    }

    public void llenarTabla() {
        modelo = (DefaultTableModel) form.tblReservas.getModel();
        modelo.setRowCount(0);
        ArrayList<Reservacion> lista = conBD.obtenerTodasReservas();
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay reservaciones para mostrar", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        for (Reservacion r : lista) {
            datos[0] = r.getIdReservacion();
            datos[1] = r.getIdCliente();
            datos[2] = r.getNombreCliente();
            datos[3] = r.getTipoHabitacion();
            datos[4] = r.getNumHabitacion();
            datos[5] = r.getFechaEntrada();
            datos[6] = r.getFechaSalida();
            datos[7] = r.getEstadoReserva();
            modelo.addRow(datos);
        }
        form.tblReservas.setModel(modelo);
    }

    public void limpiar() {
        form.jtfID.setText("0");
        form.jtfIDC.setText("");
        form.jtfNombre.setText("");
        form.cboTipoHab.setSelectedIndex(-1);
        form.cboNumHab.setModel(new DefaultComboBoxModel<>());
        form.jtfFechaEntrada.setText("");
        form.jtfFechaSalida.setText("");
        form.jtfID.requestFocus();
        modelo.setRowCount(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int idReserva = 0;
        int idCliente = 0;

        try {
            idReserva = Integer.parseInt(form.jtfID.getText());
        } catch (NumberFormatException ex) {
        }

        try {
            idCliente = Integer.parseInt(form.jtfIDC.getText());
        } catch (NumberFormatException ex) {
        }

        if (e.getSource() == form.btnCrear) {
            if (!validarCamposCrear()) return;

            reservacion.setIdCliente(idCliente);
            reservacion.setNombreCliente(form.jtfNombre.getText());
            reservacion.setTipoHabitacion((String) form.cboTipoHab.getSelectedItem());
            reservacion.setNumHabitacion(Integer.parseInt(form.cboNumHab.getSelectedItem().toString()));
            reservacion.setFechaEntrada(form.jtfFechaEntrada.getText());
            reservacion.setFechaSalida(form.jtfFechaSalida.getText());
            reservacion.setEstadoReserva("Activa");

            if (conBD.insertarReservacion(reservacion)) {
                boolean actualizado = conBD.actualizarEstadoHabitacion(
            String.valueOf(reservacion.getNumHabitacion()), "Reservada");
                if (!actualizado) {
                    JOptionPane.showMessageDialog(null, "No se pudo actualizar el estado de la habitacion", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
                JOptionPane.showMessageDialog(null, "Reservacion creada correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
                llenarTabla();
            } else {
                JOptionPane.showMessageDialog(null, "Error al crear reservacion", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == form.btnLeer) {
            llenarTabla();
        }

        if (e.getSource() == form.btnEliminar) {
            if (idReserva <= 0) {
                JOptionPane.showMessageDialog(null, "Ingrese un ID valido para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(null, "Desea eliminar esta reservacion?", "Confirmar eliminacion", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (conBD.eliminarReservacion(idReserva)) {
                    JOptionPane.showMessageDialog(null, "Reservacion eliminada correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    limpiar();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar reservacion", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (e.getSource() == form.btnBuscarID) {
            if (idReserva <= 0) {
                JOptionPane.showMessageDialog(null, "Ingrese un ID valido para buscar", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Reservacion r = conBD.buscarReservacionPorId(idReserva);
            if (r != null) {
                form.jtfID.setText(String.valueOf(r.getIdReservacion()));
                form.jtfIDC.setText(String.valueOf(r.getIdCliente()));
                form.jtfNombre.setText(r.getNombreCliente());
                form.cboTipoHab.setSelectedItem(r.getTipoHabitacion());
                form.cboNumHab.setSelectedItem(r.getNumHabitacion());
                form.jtfFechaEntrada.setText(r.getFechaEntrada());
                form.jtfFechaSalida.setText(r.getFechaSalida());
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro la reservacion", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        if (e.getSource() == form.btnBuscarIdC) {
            if (idCliente <= 0) {
                JOptionPane.showMessageDialog(null, "Ingrese un ID cliente valido para buscar", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String nombreCliente = conBD.obtenerNombreCliente(idCliente);
            if (nombreCliente != null) {
                form.jtfNombre.setText(nombreCliente);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el cliente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        if (e.getSource() == form.btnLimpiar) {
            limpiar();
        }

        if (e.getSource() == form.btnRegresar) {
            form.dispose();
        }
    }

    private boolean validarCamposCrear() {
        if (form.jtfIDC.getText().isEmpty() || form.jtfNombre.getText().isEmpty() || form.cboTipoHab.getSelectedIndex() == -1
                || form.cboNumHab.getSelectedIndex() == -1 || form.jtfFechaEntrada.getText().isEmpty() || form.jtfFechaSalida.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos para crear una reservacion", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            Integer.parseInt(form.jtfIDC.getText());
            Integer.parseInt(form.cboNumHab.getSelectedItem().toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID Cliente y Numero de habitacion deben ser numeros validos", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean validarCamposActualizar() {
        if (form.cboTipoHab.getSelectedIndex() == -1 || form.cboNumHab.getSelectedIndex() == -1
                || form.jtfFechaEntrada.getText().isEmpty() || form.jtfFechaSalida.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos para actualizar una reservacion", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            Integer.parseInt(form.cboNumHab.getSelectedItem().toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Numero de habitacion debe ser un numero valido", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
