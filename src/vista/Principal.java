package vista;

import java.awt.Color;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


public class Principal extends javax.swing.JFrame {

    String usuario;
    Cursor cursorNormal, cursorClick;
    ImageIcon imagen;
    Icon icono;

    public Principal(String user) {
        this.usuario = user;
        initComponents();

        cambioImagen(lblFondo, "Hotel_El_Suenito_logo.png");
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Image imgNormal = tk.getImage("src/imgs/campana1.png");
        Image imgClick = tk.getImage("src/imgs/campana2.png");

        cursorNormal = tk.createCustomCursor(imgNormal, new Point(0, 0), "Normal");
        cursorClick = tk.createCustomCursor(imgClick, new Point(0, 0), "Click");
        
        setCursor(cursorNormal);
        aplicarCursorATodosLosBotones(this.getContentPane());

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setCursor(cursorClick);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setCursor(cursorNormal);
            }
        });

        lblUsuario.setText(usuario);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public Principal() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public void cambioImagen(JLabel lblNombre, String nombreImagen) {
        imagen = new ImageIcon("src/imgs/" + nombreImagen);
        icono = new ImageIcon(imagen.getImage().getScaledInstance(lblNombre.getWidth(), lblNombre.getHeight(), Image.SCALE_FAST));
        lblNombre.setIcon(icono);
    }
    
