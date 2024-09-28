/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.OrderDetail;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author ASUS
 */
public class ListDAO extends DBContext {
    public ArrayList<OrderDetail> findAll(int user_id){
        Connection con;
        try {
            con = DBContext.makeConnection();
            String sql = "SELECT * FROM [dbo].[Products] ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery(); // trả về 1 cái bảng database

            ArrayList<OrderDetail> list = new ArrayList<>();
            while (rs.next()) { // rs sẽ chạy từng dòng xuống trong database
                OrderDetail orderdetail = new OrderDetail();
                orderdetail.setProduct_id(rs.getInt("product_id"));
                orderdetail.setOrder_id(rs.getInt("order_id"));
                orderdetail.setQuantity(rs.getInt("quantity"));
                list.add(orderdetail);
            }
            rs.close();
            ps.close();
            return list;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
