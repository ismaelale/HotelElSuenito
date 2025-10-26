package modelo;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    Connection c = null;
    private String bd = "bd_el_suenito";
    private String user = "root";
    private String pass = "ismael1234";
    private String url = "jdbc:mysql://localhost:3306/"+bd;
    
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c=(Connection) DriverManager.getConnection(this.url,this.user, this.pass);
            //System.out.print("Conexion exitosa");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex){
            System.out.print("No se pudo realizar la conexion " + ex);
        }
        return c;
    }
    
    public static void main(String[] args){
        Conexion con = new Conexion();
        con.getConnection();
    }
}