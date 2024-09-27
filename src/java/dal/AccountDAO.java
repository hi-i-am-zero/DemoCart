/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Account;
import java.sql.SQLException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ASUS
 */
public class AccountDAO extends DBContext {

    public Account findByUsernamePassword(String username, String password) {
        //-connect with DB
        Connection con;
        try {
        con = DBContext.makeConnection();
        //-chuan bi cau lenh SQL
        String sql = "SELECT *\n"
                + "  FROM [dbo].[Users]\n"
                + "WHERE [username] = ? AND  [password] = ? ";
            //-tao doi tuong PrepareStatement
            PreparedStatement ps = con.prepareStatement(sql);

            //-set parameter (optional)
            ps.setObject(1, username);
            ps.setObject(2, password);
            //-thuc thi cau lenh
            ResultSet rs = ps.executeQuery();
            //-tra ve ket qua
            if (rs.next()) {
                String username_Found = rs.getString("username").trim();
                String password_Found = rs.getString("password").trim();
                String email = rs.getString("email");
                String fullname = rs.getString("full_name");
                String address = rs.getString("address");
                String created_at = rs.getString("created_at");
                String role = rs.getString("role");
                Account account = new Account(0, username, email, password, fullname, address, created_at, role);
                return account;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    public static void main(String[] args) {
        System.out.println(new AccountDAO().findByUsernamePassword("qwe", "2").getRole());
    }
}
