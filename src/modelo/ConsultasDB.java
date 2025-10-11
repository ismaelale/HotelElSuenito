package modelo;

import java.sql.Connection;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import modelo.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class ConsultasDB extends Conexion {

    Connection con = (Connection) getConnection();
    String sentenciaSQL;
    ResultSet rs = null;
    HabitacionesModel hab;
    PreparedStatement ps = null;
    Clientes_POO cli;
    CompraHab_POO Comhab;

    public boolean crear(Clientes_POO cli) {
        Connection con = (Connection)getConnection();
        sentenciaSQL = "SELECT COUNT(*) FROM clientes WHERE dni = ?";

        try {
            ps = con.prepareStatement(sentenciaSQL);
            ps.setString(1, cli.getDni());
            rs = ps.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(null, "YA EXISTE UN CLIENTE CON ESTE DNI", "ERROR", 0);
                return false;
            } else {
                sentenciaSQL = "INSERT INTO clientes (id_cliente, nombre_completo, dni, telefono) VALUES (?, ?, ?, ?)";
                ps = con.prepareStatement(sentenciaSQL);
                ps.setInt(1, cli.getIdCliente());
                ps.setString(2, cli.getNombreCompleto());
                ps.setString(3, cli.getDni());
                ps.setString(4, cli.getTelefono());
                ps.execute();
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR AL CREAR CLIENTE: " + ex.getMessage(), "ERROR", 0);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.print(ex.getMessage());
            }
        }
    }

    public ArrayList<Clientes_POO> leerTodosClientes() {
        Connection con = (Connection)getConnection();
        ArrayList<Clientes_POO> clientes = new ArrayList<>();
        sentenciaSQL = "SELECT * FROM clientes";

        try {
            ps = con.prepareStatement(sentenciaSQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                cli = new Clientes_POO();
                cli.setIdCliente(rs.getInt("id_cliente"));
                cli.setNombreCompleto(rs.getString("nombre_completo"));
                cli.setDni(rs.getString("dni"));
                cli.setTelefono(rs.getString("telefono"));
                clientes.add(cli);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR AL LEER CLIENTES: " + ex.getMessage(), "ERROR", 0);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.print(ex.getMessage());
            }
        }
        return clientes;
    }

    public boolean buscarCodigoCliente(Clientes_POO cli) {
        Connection con = (Connection)getConnection();
        sentenciaSQL = "SELECT * FROM clientes WHERE id_cliente = ?";

        try {
            ps = con.prepareStatement(sentenciaSQL);
            ps.setInt(1, cli.getIdCliente());
            rs = ps.executeQuery();

            if (rs.next()) {
                cli.setIdCliente(rs.getInt("id_cliente"));
                cli.setNombreCompleto(rs.getString("nombre_completo"));
                cli.setDni(rs.getString("dni"));
                cli.setTelefono(rs.getString("telefono"));
                return true;
            }
            return false;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR AL BUSCAR CLIENTE POR ID: " + ex.getMessage(), "ERROR", 0);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.print(ex.getMessage());
            }
        }
    }

    public boolean buscarNombreCliente(Clientes_POO cli) {
        Connection con = (Connection)getConnection();
        sentenciaSQL = "SELECT * FROM clientes WHERE nombre_completo = ?";

        try {
            ps = con.prepareStatement(sentenciaSQL);
            ps.setString(1, cli.getNombreCompleto());
            rs = ps.executeQuery();

            if (rs.next()) {
                cli.setIdCliente(rs.getInt("id_cliente"));
                cli.setNombreCompleto(rs.getString("nombre_completo"));
                cli.setDni(rs.getString("dni"));
                cli.setTelefono(rs.getString("telefono"));
                return true;
            }
            return false;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR AL BUSCAR CLIENTE POR NOMBRE: " + ex.getMessage(), "ERROR", 0);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.print(ex.getMessage());
            }
        }
    }

    public boolean actualizarCliente(Clientes_POO cli) {
        Connection con = (Connection)getConnection();
        sentenciaSQL = "UPDATE clientes SET nombre_completo = ?, dni = ?, telefono = ? WHERE id_cliente = ?";

        try {
            ps = con.prepareStatement(sentenciaSQL);
            ps.setString(1, cli.getNombreCompleto());
            ps.setString(2, cli.getDni());
            ps.setString(3, cli.getTelefono());
            ps.setInt(4, cli.getIdCliente());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR CLIENTE: " + ex.getMessage(), "ERROR", 0);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.print(ex.getMessage());
            }
        }
    }

    public boolean eliminarCliente(Clientes_POO cli) {
        Connection con = (Connection)getConnection();
        sentenciaSQL = "DELETE FROM clientes WHERE id_cliente = ?";

        try {
            ps = con.prepareStatement(sentenciaSQL);
            ps.setInt(1, cli.getIdCliente());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR CLIENTE: " + ex.getMessage(), "ERROR", 0);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.print(ex.getMessage());
            }
        }
    }

    public List<Integer> obtenerHabitacionesDisponiblesPorTipo(String tipo) {
        List<Integer> lista = new ArrayList<>();
        sentenciaSQL = "SELECT num_hab FROM habitaciones WHERE tipo = ? AND estado = 'Disponible'";
        try (Connection con = (Connection)getConnection()) {
            ps = con.prepareStatement(sentenciaSQL);
            ps.setString(1, tipo);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getInt("num_hab"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener habitaciones: " + e.getMessage());
        }
        return lista;
    }

    public boolean insertarUsuario(Usuario u) {
        con = (Connection)getConnection();
        sentenciaSQL = "INSERT INTO usuarios (nombre_usuario, username, password, estado, rol,acceso) VALUES (?, ?, ?, 'Activo', ?, ?)";

        try {
            ps = con.prepareStatement(sentenciaSQL);
            ps.setString(1, u.getNombreAsignado());
            ps.setString(2, u.getUsername());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getRol());
            ps.setInt(5, u.getNivelAcceso());

            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR AL INSERTAR USUARIO: " + e.getMessage(), "ERROR", 0);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.print(ex.getMessage());
            }
        }
    }

    public ArrayList<Usuario> leerTodosUsuarios() {
        con = (Connection)getConnection();
        ArrayList<Usuario> usuarios = new ArrayList<>();
        sentenciaSQL = "SELECT * FROM usuarios";

        try {
            ps = con.prepareStatement(sentenciaSQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setUsername(rs.getString("username"));
                usuario.setNombreAsignado(rs.getString("nombre_usuario"));
                usuario.setPassword(rs.getString("password"));
                usuario.setEstado(rs.getString("estado"));
                usuario.setRol(rs.getString("rol"));
                usuario.setNivelAcceso(rs.getInt("acceso"));
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR AL LEER USUARIOS: " + ex.getMessage(), "ERROR", 0);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.print(ex.getMessage());
            }
        }
        return usuarios;
    }

    public Usuario buscarUsuarioPorID(int id) {
        Usuario u = null;
        con = (Connection)getConnection();
        sentenciaSQL = "SELECT * FROM usuarios WHERE id_usuario = ?";

        try {
            ps = con.prepareStatement(sentenciaSQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                u = new Usuario();
                u.setIdUsuario(id);
                u.setNombreAsignado(rs.getString("nombre_usuario"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setEstado(rs.getString("estado"));
                u.setRol(rs.getString("rol"));
                u.setNivelAcceso(rs.getInt("acceso"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR AL BUSCAR USUARIO POR ID: " + ex.getMessage(), "ERROR", 0);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.print(ex.getMessage());
            }
        }
        return u;
    }

    public boolean actualizarUsuario(Usuario u) {
        Connection con = (Connection)getConnection();
        sentenciaSQL = "UPDATE usuarios SET nombre_usuario = ?, username = ?, password = ?, rol = ?, acceso = ? WHERE id_usuario = ?";

        try {
            ps = con.prepareStatement(sentenciaSQL);
            ps.setString(1, u.getNombreAsignado());
            ps.setString(2, u.getUsername());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getRol());
            ps.setInt(5, u.getNivelAcceso());
            ps.setInt(6, u.getIdUsuario());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR USUARIO: " + ex.getMessage(), "ERROR", 0);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.print(ex.getMessage());
            }
        }
    }

    public boolean eliminarUsuario(Usuario u) {
        Connection con = (Connection)getConnection();
        sentenciaSQL = "DELETE FROM usuarios WHERE id_usuario = ?";

        try {
            ps = con.prepareStatement(sentenciaSQL);
            ps.setInt(1, u.getIdUsuario());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR USUARIO: " + ex.getMessage(), "ERROR", 0);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.print(ex.getMessage());
            }
        }
    }

    public Usuario validarLogin(String username, String password) {
        String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ? AND estado = 'Activo'";
        Usuario usuario = null;
        Connection con = null;

        try {
            con = (Connection) new Conexion().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setUsername(rs.getString("username"));
                usuario.setNombreAsignado(rs.getString("nombre_usuario"));
                usuario.setPassword(rs.getString("password"));
                usuario.setEstado(rs.getString("estado"));
                usuario.setRol(rs.getString("rol"));
                usuario.setNivelAcceso(rs.getInt("acceso"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.print(ex.getMessage());
            }
        }

        return usuario;
    }

 public boolean crearHabitaciones(HabitacionesModel hab) {
        Connection con = (Connection)getConnection();
        try {
            sentenciaSQL = "INSERT INTO habitaciones (id_hab, tipo, num_hab, precio, foto, estado) Values (?,?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sentenciaSQL);
            ps.setInt(1, hab.getId_hab());
            ps.setString(2, hab.getTipo());
            ps.setInt(3, hab.getNum_hab());
            ps.setDouble(4, hab.getPrecio());
            ps.setString(5, hab.getFoto());
            ps.setString(6, hab.getEstado());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR " + ex.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.print(ex.getMessage());
            }
        }
    }

    public boolean eliminarHabitacion(HabitacionesModel hab) {
        Connection con = (Connection)getConnection();
        String sentenciaSQL = "DELETE FROM habitaciones WHERE id_hab = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sentenciaSQL);
            ps.setInt(1, hab.getId_hab());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR HABITACIÓN: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexión: " + ex.getMessage());
            }
        }
    }

    public boolean actualizarHabitacion(HabitacionesModel hab) {
        Connection con = (Connection)getConnection();
        sentenciaSQL = "UPDATE habitaciones SET tipo = ?, num_hab = ?, precio = ?,foto = ? WHERE id_hab = ?";
        try {
            ps = con.prepareStatement(sentenciaSQL);
            ps.setString(1, hab.getTipo());
            ps.setInt(2, hab.getNum_hab());
            ps.setDouble(3, hab.getPrecio());
            ps.setString(4, hab.getFoto());
            ps.setInt(5, hab.getId_hab());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO ACTUALIZAR LA HABITACIÓN - CONSULTA", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.print(ex.getMessage());
            }
        }
    }

    public boolean buscarHabitaciones(HabitacionesModel hab) {
        Connection con = (Connection)getConnection();
        String sentenciaSQL = "SELECT * FROM habitaciones WHERE id_hab = ? AND estado = 'DISPONIBLE'";

        try {
            PreparedStatement ps = con.prepareStatement(sentenciaSQL);
            ps.setInt(1, hab.getId_hab());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                hab.setId_hab(rs.getInt("id_hab"));
                hab.setTipo(rs.getString("tipo"));
                hab.setNum_hab(rs.getInt("num_hab"));
                hab.setPrecio(rs.getDouble("precio"));
                hab.setFoto(rs.getString("foto"));
                hab.setEstado(rs.getString("estado"));
                return true;
            }

            return false;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO ENCONTRAR LA HABITACIÓN\n" + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;

        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexión: " + ex.getMessage());
            }
        }
    }

    public boolean marcarSalidaHabitaciones(int idCompra) {
        String sql1 = "UPDATE habitaciones SET estado = 'Disponible' "
                + "WHERE num_hab IN ("
                + "  SELECT num_habitacion FROM compra_habitaciones WHERE id_compra = ?"
                + ")";
        String sql2 = "UPDATE compra_habitaciones SET estado_salida = 'Finalizada' WHERE id_compra = ?";

        try {
            con.setAutoCommit(false);

            ps = con.prepareStatement(sql1);
            ps.setInt(1, idCompra);
            ps.executeUpdate();

            ps = con.prepareStatement(sql2);
            ps.setInt(1, idCompra);
            ps.executeUpdate();

            con.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<HabitacionesModel> leerTodos() {
        Connection con = (Connection)getConnection();
        ArrayList<HabitacionesModel> habitaciones = new ArrayList<>();
        sentenciaSQL = "SELECT * FROM habitaciones";

        try {
            ps = con.prepareStatement(sentenciaSQL);
            rs = ps.executeQuery();

            while (rs.next()) {

                HabitacionesModel hab = new HabitacionesModel();

                hab.setId_hab(rs.getInt("id_hab"));
                hab.setTipo(rs.getString("tipo"));
                hab.setNum_hab(rs.getInt("num_hab"));
                hab.setPrecio(rs.getDouble("precio"));
                hab.setEstado(rs.getString("estado"));

                habitaciones.add(hab);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "NO SE PUDIERON LEER LOS DATOS " + ex.getMessage(), "ERROR", 0);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.print(ex.getMessage());
            }
        }
        return habitaciones;
    }

    public String obtenerNombreCliente(int idCliente) {
        String nombre = null;
        sentenciaSQL = "SELECT nombre_completo FROM clientes WHERE id_cliente    = ?";
        try {
            ps = con.prepareStatement(sentenciaSQL);
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();
            if (rs.next()) {
                nombre = rs.getString("nombre_completo");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener nombre del cliente: " + e);
        }
        return nombre;
    }
    
    public ArrayList<String> obtenerTiposHabitacionDisponibles() {
    ArrayList<String> tipos = new ArrayList<>();
    sentenciaSQL = "SELECT DISTINCT tipo FROM habitaciones WHERE estado = 'Disponible'";
    try {
        ps = con.prepareStatement(sentenciaSQL);
        rs = ps.executeQuery();
        while (rs.next()) {
            tipos.add(rs.getString("tipo"));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al obtener tipos de habitación: " + e);
    }
    return tipos;
}


    public ArrayList<String> obtenerHabitacionesPorTipo(String tipo) {
        ArrayList<String> habitaciones = new ArrayList<>();
        sentenciaSQL = "SELECT num_hab FROM habitaciones WHERE tipo = ? AND estado = 'Disponible'";
        try {
            ps = con.prepareStatement(sentenciaSQL);
            ps.setString(1, tipo);
            rs = ps.executeQuery();
            while (rs.next()) {
                habitaciones.add(rs.getString("num_hab"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener habitaciones por tipo: " + e);
        }
        return habitaciones;
    }

    public List<Integer> obtenerNumerosHabitacionesPorTipo(String tipo) {
        List<Integer> numeros = new ArrayList<>();
        try {
            sentenciaSQL = "SELECT num_hab FROM habitaciones WHERE tipo = ? AND estado = 'Disponible'";
            ps = con.prepareStatement(sentenciaSQL);
            ps.setString(1, tipo);
            rs = ps.executeQuery();
            while (rs.next()) {
                numeros.add(rs.getInt("num_hab"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener números de habitación: " + e);
        }
        return numeros;
    }

    public double obtenerPrecioHabitacion(String numero) {
        double precio = 0;
        sentenciaSQL = "SELECT precio FROM habitaciones WHERE num_hab = ?";
        try {
            ps = con.prepareStatement(sentenciaSQL);
            ps.setString(1, numero);
            rs = ps.executeQuery();
            if (rs.next()) {
                precio = rs.getDouble("precio");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener precio de habitación: " + e);
        }
        return precio;
    }

    public boolean registrarCompraHabitacion(int idCliente, String nombreCliente, String tipoHab, String numHab, double precio, String metodoPago, double subtotal, double impuesto, double total, double pago, double cambio, String fechaEntrada, String fechaSalida) {
        boolean registrado = false;

        sentenciaSQL = "INSERT INTO compra_habitaciones (id_cliente, nombre_cliente, tipo_habitacion, num_habitacion, precio, metodo_pago, subtotal, impuesto, total, pago, cambio, fecha_entrada,fecha_salida) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

        try {
            ps = con.prepareStatement(sentenciaSQL);
            ps.setInt(1, idCliente);
            ps.setString(2, nombreCliente);
            ps.setString(3, tipoHab);
            ps.setString(4, numHab);
            ps.setDouble(5, precio);
            ps.setString(6, metodoPago);
            ps.setDouble(7, subtotal);
            ps.setDouble(8, impuesto);
            ps.setDouble(9, total);
            ps.setDouble(10, pago);
            ps.setDouble(11, cambio);
            ps.setString(12, fechaEntrada);
            ps.setString(13, fechaSalida);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                registrado = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar compra: " + e.getMessage());
        }
        return registrado;
    }

    public ArrayList<CompraHab_POO> obtenerComprasActivas() {
    ArrayList<CompraHab_POO> lista = new ArrayList<>();
    String sql = "SELECT * FROM compra_habitaciones WHERE estado_salida = 'Activa'";
    try {
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            CompraHab_POO compra = new CompraHab_POO(
                rs.getInt("id_compra"),
                rs.getInt("id_cliente"),
                rs.getString("nombre_cliente"),
                rs.getString("tipo_habitacion"),
                rs.getString("num_habitacion"),
                rs.getDouble("precio"),
                rs.getString("fecha_entrada"),
                rs.getString("fecha_salida")
            );
          
            compra.setSubtotal(rs.getDouble("subtotal"));
            compra.setImpuesto(rs.getDouble("impuesto"));
            compra.setTotal(rs.getDouble("total"));
            
            lista.add(compra);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al obtener compras activas: " + e.getMessage());
    }
    return lista;
}



    public boolean actualizarFechaSalidaCompra(int idCompra, String nuevaFechaSalida) {
        String sql = "UPDATE compra_habitaciones SET fecha_salida = ? WHERE id_compra = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, nuevaFechaSalida);
            ps.setInt(2, idCompra);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar fecha de salida: " + e.getMessage());
            return false;
        }
    }

public boolean actualizarTotalesYFechaSalida(int idCompra, String fechaSalida, double subtotal, double impuesto, double total) {
    String sql = "UPDATE compra_habitaciones SET fecha_salida = ?, subtotal = ?, impuesto = ?, total = ? WHERE id_compra = ?";
    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, fechaSalida);
        ps.setDouble(2, subtotal);
        ps.setDouble(3, impuesto);
        ps.setDouble(4, total);
        ps.setInt(5, idCompra);

        int filas = ps.executeUpdate();
        return filas > 0;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al actualizar totales y fecha salida: " + e.getMessage());
        return false;
    }
}





    
    public CompraHab_POO obtenerCompraPorId(int idCompra) {
        CompraHab_POO compra = null;
        String sql = "SELECT * FROM compra_habitaciones WHERE id_compra = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCompra);
            rs = ps.executeQuery();
            if (rs.next()) {
                compra = new CompraHab_POO(
                        rs.getInt("id_compra"),
                        rs.getInt("id_cliente"),
                        rs.getString("nombre_cliente"),
                        rs.getString("tipo_habitacion"),
                        rs.getString("num_habitacion"),
                        rs.getDouble("precio"),
                        rs.getString("fecha_entrada"),
                        rs.getString("fecha_salida")
                );
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener compra por ID: " + e.getMessage());
        }
        return compra;
    }
    
public ArrayList<CompraHab_POO> obtenerHabitacionesPorCompra(int idCompra) {
    ArrayList<CompraHab_POO> lista = new ArrayList<>();
    String sql = "SELECT * FROM compra_habitaciones WHERE id_compra = ?";
    try {
        ps = con.prepareStatement(sql);
        ps.setInt(1, idCompra);
        rs = ps.executeQuery();
        while (rs.next()) {
            CompraHab_POO compra = new CompraHab_POO(
                rs.getInt("id_compra"),
                rs.getInt("id_cliente"),
                rs.getString("nombre_cliente"),
                rs.getString("tipo_habitacion"),
                rs.getString("num_habitacion"),
                rs.getDouble("precio"),
                rs.getString("fecha_entrada"),
                rs.getString("fecha_salida")
            );
            lista.add(compra);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al obtener habitaciones por compra: " + e.getMessage());
    }
    return lista;
}

    
    
public Clientes_POO buscarClientePorId(int idCliente) {
    Clientes_POO cliente = null;
    String sql = "SELECT dni, nombre_completo, telefono FROM clientes WHERE id_cliente = ?";
    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, idCliente);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                cliente = new Clientes_POO();
                cliente.setDni(rs.getString("dni"));
                cliente.setNombreCompleto(rs.getString("nombre_completo"));
                cliente.setTelefono(rs.getString("telefono"));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return cliente;
}





public void buscarHabitacionDisponible(String tipo, vista.Reservaciones vista) {
     
        con = (Connection)getConnection();
        String sql = "SELECT num_hab FROM habitaciones WHERE tipo = ? AND estado = 'Disponible' ORDER BY num_hab";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, tipo);
            rs = ps.executeQuery();
            vista.cboNumHab.removeAllItems();
            while (rs.next()) {
                vista.cboNumHab.addItem(Integer.valueOf(rs.getInt("num_hab")));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar habitaciones disponibles: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.print(ex.getMessage());
            }
        }
    }


public ArrayList<Reservacion> obtenerReservasEstado(String estado) {
    ArrayList<Reservacion> lista = new ArrayList<>();
    sentenciaSQL = "SELECT * FROM reservaciones WHERE estado_reserva = ?";

    try (Connection con = (Connection)getConnection()) {
        ps = con.prepareStatement(sentenciaSQL);
        ps.setString(1, estado);
        rs = ps.executeQuery();

        while (rs.next()) {
            Reservacion r = new Reservacion();
            r.setIdReservacion(rs.getInt("id_reservacion"));
            r.setIdCliente(rs.getInt("id_cliente"));
            r.setNombreCliente(rs.getString("nombre_cliente"));
            r.setTipoHabitacion(rs.getString("tipo_habitacion"));
            r.setNumHabitacion(rs.getInt("num_habitacion"));
            r.setFechaEntrada(rs.getString("fecha_entrada"));
            r.setFechaSalida(rs.getString("fecha_salida"));
            r.setEstadoReserva(rs.getString("estado_reserva"));
            lista.add(r);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al obtener reservas: " + e.getMessage());
    }
    return lista;
}

public ArrayList<Reservacion> obtenerTodasReservas() {
    ArrayList<Reservacion> lista = new ArrayList<>();
    sentenciaSQL = "SELECT * FROM reservaciones WHERE estado_reserva = 'Activa'";

    try (Connection con = (Connection)getConnection()) {
        ps = con.prepareStatement(sentenciaSQL);
        rs = ps.executeQuery();

        while (rs.next()) {
            Reservacion r = new Reservacion();
            r.setIdReservacion(rs.getInt("id_reservacion"));
            r.setIdCliente(rs.getInt("id_cliente"));
            r.setNombreCliente(rs.getString("nombre_cliente"));
            r.setTipoHabitacion(rs.getString("tipo_habitacion"));
            r.setNumHabitacion(rs.getInt("num_habitacion"));
            r.setFechaEntrada(rs.getString("fecha_entrada"));
            r.setFechaSalida(rs.getString("fecha_salida"));
            r.setEstadoReserva(rs.getString("estado_reserva"));
            lista.add(r);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al obtener todas las reservas: " + e.getMessage());
    }
    return lista;
}

public boolean insertarReservacion(Reservacion r) {
    Connection con = (Connection)getConnection();
    sentenciaSQL = "INSERT INTO reservaciones (id_cliente, nombre_cliente, tipo_habitacion, num_habitacion, fecha_entrada, fecha_salida, estado_reserva) VALUES (?, ?, ?, ?, ?, ?, ?)";

    try {
        ps = con.prepareStatement(sentenciaSQL);
        ps.setInt(1, r.getIdCliente());
        ps.setString(2, r.getNombreCliente());
        ps.setString(3, r.getTipoHabitacion());
        ps.setInt(4, r.getNumHabitacion());
        ps.setString(5, r.getFechaEntrada());
        ps.setString(6, r.getFechaSalida());
        ps.setString(7, r.getEstadoReserva());

        ps.execute();
        return true;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al insertar reservación: " + e.getMessage());
        return false;
    } finally {
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }
}

public Reservacion buscarReservacionPorId(int idReserva) {
    Reservacion r = null;
    Connection con = (Connection)getConnection();
    sentenciaSQL = "SELECT * FROM reservaciones WHERE id_reservacion = ?";

    try {
        ps = con.prepareStatement(sentenciaSQL);
        ps.setInt(1, idReserva);
        rs = ps.executeQuery();

        if (rs.next()) {
            r = new Reservacion();
            r.setIdReservacion(rs.getInt("id_reservacion"));
            r.setIdCliente(rs.getInt("id_cliente"));
            r.setNombreCliente(rs.getString("nombre_cliente"));
            r.setTipoHabitacion(rs.getString("tipo_habitacion"));
            r.setNumHabitacion(rs.getInt("num_habitacion"));
            r.setFechaEntrada(rs.getString("fecha_entrada"));
            r.setFechaSalida(rs.getString("fecha_salida"));
            r.setEstadoReserva(rs.getString("estado_reserva"));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al buscar reservación: " + e.getMessage());
    } finally {
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }
    return r;
}

public boolean actualizarReservacion(Reservacion r) {
    Connection con = (Connection)getConnection();
    sentenciaSQL = "UPDATE reservaciones SET tipo_habitacion = ?, num_habitacion = ?, fecha_entrada = ?, fecha_salida = ? WHERE id_reservacion = ?";

    try {
        ps = con.prepareStatement(sentenciaSQL);
        ps.setString(1, r.getTipoHabitacion());
        ps.setInt(2, r.getNumHabitacion());
        ps.setString(3, r.getFechaEntrada());
        ps.setString(4, r.getFechaSalida());
        ps.setInt(5, r.getIdReservacion());

        ps.execute();
        return true;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al actualizar reservación: " + e.getMessage());
        return false;
    } finally {
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }
}

public boolean eliminarReservacion(int idReserva) {
    Connection con = (Connection)getConnection();
    try {
        sentenciaSQL = "SELECT tipo_habitacion, num_habitacion FROM reservaciones WHERE id_reservacion = ?";
        ps = con.prepareStatement(sentenciaSQL);
        ps.setInt(1, idReserva);
        rs = ps.executeQuery();

        String tipoHab = null;
        int numHab = 0;
        if (rs.next()) {
            tipoHab = rs.getString("tipo_habitacion");
            numHab = rs.getInt("num_habitacion");
        }

        sentenciaSQL = "DELETE FROM reservaciones WHERE id_reservacion = ?";
        ps = con.prepareStatement(sentenciaSQL);
        ps.setInt(1, idReserva);
        int filas = ps.executeUpdate();

        if (filas > 0 && tipoHab != null) {
            sentenciaSQL = "UPDATE habitaciones SET estado = 'Disponible' WHERE tipo = ? AND num_hab = ?";
            ps = con.prepareStatement(sentenciaSQL);
            ps.setString(1, tipoHab);
            ps.setInt(2, numHab);
            ps.executeUpdate();
        }

        return filas > 0;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al eliminar reservación: " + e.getMessage());
        return false;
    } finally {
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    
        }
    }

public String buscarNombreClientePorId(int idCliente) {
    String nombreCliente = null;
    sentenciaSQL = "SELECT nombre_completo FROM clientes WHERE id_cliente = ?";

    try (Connection con = (Connection)getConnection()) {
        ps = con.prepareStatement(sentenciaSQL);
        ps.setInt(1, idCliente);
        rs = ps.executeQuery();

        if (rs.next()) {
            nombreCliente = rs.getString("nombre_completo");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al buscar nombre del cliente: " + e.getMessage());
    }

    return nombreCliente;
}



public boolean actualizarEstadoReservacion(int idReserva, String nuevoEstado) {
    Connection con = (Connection)getConnection();
    sentenciaSQL = "UPDATE reservaciones SET estado_reserva = ? WHERE id_reservacion = ?";

    try {
        ps = con.prepareStatement(sentenciaSQL);
        ps.setString(1, nuevoEstado);
        ps.setInt(2, idReserva);
        ps.execute();
        return true;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al actualizar estado de reservación: " + e.getMessage());
        return false;
    } finally {
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }
}

public boolean actualizarEstadoHabitacion(String numHab, String nuevoEstado) {
    Connection con = (Connection)getConnection();
    sentenciaSQL = "UPDATE habitaciones SET estado = ? WHERE num_hab = ?";

    try {
        ps = con.prepareStatement(sentenciaSQL);
        ps.setString(1, nuevoEstado);
        ps.setInt(2, Integer.parseInt(numHab));
        ps.execute();
        return true;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al actualizar estado de habitación: " + e.getMessage());
        return false;
    } finally {
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }
}}

  
