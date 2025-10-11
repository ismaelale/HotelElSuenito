package vista;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


public class Conocenosuwu extends javax.swing.JFrame {

    int largocamara = 170;
    int anchocamara = 110;
    BufferedImage ruta;
    int contador = 0;
    Dimension dimension = new Dimension(largocamara, anchocamara);
    Dimension dimension1 = WebcamResolution.VGA.getSize();

    Webcam webcam;
    WebcamPanel webcamPanel;

    public Conocenosuwu() {
        initComponents();
        this.setLocationRelativeTo(null);

        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "error");
        webcam = Webcam.getDefault();

        if (webcam == null) {
            JOptionPane.showMessageDialog(this, "No se encontró ninguna cámara.");
            return;
        }
        webcam.setViewSize(dimension1);

        webcamPanel = new WebcamPanel(webcam, dimension, false);
        webcamPanel.setFitArea(true);

        panelfoto.setLayout(new FlowLayout());
        panelfoto.add(webcamPanel);

        btnFoto.addActionListener(this::btnFotoActionPerformed);
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        btnGuardar.setEnabled(false);

        btnEncender.addActionListener(e -> {
            Thread t = new Thread(() -> webcamPanel.start());
            t.setDaemon(true);
            t.start();
        });

        btnApagar.addActionListener(e -> {
            webcamPanel.stop();
            lblfoto.setIcon(null);
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnEncender = new javax.swing.JButton();
        btnApagar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        panelfoto = new javax.swing.JPanel();
        lblfoto = new javax.swing.JLabel();
        btnFoto = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(102, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(7, 7, 7, 7, new java.awt.Color(51, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 0, 0));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("APAGAR");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 270, 90, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("ENCENDER");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 90, -1));

        btnEncender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/encender.png"))); // NOI18N
        jPanel2.add(btnEncender, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 60, 50));

        btnApagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/apagado.png"))); // NOI18N
        jPanel2.add(btnApagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 150, 340));

        jPanel3.setBackground(new java.awt.Color(51, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelfoto.setBackground(new java.awt.Color(102, 0, 0));
        panelfoto.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(102, 0, 0)));

        javax.swing.GroupLayout panelfotoLayout = new javax.swing.GroupLayout(panelfoto);
        panelfoto.setLayout(panelfotoLayout);
        panelfotoLayout.setHorizontalGroup(
            panelfotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 176, Short.MAX_VALUE)
        );
        panelfotoLayout.setVerticalGroup(
            panelfotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );

        jPanel3.add(panelfoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 180, 120));

        lblfoto.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(102, 0, 0)));
        jPanel3.add(lblfoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 160, 100));

        btnFoto.setText("Tomar Foto");
        btnFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFotoActionPerformed(evt);
            }
        });
        jPanel3.add(btnFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, -1, -1));

        btnGuardar.setText("Guardar Foto");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel3.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 170, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 410, 220));

        jPanel4.setBackground(new java.awt.Color(51, 0, 0));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 406, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 350, 410, 20));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Imprimir Foto");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 300, -1, 40));

        btnCerrar.setBackground(new java.awt.Color(204, 204, 204));
        btnCerrar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnCerrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrar.setText("Cerrar");
        btnCerrar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCerrar.setBorderPainted(false);
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 80, 30));

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/pdf.png"))); // NOI18N
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });
        jPanel1.add(btnImprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 300, -1, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEncenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncenderActionPerformed
        Thread hilo = new Thread() {
            @Override
            public void run() {
                webcamPanel.start();
            }
        };
        hilo.setDaemon(true);
        hilo.start();

    }//GEN-LAST:event_btnEncenderActionPerformed

    private void btnApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApagarActionPerformed
        webcamPanel.stop();
        lblfoto.setIcon(null);

    }//GEN-LAST:event_btnApagarActionPerformed

    private void btnFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFotoActionPerformed
        if (webcam == null) {
            JOptionPane.showMessageDialog(this, "No hay cámara disponible.");
            return;
        }
        if (!webcam.isOpen()) {
            new Thread(() -> webcamPanel.start()).start();
            try {
                Thread.sleep(200);
            } catch (InterruptedException ignored) {
            }
        }
        BufferedImage img = webcam.getImage();
        if (img == null) {
            JOptionPane.showMessageDialog(this, "No se pudo capturar imagen (¿cámara ocupada?).");
            return;
        }
        ruta = img;
        ImageIcon icon = new ImageIcon(img);
        Icon scaled = new ImageIcon(
                icon.getImage().getScaledInstance(lblfoto.getWidth(), lblfoto.getHeight(), Image.SCALE_SMOOTH)
        );
        lblfoto.setIcon(scaled);
        btnGuardar.setEnabled(true);
    }//GEN-LAST:event_btnFotoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (ruta == null) {
            JOptionPane.showMessageDialog(this, "Primero toma una foto.");
            return;
        }

        String userHome = System.getProperty("user.home");
        File carpeta = new File(userHome, "Pictures");
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        File salida = new File(carpeta, "Foto_" + (contador++) + ".jpg");

        try {
            ImageIO.write(ruta, "jpg", salida);
            JOptionPane.showMessageDialog(this, "Guardado en:\n" + salida.getAbsolutePath());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage());
        }

    }//GEN-LAST:event_btnGuardarActionPerformed
    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
   if (ruta == null) {
        JOptionPane.showMessageDialog(this, "Primero toma una foto.");
        return;
    }

    try {
        String rutaUsuario = System.getProperty("user.home");
        File carpeta = new File(rutaUsuario + "/Desktop/REPORTES");
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        String nombrePDF = carpeta.getAbsolutePath() + "/Foto_Capturada.pdf";

        com.itextpdf.text.Document documento = new com.itextpdf.text.Document();
        com.itextpdf.text.pdf.PdfWriter.getInstance(documento, new java.io.FileOutputStream(nombrePDF));

        documento.open();

        com.itextpdf.text.Paragraph titulo = new com.itextpdf.text.Paragraph(
                "SONRIE PARA LA FOTO!!!\n\n",
                com.itextpdf.text.FontFactory.getFont("Arial", 20, com.itextpdf.text.Font.BOLD, com.itextpdf.text.BaseColor.BLACK)
        );
        titulo.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        documento.add(titulo);

        java.awt.Image awtImage = ruta;
        java.awt.image.BufferedImage bufferedImage = (java.awt.image.BufferedImage) awtImage;
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        javax.imageio.ImageIO.write(bufferedImage, "jpg", baos);
        com.itextpdf.text.Image fotoPDF = com.itextpdf.text.Image.getInstance(baos.toByteArray());

        fotoPDF.scaleToFit(400, 400);
        fotoPDF.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        documento.add(fotoPDF);

        documento.close();

        JOptionPane.showMessageDialog(this, "PDF creado en:\n" + nombrePDF);

        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + nombrePDF);
        } catch (IOException e) {
            System.out.println("No se pudo abrir el PDF automáticamente.");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al crear PDF: " + e.getMessage());
    }
    }//GEN-LAST:event_btnImprimirActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Conocenosuwu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApagar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEncender;
    private javax.swing.JButton btnFoto;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblfoto;
    private javax.swing.JPanel panelfoto;
    // End of variables declaration//GEN-END:variables
}
