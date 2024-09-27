/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class DBContext {
    public static Connection makeConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=ElectronicShop;";
        Connection connection = DriverManager.getConnection(url, "sa", "123");
        return connection;
    }

    public static void main(String[] args) {
        try {
            System.out.println(DBContext.makeConnection());
        } catch (ClassNotFoundException | SQLException ex) {
            java.util.logging.Logger.getLogger(DBContext.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
