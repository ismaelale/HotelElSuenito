package Reportes;

import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import modelo.Conexion;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import vista.Principal;

public class BotonesReportes extends javax.swing.JDialog {

    String nombreC, sentenciaSQL;
    Connection con = null;
    Conexion conecta;

    public BotonesReportes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
    }

    public void conectarBD() {
        conecta = new Conexion();
        con = (Connection) conecta.getConnection();
    }

    private Map<String, Object> buildBaseParams() {
        Map<String, Object> params = new HashMap<>();

        java.io.InputStream logo  = getClass().getResourceAsStream("/Reportes/Hotel_El_Suenito_logo");
        java.io.InputStream stain = getClass().getResourceAsStream("/Reportes/Hotel_El_Suenito_logo");

        System.out.println("logo null? " + (logo == null));
        System.out.println("stain null? " + (stain == null));

        params.put("IMG_LOGO",  logo);
        params.put("IMG_STAIN", stain);

        return params;
    }

    private void callReport(String jasperName, String windowTitle, String promptMsg, String paramKey) {
        try {
            conectarBD();

            URL urlMaestro = getClass().getResource("/Reportes/" + jasperName);
            if (urlMaestro == null) throw new IllegalStateException("No se encontró /Reportes/" + jasperName);

            JasperReport reporte = (JasperReport) JRLoader.loadObject(urlMaestro);

            Map<String, Object> params = buildBaseParams();

            if (paramKey != null) {
                String entrada = (promptMsg == null) ? null : JOptionPane.showInputDialog(this, promptMsg);
                String v = (entrada == null) ? "" : entrada.trim();
                params.put(paramKey, v.isEmpty() ? null : v);
            }

            JasperPrint jprint = JasperFillManager.fillReport(reporte, params, con);

            JasperViewer view = new JasperViewer(jprint, true);
            view.setTitle(windowTitle);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void llamarReportes(String nombre) {
        callReport(nombre, "MOTEL, VE, HOTEL EL SUEÑITO", null, null);
    }

    public void llamarCliente() {
        callReport(
            "reporteCliente.jasper",
            "MOTEL, VE, HOTEL EL SUEÑITO",
            "INGRESE EL NOMBRE DEL CLIENTE PARA LA BÚSQUEDA",
            "nombreC"
        );
    }

    public void llamarComprasHab() {
        callReport(
            "reporteComprasHab.jasper",
            "MOTEL, VE, HOTEL EL SUEÑITO",
            "INGRESE EL NOMBRE DEL CLIENTE PARA LA BÚSQUEDA",
            "nombreC"
        );
    }
    
        public void llamarGrafica() {
        callReport(
            "reporteHabitacionGraficaMas.jasper",
            "MOTEL, VE, HOTEL EL SUEÑITO",
            "INGRESE EL NOMBRE DEL CLIENTE PARA LA BÚSQUEDA",
            "nombreC"
        );
    }

    public void llamarUsuarios() {
        callReport(
            "reporteUsuario.jasper",
            "MOTEL, VE, HOTEL EL SUEÑITO",
            "INGRESE EL NOMBRE DEL USUARIO PARA LA BÚSQUEDA",
            "nombreC"
        );
    }

    public void llamarHabitaciones() {
        callReport(
            "reporteHabitaciones.jasper",
            "MOTEL, VE, HOTEL EL SUEÑITO",
            null,
            null
        );
    }

    public void llamarClientesConTablas() {
        callReport(
            "reporteClientesTabla.jasper",
            "MOTEL, VE, HOTEL EL SUEÑITO",
            "INGRESE EL NOMBRE DEL CLIENTE PARA LA BÚSQUEDA",
            "nombreC"
        );
    }

    public void llamarConocenos() {
        callReport(
            "reporteConocenos.jasper",
            "MOTEL, VE, HOTEL EL SUEÑITO",
            null,
            null
        );
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        botReporteClientes = new javax.swing.JButton();
        botReporteHabitaciones = new javax.swing.JButton();
        botReporteCompraHab = new javax.swing.JButton();
        botReporteClientesTablas = new javax.swing.JButton();
        botReporteUsuarios = new javax.swing.JButton();
        botSalir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        botConocenos = new javax.swing.JButton();
        btnPrincipal = new javax.swing.JButton();
        botGrafica = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(29, 28, 30));

        jPanel1.setBackground(new java.awt.Color(111, 74, 74));

        botReporteClientes.setBackground(new java.awt.Color(136, 74, 74));
        botReporteClientes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botReporteClientes.setForeground(new java.awt.Color(255, 255, 255));
        botReporteClientes.setText("REPORTE DE CLIENTES");
        botReporteClientes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botReporteClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botReporteClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botReporteClientesActionPerformed(evt);
            }
        });

        botReporteHabitaciones.setBackground(new java.awt.Color(136, 74, 74));
        botReporteHabitaciones.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botReporteHabitaciones.setForeground(new java.awt.Color(255, 255, 255));
        botReporteHabitaciones.setText("REPORTE DE HABITACIONES");
        botReporteHabitaciones.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botReporteHabitaciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botReporteHabitaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botReporteHabitacionesActionPerformed(evt);
            }
        });

        botReporteCompraHab.setBackground(new java.awt.Color(136, 74, 74));
        botReporteCompraHab.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botReporteCompraHab.setForeground(new java.awt.Color(255, 255, 255));
        botReporteCompraHab.setText("REPORTE DE COMPRA HABITACIONES");
        botReporteCompraHab.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botReporteCompraHab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botReporteCompraHab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botReporteCompraHabActionPerformed(evt);
            }
        });

        botReporteClientesTablas.setBackground(new java.awt.Color(136, 74, 74));
        botReporteClientesTablas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botReporteClientesTablas.setForeground(new java.awt.Color(255, 255, 255));
        botReporteClientesTablas.setText("REPORTE DE CLIENTES CON TABLAS");
        botReporteClientesTablas.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botReporteClientesTablas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botReporteClientesTablas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botReporteClientesTablasActionPerformed(evt);
            }
        });

        botReporteUsuarios.setBackground(new java.awt.Color(136, 74, 74));
        botReporteUsuarios.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botReporteUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        botReporteUsuarios.setText("REPORTE USUARIOS");
        botReporteUsuarios.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botReporteUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botReporteUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botReporteUsuariosActionPerformed(evt);
            }
        });

        botSalir.setBackground(new java.awt.Color(136, 74, 74));
        botSalir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botSalir.setForeground(new java.awt.Color(255, 255, 255));
        botSalir.setText("SALIR");
        botSalir.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botSalirMouseExited(evt);
            }
        });
        botSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botSalirActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REPORTES");
        jLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        botConocenos.setBackground(new java.awt.Color(136, 74, 74));
        botConocenos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botConocenos.setForeground(new java.awt.Color(255, 255, 255));
        botConocenos.setText("REPORTE CONOCENOS");
        botConocenos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botConocenos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botConocenos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botConocenosActionPerformed(evt);
            }
        });

        btnPrincipal.setBackground(new java.awt.Color(136, 74, 74));
        btnPrincipal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPrincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/icons8-home-48.png"))); // NOI18N
        btnPrincipal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPrincipal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrincipal.setFocusable(false);
        btnPrincipal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPrincipalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPrincipalMouseExited(evt);
            }
        });
        btnPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrincipalActionPerformed(evt);
            }
        });

        botGrafica.setBackground(new java.awt.Color(136, 74, 74));
        botGrafica.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botGrafica.setForeground(new java.awt.Color(255, 255, 255));
        botGrafica.setText("REPORTE GRAFICA HABITACION");
        botGrafica.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botGrafica.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botGrafica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botGraficaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botReporteClientesTablas, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botReporteUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botConocenos, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botReporteHabitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botReporteCompraHab, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botReporteClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPrincipal))
                    .addComponent(botGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrincipal))
                .addGap(18, 18, 18)
                .addComponent(botReporteClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(botReporteHabitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(botReporteCompraHab, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(botGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botReporteClientesTablas, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(botReporteUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botConocenos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botReporteClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botReporteClientesActionPerformed
        llamarCliente();
    }//GEN-LAST:event_botReporteClientesActionPerformed

    private void botReporteHabitacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botReporteHabitacionesActionPerformed
        llamarHabitaciones();
    }//GEN-LAST:event_botReporteHabitacionesActionPerformed

    private void botReporteUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botReporteUsuariosActionPerformed
        llamarUsuarios();
    }//GEN-LAST:event_botReporteUsuariosActionPerformed

    private void botReporteClientesTablasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botReporteClientesTablasActionPerformed
        llamarClientesConTablas();
    }//GEN-LAST:event_botReporteClientesTablasActionPerformed

    private void botReporteCompraHabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botReporteCompraHabActionPerformed
        llamarComprasHab();
    }//GEN-LAST:event_botReporteCompraHabActionPerformed

    private void botSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botSalirActionPerformed
