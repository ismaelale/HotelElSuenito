package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.ADMINS;
import vista.Administracion_de_usuarios;
import vista.Habitaciones;
import vista.Principal;

public class ADMINS_Controlador implements ActionListener {
   
    private Principal pri;
    private ADMINS ad;
    private Administracion_de_usuarios aduser;
    private Habitaciones habitaciones;

    public ADMINS_Controlador(Principal pri,Administracion_de_usuarios aduser, ADMINS ad,Habitaciones habitaciones) {
        
        this.pri= pri;
        this.ad = ad;
        this.habitaciones = habitaciones;
        this.aduser= aduser;

        this.ad.btnPrincipal.addActionListener(this);
        this.ad.btnUsuarios.addActionListener(this);
        this.ad.btnHabitaciones.addActionListener(this);
        this.ad.btnSalir.addActionListener(this);
    }

    public void iniciar() {
        aduser.setLocationRelativeTo(null);
        habitaciones.setLocationRelativeTo(null);
        ad.setLocationRelativeTo(null);
        pri.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == ad.btnHabitaciones) {
            habitaciones.setVisible(true);
            ad.dispose();
        }

        if (e.getSource() == ad.btnPrincipal) {
            pri.setVisible(true);
            ad.dispose();
        }

        if (e.getSource() == ad.btnUsuarios) {
            aduser.setVisible(true);
            ad.dispose();
        }

        if (e.getSource() == ad.btnSalir) {
            ad.setVisible(true);
            ad.dispose();
        }
    }
}