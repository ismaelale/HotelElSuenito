package vista;

import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;

public class Gestion_De_Habitaciones extends javax.swing.JFrame {
    String usuario;
    public Gestion_De_Habitaciones(String user) {
        this.usuario = user;
        initComponents();
        cambioImagen(lblLogo, "alas.png");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        jtfIdCompra.setText("0");
        lblUsuario1.setText(usuario);
    }

    ImageIcon imagen;
    Icon icono;
    
    public void cambioImagen(JLabel lblNombre, String nombreImagen) {
        imagen = new ImageIcon("src/imgs/" + nombreImagen);
        icono = new ImageIcon(imagen.getImage().getScaledInstance(lblNombre.getWidth(), lblNombre.getHeight(), Image.SCALE_FAST));
        lblNombre.setIcon(icono);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jcbMetodoPago = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jtfIdCliente = new javax.swing.JTextField();
        jtfNombreCliente = new javax.swing.JTextField();
        jcbNumHabitacion = new javax.swing.JComboBox<>();
        btnAnadir = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnLeer = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jtfBuscarIdCompra = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnBuscarVendida = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtHabitaciones = new javax.swing.JTable();
        lblSubtotal = new javax.swing.JLabel();
        lblIsv = new javax.swing.JLabel();
        lblTot = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jtfPago = new javax.swing.JTextField();
        lblCambio = new javax.swing.JLabel();
        jtfSubtotal = new javax.swing.JTextField();
        jtfImpuesto = new javax.swing.JTextField();
        jtfTotal = new javax.swing.JTextField();
        jtfCambio = new javax.swing.JTextField();
        btnRenovarEstadia = new javax.swing.JButton();
        btnRegistrarCompra = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtVentas = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        btnMarcarSalida = new javax.swing.JButton();
        jcbTipoHab = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jtfFecha = new javax.swing.JTextField();
        jtfIdCompra = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnBuscarCliente = new javax.swing.JButton();
        jftFechaSalida = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jtfReservacion = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtNumHab = new javax.swing.JTextField();
        lblUsuario1 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblNombreUsuario1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(245, 245, 220));