private void aplicarCursorATodosLosBotones(Container container) {
    for (Component comp : container.getComponents()) {
        if (comp instanceof JButton) {
            JButton btn = (JButton) comp;

            btn.setCursor(cursorNormal);
            btn.setBackground(new Color(252, 246, 234));
            btn.setForeground(Color.BLACK);
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            btn.setContentAreaFilled(false);
            btn.setOpaque(true);

            btn.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    btn.setBackground(new Color(150, 150, 150));
                    btn.setForeground(Color.WHITE);
                    btn.setCursor(cursorClick);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    btn.setBackground(new Color(252, 246, 234));
                    btn.setForeground(Color.BLACK);
                    btn.setCursor(cursorNormal);
                }
            });
        } else if (comp instanceof Container) {
            aplicarCursorATodosLosBotones((Container) comp);
        }
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnClientes = new javax.swing.JButton();
        btnHabitaciones = new javax.swing.JButton();
        BtnAdmins = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnFacturacion = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        lblUsuario = new javax.swing.JLabel();
        btnConocenos = new javax.swing.JButton();
        btnManual = new javax.swing.JButton();
        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(29, 29, 29));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(96, 24, 44));
        jPanel2.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("USUARIO");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(30, 250, 62, 20);

        btnClientes.setBackground(new java.awt.Color(252, 246, 234));
        btnClientes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/clientes.png"))); // NOI18N
        btnClientes.setText("                        CLIENTES");
        btnClientes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnClientes.setFocusable(false);
        btnClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnClientesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnClientesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnClientesMouseExited(evt);
            }
        });
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        jPanel2.add(btnClientes);
        btnClientes.setBounds(30, 10, 260, 70);

        btnHabitaciones.setBackground(new java.awt.Color(252, 246, 234));
        btnHabitaciones.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHabitaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/habitaciones.png"))); // NOI18N
        btnHabitaciones.setText("                HABITACIONES");
        btnHabitaciones.setActionCommand("         HABITACIONES");
        btnHabitaciones.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnHabitaciones.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnHabitaciones.setFocusable(false);
        btnHabitaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHabitacionesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHabitacionesMouseExited(evt);
            }
        });
        btnHabitaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHabitacionesActionPerformed(evt);
            }
        });
        jPanel2.add(btnHabitaciones);
        btnHabitaciones.setBounds(310, 10, 260, 70);

        BtnAdmins.setBackground(new java.awt.Color(252, 246, 234));
        BtnAdmins.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnAdmins.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/trabajo-en-equipo.png"))); // NOI18N
        BtnAdmins.setText("\t                            \tADMINS");
        BtnAdmins.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnAdmins.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BtnAdmins.setFocusable(false);
        BtnAdmins.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnAdminsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnAdminsMouseExited(evt);
            }
        });
        BtnAdmins.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAdminsActionPerformed(evt);
            }
        });
        jPanel2.add(BtnAdmins);
        BtnAdmins.setBounds(310, 90, 260, 70);

        btnSalir.setBackground(new java.awt.Color(252, 246, 234));
        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/salida.png"))); // NOI18N
        btnSalir.setText("                             SALIR");
        btnSalir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSalir.setFocusable(false);
        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSalirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSalirMouseExited(evt);
            }
        });
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel2.add(btnSalir);
        btnSalir.setBounds(310, 170, 260, 70);

        btnFacturacion.setBackground(new java.awt.Color(252, 246, 234));
        btnFacturacion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnFacturacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/facturacion.png"))); // NOI18N
        btnFacturacion.setText("              RESERVACIONES");
        btnFacturacion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnFacturacion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnFacturacion.setFocusable(false);
        btnFacturacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFacturacionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFacturacionMouseExited(evt);
            }
        });
        jPanel2.add(btnFacturacion);
        btnFacturacion.setBounds(590, 10, 260, 70);

        btnReportes.setBackground(new java.awt.Color(252, 246, 234));
        btnReportes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/reporte.png"))); // NOI18N
        btnReportes.setText("                      REPORTES");
        btnReportes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnReportes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnReportes.setFocusable(false);
        btnReportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnReportesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnReportesMouseExited(evt);
            }
        });
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });
        jPanel2.add(btnReportes);
        btnReportes.setBounds(30, 90, 260, 70);

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lblUsuario);
        lblUsuario.setBounds(100, 250, 180, 20);

        btnConocenos.setBackground(new java.awt.Color(252, 246, 234));
        btnConocenos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnConocenos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/compania_1.png"))); // NOI18N
        btnConocenos.setText("                      CONOCENOS");
        btnConocenos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnConocenos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnConocenos.setFocusable(false);
        btnConocenos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConocenosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConocenosMouseExited(evt);
            }
        });
        btnConocenos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConocenosActionPerformed(evt);
            }
        });
        jPanel2.add(btnConocenos);
        btnConocenos.setBounds(30, 170, 260, 70);

        btnManual.setBackground(new java.awt.Color(252, 246, 234));
        btnManual.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnManual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/manual.png"))); // NOI18N
        btnManual.setText("        MANUAL DE USUARIO");
        btnManual.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnManual.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnManual.setFocusable(false);
        btnManual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnManualMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnManualMouseExited(evt);
            }
        });
        btnManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManualActionPerformed(evt);
            }
        });
        jPanel2.add(btnManual);
        btnManual.setBounds(590, 90, 260, 70);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 880, 270);

        lblFondo.setBackground(new java.awt.Color(255, 255, 255));
        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Hotel_El_Suenito_logo.png"))); // NOI18N
        lblFondo.setText("jLabel3");
        jPanel1.add(lblFondo);
        lblFondo.setBounds(150, 270, 610, 380);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 881, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnClientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClientesMouseEntered
    
    }//GEN-LAST:event_btnClientesMouseEntered

    private void btnClientesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClientesMouseExited
       
    }//GEN-LAST:event_btnClientesMouseExited

    private void btnHabitacionesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHabitacionesMouseEntered
      
    }//GEN-LAST:event_btnHabitacionesMouseEntered

    private void btnHabitacionesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHabitacionesMouseExited
       
    }//GEN-LAST:event_btnHabitacionesMouseExited

    private void BtnAdminsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnAdminsMouseEntered
       
    }//GEN-LAST:event_BtnAdminsMouseEntered

    private void BtnAdminsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnAdminsMouseExited
      
    }//GEN-LAST:event_BtnAdminsMouseExited

    private void btnFacturacionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFacturacionMouseEntered
        
    }//GEN-LAST:event_btnFacturacionMouseEntered

    private void btnFacturacionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFacturacionMouseExited
      
    }//GEN-LAST:event_btnFacturacionMouseExited

    private void btnReportesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportesMouseEntered
       
    }//GEN-LAST:event_btnReportesMouseEntered

    private void btnReportesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportesMouseExited
    
    }//GEN-LAST:event_btnReportesMouseExited

    private void btnSalirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseEntered
      
    }//GEN-LAST:event_btnSalirMouseEntered

    private void btnSalirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseExited
     
    }//GEN-LAST:event_btnSalirMouseExited

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed

    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed

    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnHabitacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHabitacionesActionPerformed

    }//GEN-LAST:event_btnHabitacionesActionPerformed

    private void BtnAdminsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAdminsActionPerformed

    }//GEN-LAST:event_BtnAdminsActionPerformed

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReportesActionPerformed

    private void btnClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClientesMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnClientesMouseClicked

    private void btnConocenosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConocenosMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConocenosMouseEntered

    private void btnConocenosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConocenosMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConocenosMouseExited

    private void btnConocenosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConocenosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConocenosActionPerformed

    private void btnManualMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnManualMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnManualMouseEntered

    private void btnManualMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnManualMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnManualMouseExited

    private void btnManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnManualActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal("Principal").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton BtnAdmins;
    public javax.swing.JButton btnClientes;
    public javax.swing.JButton btnConocenos;
    public javax.swing.JButton btnFacturacion;
    public javax.swing.JButton btnHabitaciones;
    public javax.swing.JButton btnManual;
    public javax.swing.JButton btnReportes;
    public javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblFondo;
    public javax.swing.JLabel lblUsuario;
    // End of variables declaration//GEN-END:variables
}
