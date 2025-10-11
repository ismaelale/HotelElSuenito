package controlador;

import modelo.CompraHab_POO;
import modelo.ConsultasDB;
import vista.Gestion_De_Habitaciones;

import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Reservacion;

public class Compra_Controlador implements ActionListener {

    private Gestion_De_Habitaciones vista;
    private ConsultasDB consultas;
    private DefaultTableModel modeloTabla;
    private DefaultTableModel modeloVentas;

    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Compra_Controlador(Gestion_De_Habitaciones vista) {
        this.vista = vista;
        this.consultas = new ConsultasDB();
        this.modeloTabla = (DefaultTableModel) vista.jtHabitaciones.getModel();
        this.modeloVentas = (DefaultTableModel) vista.jtVentas.getModel();
        
        this.vista.btnBuscarCliente.addActionListener(this);
        this.vista.btnAnadir.addActionListener(this);
        this.vista.btnRegistrarCompra.addActionListener(this);
        this.vista.btnSalir.addActionListener(this);
        this.vista.btnLeer.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnBuscarVendida.addActionListener(this);
        this.vista.btnRenovarEstadia.addActionListener(this);
        this.vista.btnMarcarSalida.addActionListener(e -> marcarSalida());
        this.vista.jcbMetodoPago.addActionListener(e -> actualizarEstadoPagoCambio());
        this.vista.jtfReservacion.addActionListener(e -> buscarReservacion());

        this.vista.jtfPago.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                calcularCambio();
            }
        });

        this.vista.jcbTipoHab.addActionListener(e -> llenarComboNumerosPorTipo((String) vista.jcbTipoHab.getSelectedItem()));
        this.vista.txtNumHab.addActionListener(e -> seleccionarNumDesdeTexto());
        this.vista.jcbNumHabitacion.addActionListener(e -> {
            Object sel = vista.jcbNumHabitacion.getSelectedItem();
            vista.txtNumHab.setText(sel == null ? "" : sel.toString());
        });

        inicializarVista();
    }

    private void inicializarVista() {
        vista.setVisible(true);
        vista.jtfFecha.setText(fechaActual());
        vista.jtfFecha.setEditable(false);
        vista.jtfNombreCliente.setEditable(false);
        vista.jtfSubtotal.setEditable(false);
        vista.jtfImpuesto.setEditable(false);
        vista.jtfTotal.setEditable(false);
        vista.jtfPago.setEnabled(false);
        vista.jtfCambio.setEnabled(false);
        vista.jtfCambio.setEditable(false);

        vista.jcbTipoHab.removeAllItems();
        vista.jcbNumHabitacion.removeAllItems();
        vista.jcbTipoHab.addItem("Sencilla");
        vista.jcbTipoHab.addItem("Doble");
        vista.jcbTipoHab.addItem("Triple");

        actualizarEstadoPagoCambio();
    }

    private String fechaActual() {
        return LocalDate.now().format(FORMATO_FECHA);
    }

    private void llenarComboNumerosPorTipo(String tipo) {
        vista.jcbNumHabitacion.removeAllItems();
        ArrayList<String> numeros = consultas.obtenerHabitacionesPorTipo(tipo);
        for (String num : numeros) {
            vista.jcbNumHabitacion.addItem(num);
        }
        String t = vista.txtNumHab.getText().trim();
        if (!t.isEmpty()) {
            DefaultComboBoxModel<?> m = (DefaultComboBoxModel<?>) vista.jcbNumHabitacion.getModel();
            boolean existe = false;
            for (int i = 0; i < m.getSize(); i++) {
                if (t.equals(String.valueOf(m.getElementAt(i)))) { existe = true; break; }
            }
            if (!existe) vista.jcbNumHabitacion.addItem(t);
            vista.jcbNumHabitacion.setSelectedItem(t);
        }
    }

    private void seleccionarNumDesdeTexto() {
        String t = vista.txtNumHab.getText().trim();
        if (t.isEmpty()) return;
        DefaultComboBoxModel<?> m = (DefaultComboBoxModel<?>) vista.jcbNumHabitacion.getModel();
        boolean existe = false;
        for (int i = 0; i < m.getSize(); i++) {
            if (t.equals(String.valueOf(m.getElementAt(i)))) { existe = true; break; }
        }
        if (!existe) vista.jcbNumHabitacion.addItem(t);
        vista.jcbNumHabitacion.setSelectedItem(t);
    }

    private void calcularTotales() {
        double subtotal = 0;
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            subtotal += Double.parseDouble(modeloTabla.getValueAt(i, 4).toString());
        }
        double impuesto = subtotal * 0.15;
        double total = subtotal + impuesto;
        vista.jtfSubtotal.setText(String.format("%.2f", subtotal));
        vista.jtfImpuesto.setText(String.format("%.2f", impuesto));
        vista.jtfTotal.setText(String.format("%.2f", total));
    }

    private void limpiar() {
        vista.jtfIdCliente.setText("");
        vista.txtNumHab.setText(null);
        vista.jtfNombreCliente.setText("");
        vista.jcbTipoHab.setSelectedIndex(0);
        vista.jcbNumHabitacion.removeAllItems();
        vista.jtfSubtotal.setText("");
        vista.jtfImpuesto.setText("");
        vista.jtfTotal.setText("");
        vista.jtfPago.setText("");
        vista.jtfCambio.setText("");
        vista.jtfPago.setEnabled(false);
        vista.jtfCambio.setEnabled(false);
        modeloTabla.setRowCount(0);
        modeloVentas.setRowCount(0);
    }

    private void actualizarEstadoPagoCambio() {
        String metodo = (String) vista.jcbMetodoPago.getSelectedItem();
        boolean efectivo = "Efectivo".equals(metodo);
        vista.jtfPago.setEnabled(efectivo);
        vista.jtfCambio.setEnabled(efectivo);
        if (!efectivo) {
            vista.jtfPago.setText("");
            vista.jtfCambio.setText("");
        }
    }

    private void calcularCambio() {
        try {
            double pago = Double.parseDouble(vista.jtfPago.getText().replace(",", "."));
            double total = Double.parseDouble(vista.jtfTotal.getText().replace(",", "."));
            if (pago < total) {
                vista.jtfCambio.setText("");
                return;
            }
            double cambio = pago - total;
            vista.jtfCambio.setText(String.format("%.2f", cambio));
        } catch (NumberFormatException ex) {
            vista.jtfCambio.setText("");
        }
    }

    private void buscarCliente() {
        try {
            int idCliente = Integer.parseInt(vista.jtfIdCliente.getText());
            String nombre = consultas.obtenerNombreCliente(idCliente);
            if (nombre != null) {
                vista.jtfNombreCliente.setText(nombre);
            } else {
                JOptionPane.showMessageDialog(vista, "Cliente no encontrado");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "ID de cliente inválido");
        }
    }

    private int calcularNoches(String fechaEntrada, String fechaSalida) {
        try {
            LocalDate entrada = LocalDate.parse(fechaEntrada, FORMATO_FECHA);
            LocalDate salida = LocalDate.parse(fechaSalida, FORMATO_FECHA);
            if (salida.isBefore(entrada)) {
                return -1;
            }
            long noches = ChronoUnit.DAYS.between(entrada, salida);
            return (int) (noches == 0 ? 1 : noches);
        } catch (Exception e) {
            return -1;
        }
    }

    private double obtenerPrecioPorTipo(String tipo) {
        switch (tipo) {
            case "Sencilla":
                return 535;
            case "Doble":
                return 655;
            case "Triple":
                return 900;
            default:
                return 0;
        }
    }

    private void agregarHabitacionATabla() {
        String tipo = (String) vista.jcbTipoHab.getSelectedItem();
        String numeroTxt = vista.txtNumHab.getText().trim();
        String numero = numeroTxt.isEmpty() ? (String) vista.jcbNumHabitacion.getSelectedItem() : numeroTxt;
        String fechaEntrada = vista.jtfFecha.getText().trim();
        String fechaSalida = vista.jftFechaSalida.getText().trim();

        if (tipo == null || numero == null || numero.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar tipo y número de habitación");
            return;
        }

        if (fechaSalida.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Por favor ingrese la fecha de salida.");
            return;
        }

        int noches = calcularNoches(fechaEntrada, fechaSalida);
        if (noches == -1) {
            JOptionPane.showMessageDialog(vista, "La fecha de salida no puede ser anterior a la fecha de entrada.");
            return;
        }

        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            if (modeloTabla.getValueAt(i, 1).equals(numero)) {
                JOptionPane.showMessageDialog(vista, "Esta habitación ya fue añadida");
                return;
            }
        }

        DefaultComboBoxModel<?> m = (DefaultComboBoxModel<?>) vista.jcbNumHabitacion.getModel();
        boolean existe = false;
        for (int i = 0; i < m.getSize(); i++) {
            if (numero.equals(String.valueOf(m.getElementAt(i)))) { existe = true; break; }
        }
        if (!existe) vista.jcbNumHabitacion.addItem(numero);
        vista.jcbNumHabitacion.setSelectedItem(numero);
        vista.txtNumHab.setText(numero);

        double precioUnitario = obtenerPrecioPorTipo(tipo);
        double precioTotal = precioUnitario * noches;

        modeloTabla.addRow(new Object[]{tipo, numero, precioUnitario, noches, precioTotal});
        calcularTotales();
    }

    private void guardarCompra() {
        if (modeloTabla.getRowCount() == 0) {
            JOptionPane.showMessageDialog(vista, "Debe agregar al menos una habitación");
            return;
        }
        try {
            if (vista.jtfIdCliente.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debe ingresar el ID del cliente.");
                return;
            }
            int idCliente = Integer.parseInt(vista.jtfIdCliente.getText().trim());
            String nombreCliente = vista.jtfNombreCliente.getText().trim();
            String metodoPago = (String) vista.jcbMetodoPago.getSelectedItem();
            String fechaEntrada = vista.jtfFecha.getText();
            String fechaSalida = vista.jftFechaSalida.getText().trim();

            if (fechaSalida.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debe ingresar la fecha de salida.");
                return;
            }

            int noches = calcularNoches(fechaEntrada, fechaSalida);
            if (noches == -1) {
                JOptionPane.showMessageDialog(vista, "La fecha de salida no puede ser anterior a la fecha de entrada.");
                return;
            }

            double subtotalGlobal = 0;
            double impuestoGlobal = 0;
            double totalGlobal = 0;

            double pagoCliente = 0;
            double cambioCliente = 0;
            if ("Efectivo".equals(metodoPago)) {
                try {
                    pagoCliente = Double.parseDouble(vista.jtfPago.getText().replace(",", "."));
                    cambioCliente = Double.parseDouble(vista.jtfCambio.getText().replace(",", "."));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(vista, "Ingrese un valor válido para pago y cambio.");
                    return;
                }
            }

            boolean todasRegistradas = true;

            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                String tipoHab = modeloTabla.getValueAt(i, 0).toString();
                String numHab = modeloTabla.getValueAt(i, 1).toString();
                double precioFinal = Double.parseDouble(modeloTabla.getValueAt(i, 4).toString());
                double impuesto = precioFinal * 0.15;
                double total = precioFinal + impuesto;

                subtotalGlobal += precioFinal;
                impuestoGlobal += impuesto;
                totalGlobal += total;

                double pago = 0.0;
                double cambio = 0.0;
                if ("Efectivo".equals(metodoPago) && i == modeloTabla.getRowCount() - 1) {
                    pago = pagoCliente;
                    cambio = cambioCliente;
                    if (pago < totalGlobal) {
                        JOptionPane.showMessageDialog(vista, "El pago es menor al total de la compra.");
                        return;
                    }
                }

                boolean registrado = consultas.registrarCompraHabitacion(
                        idCliente, nombreCliente, tipoHab, numHab, precioFinal,
                        metodoPago, subtotalGlobal, impuestoGlobal, totalGlobal, pago, cambio,
                        fechaEntrada, fechaSalida);

                if (!registrado) {
                    todasRegistradas = false;
                } else {
                    consultas.actualizarEstadoHabitacion(numHab, "Ocupado");
                }
            }

            String idReservaStr = vista.jtfReservacion.getText().trim();
            if (!idReservaStr.isEmpty()) {
                try {
                    int idReserva = Integer.parseInt(idReservaStr);
                    boolean reservaActualizada = consultas.actualizarEstadoReservacion(idReserva, "Finalizada");
                    if (!reservaActualizada) {
                        JOptionPane.showMessageDialog(vista, "Error al actualizar el estado de la reservación.");
                    }
                } catch (NumberFormatException e) {
                }
            }

            if (todasRegistradas) {
                JOptionPane.showMessageDialog(vista, "Compra registrada exitosamente.");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al registrar alguna habitación.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Campos numéricos inválidos.");
        }
    }

    private void marcarSalida() {
        int fila = vista.jtVentas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione una compra desde la tabla de ventas.");
            return;
        }
        int idCompra = Integer.parseInt(vista.jtVentas.getValueAt(fila, 0).toString());
        int confirm = JOptionPane.showConfirmDialog(vista, "¿Está seguro que desea marcar la salida para esta compra?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean exito = consultas.marcarSalidaHabitaciones(idCompra);
            if (exito) {
                JOptionPane.showMessageDialog(vista, "Habitaciones liberadas correctamente.");
                cargarComprasActivas();
            } else {
                JOptionPane.showMessageDialog(vista, "No se pudo liberar las habitaciones.");
            }
        }
    }

    private void cargarComprasActivas() {
        modeloVentas.setRowCount(0);
        ArrayList<CompraHab_POO> compras = consultas.obtenerComprasActivas();

        Map<Integer, CompraHab_POO> comprasMap = new HashMap<>();

        for (CompraHab_POO compra : compras) {
            if (comprasMap.containsKey(compra.getIdCompra())) {
                CompraHab_POO existente = comprasMap.get(compra.getIdCompra());
                existente.getHabitacionesSeleccionadas().addAll(compra.getHabitacionesSeleccionadas());
            } else {
                comprasMap.put(compra.getIdCompra(), compra);
            }
        }

        for (CompraHab_POO compra : comprasMap.values()) {
            StringBuilder habitacionesStr = new StringBuilder();
            for (CompraHab_POO.HabitacionSeleccionada hab : compra.getHabitacionesSeleccionadas()) {
                habitacionesStr.append(hab.getTipo())
                        .append(" #")
                        .append(hab.getNumero())
                        .append(", ");
            }
            if (habitacionesStr.length() > 0) {
                habitacionesStr.setLength(habitacionesStr.length() - 2);
            }

            modeloVentas.addRow(new Object[]{
                compra.getIdCompra(),
                compra.getNombreCliente(),
                habitacionesStr.toString(),
                String.format("%.2f", compra.getSubtotal()),
                String.format("%.2f", compra.getImpuesto()),
                String.format("%.2f", compra.getTotal()),
                compra.getFechaIngreso(),
                compra.getFechaSalida()
            });
        }
    }

    private void limpiarTablaVentas() {
        modeloVentas.setRowCount(0);
    }

    private void buscarCompraPorId() {
        String idStr = vista.jtfBuscarIdCompra.getText().trim();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Ingrese un ID de compra para buscar.");
            return;
        }
        try {
            int idCompra = Integer.parseInt(idStr);
            CompraHab_POO compra = consultas.obtenerCompraPorId(idCompra);
            modeloVentas.setRowCount(0);
            if (compra != null) {
                StringBuilder habitacionesStr = new StringBuilder();
                for (CompraHab_POO.HabitacionSeleccionada hab : compra.getHabitacionesSeleccionadas()) {
                    habitacionesStr.append(hab.getTipo())
                            .append(" #")
                            .append(hab.getNumero())
                            .append(", ");
                }
                if (habitacionesStr.length() > 0) {
                    habitacionesStr.setLength(habitacionesStr.length() - 2);
                }

                modeloVentas.addRow(new Object[]{
                    compra.getIdCompra(),
                    compra.getNombreCliente(),
                    habitacionesStr.toString(),
                    compra.getSubtotal(),
                    compra.getImpuesto(),
                    compra.getTotal(),
                    compra.getFechaIngreso(),
                    compra.getFechaSalida()
                });
            } else {
                JOptionPane.showMessageDialog(vista, "No se encontró compra con ese ID.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "ID inválido.");
        }
    }

    private void renovarEstadia() {
        int fila = vista.jtVentas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione una compra para renovar.");
            return;
        }
        String nuevaFechaSalida = vista.jftFechaSalida.getText().trim();
        if (nuevaFechaSalida.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Ingrese la nueva fecha de salida.");
            return;
        }

        try {
            LocalDate nuevaSalida = LocalDate.parse(nuevaFechaSalida, FORMATO_FECHA);
            int idCompra = (int) modeloVentas.getValueAt(fila, 0);
            String fechaEntrada = modeloVentas.getValueAt(fila, 6).toString();

            LocalDate fechaEntradaDate = LocalDate.parse(fechaEntrada, FORMATO_FECHA);

            if (nuevaSalida.isBefore(fechaEntradaDate)) {
                JOptionPane.showMessageDialog(vista, "La fecha de salida no puede ser anterior a la fecha de entrada.");
                return;
            }

            int noches = (int) ChronoUnit.DAYS.between(fechaEntradaDate, nuevaSalida);
            noches = noches == 0 ? 1 : noches;
               
            ArrayList<CompraHab_POO> habitaciones = consultas.obtenerHabitacionesPorCompra(idCompra);

            double nuevoSubtotal = 0;
            for (CompraHab_POO hab : habitaciones) {
                double precioUnitario = obtenerPrecioPorTipo(hab.getTipoHabitacion());
                nuevoSubtotal += precioUnitario * noches;
            }

            double nuevoImpuesto = nuevoSubtotal * 0.15;
            double nuevoTotal = nuevoSubtotal + nuevoImpuesto;

            boolean actualizado = consultas.actualizarTotalesYFechaSalida(idCompra, nuevaFechaSalida, nuevoSubtotal, nuevoImpuesto, nuevoTotal);

            if (actualizado) {
                modeloVentas.setValueAt(nuevaFechaSalida, fila, 7);
                modeloVentas.setValueAt(String.format("%.2f", nuevoSubtotal), fila, 3);
                modeloVentas.setValueAt(String.format("%.2f", nuevoImpuesto), fila, 4);
                modeloVentas.setValueAt(String.format("%.2f", nuevoTotal), fila, 5);

                JOptionPane.showMessageDialog(vista, "Fecha de salida y totales actualizados correctamente.");
            } else {
                JOptionPane.showMessageDialog(vista, "Error al actualizar la fecha de salida y totales.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "La fecha debe tener formato yyyy-MM-dd.");
        }
    }

    private void buscarReservacion() {
        String idReservacionStr = vista.jtfReservacion.getText().trim();
        if (idReservacionStr.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Ingrese el ID de la reservación para buscar.");
            return;
        }

        try {
            int idReservacion = Integer.parseInt(idReservacionStr);
            Reservacion res = consultas.buscarReservacionPorId(idReservacion);

            if (res == null) {
                JOptionPane.showMessageDialog(vista, "No se encontró reservación con ese ID.");
                return;
            }

            vista.jtfIdCliente.setText(String.valueOf(res.getIdCliente()));
            vista.jtfNombreCliente.setText(res.getNombreCliente());
            vista.jtfFecha.setText(res.getFechaEntrada());
            vista.jftFechaSalida.setText(res.getFechaSalida());

            llenarComboNumerosPorTipo(res.getTipoHabitacion());
            vista.jcbTipoHab.setSelectedItem(res.getTipoHabitacion());

           String num = String.valueOf(res.getNumHabitacion());

            DefaultComboBoxModel<?> m = (DefaultComboBoxModel<?>) vista.jcbNumHabitacion.getModel();
            boolean existe = false;
            for (int i = 0; i < m.getSize(); i++) {
                if (num.equals(String.valueOf(m.getElementAt(i)))) { existe = true; break; }
            }
            if (!existe) vista.jcbNumHabitacion.addItem(num);
            vista.jcbNumHabitacion.setSelectedItem(num);
            vista.txtNumHab.setText(num);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "El ID de reservación debe ser un número válido.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnBuscarCliente) {
            buscarCliente();
        } else if (e.getSource() == vista.btnAnadir) {
            agregarHabitacionATabla();
        } else if (e.getSource() == vista.btnRegistrarCompra) {
            guardarCompra();
        } else if (e.getSource() == vista.btnSalir) {
            vista.dispose();
        } else if (e.getSource() == vista.btnLeer) {
            cargarComprasActivas();
        } else if (e.getSource() == vista.btnLimpiar) {
            limpiar();
        } else if (e.getSource() == vista.btnBuscarVendida) {
            buscarCompraPorId();
        } else if (e.getSource() == vista.btnRenovarEstadia) {
            renovarEstadia();
        }
    }
}
