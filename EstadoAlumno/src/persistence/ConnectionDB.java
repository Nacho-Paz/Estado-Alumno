/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import java.sql.*;

/**
 *
 * @author ignac
 */
public class ConnectionDB {

    private static Connection c;
    private static String url = "jdbc:mysql://localhost/estadoalumnos?serverTimezone=UTC";
    private static String user = "root";
    private static String clave = "";

    /*Completar con la información correspondiente*/
    
    /*public static Connection conect() {
        try {
            c = DriverManager.getConnection(url, user, clave);
            System.out.println("Conexion establecida correctamente.");
            return c;
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexion con la DataBase: " + e.getMessage());
            return null;
        }
    }*/
    
    //Hago esto para que corra ese metodo dentro de try catch, de esta manera cierro y abro las conexiones cuando es necesario
    public static Connection conect() throws SQLException {
        try {
            return DriverManager.getConnection(url, user, clave);
        } catch (SQLException e) {
            throw new SQLException("Error al establecer la conexion con la DataBase: " + e.getMessage());
        }
    }
}