        jPanel2.setBackground(new java.awt.Color(96, 24, 44));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/alas.png"))); // NOI18N
        lblLogo.setText("jLabel1");
        jPanel2.add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 139, 110));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("GESTIÓN DE HABITACIONES");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, -1, -1));

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/icons8-home-48.png"))); // NOI18N
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel2.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 40, -1, -1));

        jPanel3.setBackground(new java.awt.Color(252, 246, 234));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "DATOS DEL CLIENTE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("ID COMPRA");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 70, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("RESERVACION");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 90, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("TIPO HAB");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 60, 14));

        jcbMetodoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Tarjeta de Credito", "TEF" }));
        jcbMetodoPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbMetodoPagoActionPerformed(evt);
            }
        });
        jPanel3.add(jcbMetodoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, 200, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("FECHA ENTRADA");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 100, -1));

        jtfIdCliente.setToolTipText("");
        jtfIdCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtfIdClienteMouseClicked(evt);
            }
        });
        jtfIdCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfIdClienteKeyTyped(evt);
            }
        });
        jPanel3.add(jtfIdCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 200, -1));

        jtfNombreCliente.setToolTipText("");
        jtfNombreCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtfNombreClienteMouseClicked(evt);
            }
        });
        jtfNombreCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfNombreClienteActionPerformed(evt);
            }
        });
        jtfNombreCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfNombreClienteKeyTyped(evt);
            }
        });
        jPanel3.add(jtfNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, 200, -1));

        jcbNumHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbNumHabitacionActionPerformed(evt);
            }
        });
        jPanel3.add(jcbNumHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 200, -1));

        btnAnadir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAnadir.setText("Añadir");
        btnAnadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnadirActionPerformed(evt);
            }
        });
        jPanel3.add(btnAnadir, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 370, 70, -1));

        jPanel5.setBackground(new java.awt.Color(252, 246, 234));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnLeer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/icons8-buscar-contactos-24.png"))); // NOI18N
        btnLeer.setText("LEER");
        btnLeer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLeerActionPerformed(evt);
            }
        });

        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/icons8-borrar-24.png"))); // NOI18N
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        jtfBuscarIdCompra.setToolTipText("");
        jtfBuscarIdCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtfBuscarIdCompraMouseClicked(evt);
            }
        });
        jtfBuscarIdCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfBuscarIdCompraKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("ID");

        btnBuscarVendida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/search-14-24.png"))); // NOI18N
        btnBuscarVendida.setBorder(null);
        btnBuscarVendida.setBorderPainted(false);
        btnBuscarVendida.setContentAreaFilled(false);
        btnBuscarVendida.setFocusPainted(false);
        btnBuscarVendida.setFocusable(false);
        btnBuscarVendida.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/search-15-24.png"))); // NOI18N
        btnBuscarVendida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarVendidaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtfBuscarIdCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarVendida, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                        .addComponent(btnLeer, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtfBuscarIdCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))
                    .addComponent(btnBuscarVendida))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLeer)
                    .addComponent(btnLimpiar))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 340, 130));

        jPanel1.setBackground(new java.awt.Color(96, 24, 44));
        jPanel1.setLayout(null);

        jtHabitaciones.setBackground(new java.awt.Color(252, 246, 234));
        jtHabitaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tipo", "Costo", "Num Hab", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtHabitaciones);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(0, 110, 784, 172);

        lblSubtotal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSubtotal.setForeground(new java.awt.Color(255, 255, 255));
        lblSubtotal.setText("SUBTOTAL");
        jPanel1.add(lblSubtotal);
        lblSubtotal.setBounds(40, 10, 76, 16);

        lblIsv.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblIsv.setForeground(new java.awt.Color(255, 255, 255));
        lblIsv.setText("IMPUESTO ");
        jPanel1.add(lblIsv);
        lblIsv.setBounds(250, 10, 82, 16);

        lblTot.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTot.setForeground(new java.awt.Color(255, 255, 255));
        lblTot.setText("TOTAL");
        jPanel1.add(lblTot);
        lblTot.setBounds(250, 60, 59, 16);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("MONTO:");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(40, 60, 48, 16);

        jtfPago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfPagoKeyPressed(evt);
            }
        });
        jPanel1.add(jtfPago);
        jtfPago.setBounds(130, 60, 90, 22);

        lblCambio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCambio.setForeground(new java.awt.Color(255, 255, 255));
        lblCambio.setText("CAMBIO");
        jPanel1.add(lblCambio);
        lblCambio.setBounds(470, 40, 69, 20);

        jtfSubtotal.setToolTipText("");
        jtfSubtotal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtfSubtotalMouseClicked(evt);
            }
        });
        jtfSubtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfSubtotalActionPerformed(evt);
            }
        });
        jtfSubtotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfSubtotalKeyTyped(evt);
            }
        });
        jPanel1.add(jtfSubtotal);
        jtfSubtotal.setBounds(130, 10, 88, 22);

        jtfImpuesto.setToolTipText("");
        jtfImpuesto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtfImpuestoMouseClicked(evt);
            }
        });
        jtfImpuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfImpuestoActionPerformed(evt);
            }
        });
        jtfImpuesto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfImpuestoKeyTyped(evt);
            }
        });
        jPanel1.add(jtfImpuesto);
        jtfImpuesto.setBounds(350, 10, 91, 22);

        jtfTotal.setToolTipText("");
        jtfTotal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtfTotalMouseClicked(evt);
            }
        });
        jtfTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfTotalActionPerformed(evt);
            }
        });
        jtfTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfTotalKeyTyped(evt);
            }
        });
        jPanel1.add(jtfTotal);
        jtfTotal.setBounds(350, 60, 91, 22);
        jPanel1.add(jtfCambio);
        jtfCambio.setBounds(560, 40, 90, 22);

        btnRenovarEstadia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRenovarEstadia.setText("RENOVAR ESTADIA");
        btnRenovarEstadia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRenovarEstadiaActionPerformed(evt);
            }
        });
        jPanel1.add(btnRenovarEstadia);
        btnRenovarEstadia.setBounds(0, 490, 144, 20);

        btnRegistrarCompra.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRegistrarCompra.setText("Guardar");
        btnRegistrarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarCompraActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegistrarCompra);
        btnRegistrarCompra.setBounds(670, 80, 90, 20);

        jtVentas.setBackground(new java.awt.Color(252, 246, 234));
        jtVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Compra", "Cliente", "Habitaciones", "Subtotal", "Impuestos", "Total", "Fecha Entrada", "FechaSalida"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jtVentas);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(0, 310, 778, 172);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("RENOVAR ESTADIA");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(0, 290, 120, 14);

        btnMarcarSalida.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnMarcarSalida.setText("Marcar Salida");
        btnMarcarSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarcarSalidaActionPerformed(evt);
            }
        });
        jPanel1.add(btnMarcarSalida);
        btnMarcarSalida.setBounds(160, 490, 121, 20);

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 780, 550));

        jPanel3.add(jcbTipoHab, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 200, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("FECHA SALIDA");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 90, -1));

        jtfFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfFechaActionPerformed(evt);
            }
        });
        jPanel3.add(jtfFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 200, -1));

        jtfIdCompra.setText("0");
        jtfIdCompra.setToolTipText("");
        jtfIdCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtfIdCompraMouseClicked(evt);
            }
        });
        jtfIdCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfIdCompraActionPerformed(evt);
            }
        });
        jtfIdCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfIdCompraKeyTyped(evt);
            }
        });
        jPanel3.add(jtfIdCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 200, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("ID CLIENTE");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 70, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("NOMBRE CLIENTE");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 100, -1));

        btnBuscarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/search-14-24.png"))); // NOI18N
        btnBuscarCliente.setBorder(null);
        btnBuscarCliente.setBorderPainted(false);
        btnBuscarCliente.setContentAreaFilled(false);
        btnBuscarCliente.setFocusPainted(false);
        btnBuscarCliente.setFocusable(false);
        btnBuscarCliente.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/search-15-24.png"))); // NOI18N
        jPanel3.add(btnBuscarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, 30, -1));
        jPanel3.add(jftFechaSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 200, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("METODO PAGO");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 90, -1));

        jtfReservacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfReservacionActionPerformed(evt);
            }
        });
        jtfReservacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfReservacionKeyPressed(evt);
            }
        });
        jPanel3.add(jtfReservacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 340, 200, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setText("NUM HAB");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 60, 14));

        txtNumHab.setToolTipText("");
        txtNumHab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNumHabMouseClicked(evt);
            }
        });
        txtNumHab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumHabActionPerformed(evt);
            }
        });
        txtNumHab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumHabKeyTyped(evt);
            }
        });
        jPanel3.add(txtNumHab, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, 200, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 135, 1210, 570));

        lblUsuario1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblUsuario1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lblUsuario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 300, 20));

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 130, 22));

        lblNombreUsuario1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNombreUsuario1.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreUsuario1.setText("USUARIO:");
        jPanel2.add(lblNombreUsuario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed

    }//GEN-LAST:event_btnSalirActionPerformed

    private void jtfIdClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfIdClienteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfIdClienteMouseClicked

    private void jtfIdClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfIdClienteKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfIdClienteKeyTyped

    private void jtfNombreClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfNombreClienteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfNombreClienteMouseClicked

    private void jtfNombreClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfNombreClienteKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfNombreClienteKeyTyped

    private void btnAnadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnadirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAnadirActionPerformed

    private void btnRegistrarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrarCompraActionPerformed

    private void jtfIdCompraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfIdCompraMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfIdCompraMouseClicked

    private void jtfIdCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfIdCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfIdCompraActionPerformed

    private void jtfIdCompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfIdCompraKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfIdCompraKeyTyped

    private void jtfSubtotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfSubtotalKeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_jtfSubtotalKeyTyped

    private void jtfSubtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfSubtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfSubtotalActionPerformed

    private void jtfSubtotalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfSubtotalMouseClicked
        this.jtfSubtotal.setText(null);
    }//GEN-LAST:event_jtfSubtotalMouseClicked

    private void jtfImpuestoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfImpuestoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfImpuestoMouseClicked

    private void jtfImpuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfImpuestoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfImpuestoActionPerformed

    private void jtfImpuestoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfImpuestoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfImpuestoKeyTyped

    private void jtfTotalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfTotalMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfTotalMouseClicked

    private void jtfTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfTotalActionPerformed

    private void jtfTotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfTotalKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfTotalKeyTyped

    private void jtfPagoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfPagoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfPagoKeyPressed

    private void btnRenovarEstadiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRenovarEstadiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRenovarEstadiaActionPerformed

    private void jtfBuscarIdCompraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfBuscarIdCompraMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfBuscarIdCompraMouseClicked

    private void jtfBuscarIdCompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfBuscarIdCompraKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfBuscarIdCompraKeyTyped

    private void btnBuscarVendidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarVendidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarVendidaActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnLeerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLeerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLeerActionPerformed

    private void btnMarcarSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMarcarSalidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMarcarSalidaActionPerformed

    private void jtfReservacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfReservacionKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfReservacionKeyPressed

    private void jtfFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfFechaActionPerformed

    private void jtfNombreClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfNombreClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfNombreClienteActionPerformed

    private void jtfReservacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfReservacionActionPerformed
   
    }//GEN-LAST:event_jtfReservacionActionPerformed

    private void jcbMetodoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbMetodoPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbMetodoPagoActionPerformed

    private void txtNumHabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNumHabMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumHabMouseClicked

    private void txtNumHabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumHabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumHabActionPerformed

    private void txtNumHabKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumHabKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumHabKeyTyped

    private void jcbNumHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbNumHabitacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbNumHabitacionActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gestion_De_Habitaciones("Gestion Hab").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAnadir;
    public javax.swing.JButton btnBuscarCliente;
    public javax.swing.JButton btnBuscarVendida;
    public javax.swing.JButton btnLeer;
    public javax.swing.JButton btnLimpiar;
    public javax.swing.JButton btnMarcarSalida;
    public javax.swing.JButton btnRegistrarCompra;
    public javax.swing.JButton btnRenovarEstadia;
    public javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JComboBox<String> jcbMetodoPago;
    public javax.swing.JComboBox<String> jcbNumHabitacion;
    public javax.swing.JComboBox<String> jcbTipoHab;
    public javax.swing.JTextField jftFechaSalida;
    public javax.swing.JTable jtHabitaciones;
    public javax.swing.JTable jtVentas;
    public javax.swing.JTextField jtfBuscarIdCompra;
    public javax.swing.JTextField jtfCambio;
    public javax.swing.JTextField jtfFecha;
    public javax.swing.JTextField jtfIdCliente;
    public javax.swing.JTextField jtfIdCompra;
    public javax.swing.JTextField jtfImpuesto;
    public javax.swing.JTextField jtfNombreCliente;
    public javax.swing.JTextField jtfPago;
    public javax.swing.JTextField jtfReservacion;
    public javax.swing.JTextField jtfSubtotal;
    public javax.swing.JTextField jtfTotal;
    public javax.swing.JLabel lblCambio;
    public javax.swing.JLabel lblIsv;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblNombreUsuario1;
    public javax.swing.JLabel lblSubtotal;
    public javax.swing.JLabel lblTot;
    public javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblUsuario1;
    public javax.swing.JTextField txtNumHab;
    // End of variables declaration//GEN-END:variables
}