setVisible(false);
    }//GEN-LAST:event_botSalirActionPerformed

    private void botSalirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botSalirMouseExited
       
    }//GEN-LAST:event_botSalirMouseExited

    private void botConocenosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botConocenosActionPerformed
        llamarConocenos();
    }//GEN-LAST:event_botConocenosActionPerformed

    private void btnPrincipalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrincipalMouseEntered

    }//GEN-LAST:event_btnPrincipalMouseEntered

    private void btnPrincipalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrincipalMouseExited

    }//GEN-LAST:event_btnPrincipalMouseExited

    private void btnPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrincipalActionPerformed
setVisible(false);
    }//GEN-LAST:event_btnPrincipalActionPerformed

    private void botGraficaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botGraficaActionPerformed
       llamarGrafica();
    }//GEN-LAST:event_botGraficaActionPerformed

public static void main(String[] args) {
    java.awt.EventQueue.invokeLater(() -> {
        BotonesReportes dialog = new BotonesReportes(new javax.swing.JFrame(), false);
        dialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    });
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botConocenos;
    private javax.swing.JButton botGrafica;
    private javax.swing.JButton botReporteClientes;
    private javax.swing.JButton botReporteClientesTablas;
    private javax.swing.JButton botReporteCompraHab;
    private javax.swing.JButton botReporteHabitaciones;
    private javax.swing.JButton botReporteUsuarios;
    private javax.swing.JButton botSalir;
    public javax.swing.JButton btnPrincipal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
