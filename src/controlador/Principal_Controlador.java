package Controlador;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

import vista.ADMINS;
import vista.Administracion_de_usuarios;
import vista.Gestion_Clientes;
import vista.Gestion_De_Habitaciones;
import vista.Habitaciones;
import vista.Principal;
import vista.Reservaciones;
import vista.AcercaDeNosotros;
import Reportes.BotonesReportes;
import vista.Gestion_Productos;

public class Principal_Controlador implements ActionListener {

    private Principal pri;
    private Gestion_Clientes clientes;
    private Habitaciones habitaciones;
    private Administracion_de_usuarios adms;
    private ADMINS ad;
    private Gestion_De_Habitaciones gestionHab;
    private Reservaciones reser;
    private BotonesReportes btnRep;
    private AcercaDeNosotros conocenos;
    private Gestion_Productos productos;
    private int nivelAcceso;

    public Principal_Controlador(Principal pri,Gestion_Clientes clientes,Gestion_De_Habitaciones gestionHab,Habitaciones habitaciones,Administracion_de_usuarios adms,ADMINS ad,
                                 Reservaciones reser,int nivelAcceso,BotonesReportes btnRep,
                                 AcercaDeNosotros conocenos, Gestion_Productos productos) {

        this.pri = pri;
        this.clientes = clientes;
        this.habitaciones = habitaciones;
        this.adms = adms;
        this.ad = ad;
        this.gestionHab = gestionHab;
        this.reser = reser;
        this.btnRep = btnRep;
        this.conocenos = conocenos;
        this.nivelAcceso = nivelAcceso;
        this.productos = productos;

        configurarAccesos();

        this.pri.btnClientes.addActionListener(this);
        this.pri.btnHabitaciones.addActionListener(this);
        this.pri.BtnAdmins1.addActionListener(this);
        this.pri.btnSalir.addActionListener(this);
        this.pri.btnFacturacion.addActionListener(this);
        this.pri.btnReportes.addActionListener(this);
        this.pri.btnManual.addActionListener(this);
        this.pri.btnConocenos.addActionListener(this);
        this.pri.btnProductos.addActionListener(this);
    }

    public void iniciar() {
        pri.setLocationRelativeTo(null);
        pri.setResizable(false);
        btnRep.setLocationRelativeTo(null);
        clientes.setLocationRelativeTo(null);
        habitaciones.setLocationRelativeTo(null);
        adms.setLocationRelativeTo(null);
        reser.setLocationRelativeTo(null);
        conocenos.setLocationRelativeTo(null);
        productos.setLocationRelativeTo(null);
    }

    private void configurarAccesos() {
        switch (nivelAcceso) {
            case 1:
                break;
            case 2:
                pri.BtnAdmins1.setVisible(false);
                break;
            case 3:
                pri.btnClientes.setVisible(false);
                pri.btnHabitaciones.setVisible(false);
                pri.BtnAdmins1.setVisible(false);
                pri.btnFacturacion.setVisible(false);
                break;
            default:
                pri.btnClientes.setVisible(false);
                pri.btnHabitaciones.setVisible(false);
                pri.BtnAdmins1.setVisible(false);
                pri.btnReportes.setVisible(false);
                pri.btnFacturacion.setVisible(false);
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pri.btnClientes) {
            clientes.setVisible(true);
        }

        if (e.getSource() == pri.btnHabitaciones) {
            gestionHab.setVisible(true);
        }

        if (e.getSource() == pri.BtnAdmins1) {
            ad.setVisible(true);
        }

        if (e.getSource() == pri.btnFacturacion) {
            reser.setVisible(true);
        }

        if (e.getSource() == pri.btnSalir) {
            System.exit(0);
        }

        if (e.getSource() == pri.btnReportes) {
            btnRep.setVisible(true);
        }

        if (e.getSource() == pri.btnManual) {
            abrirPDF();
        }

        if (e.getSource() == pri.btnConocenos) {
            conocenos.setVisible(true);
        }
        if(e.getSource() == pri.btnProductos){
            productos.setVisible(true);
        }

        
    }

    private void abrirPDF() {
        String manual = "";
    
                manual = "C:\\Manual\\MANUAL.pdf";
        File archivo = new File(manual);

        if (!archivo.exists()) {
            JOptionPane.showMessageDialog(pri, "El archivo no existe:\n" + manual, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!Desktop.isDesktopSupported()) {
            JOptionPane.showMessageDialog(pri, "La función para abrir archivos no es soportada en este sistema.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Desktop.getDesktop().open(archivo);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(pri, "Error al abrir el archivo:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
