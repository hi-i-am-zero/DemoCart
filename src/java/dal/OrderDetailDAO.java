/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.OrderDetail;
import java.sql.*;


/**
 *
 * @author ASUS
 */
public class OrderDetailDAO extends DBContext{
    public OrderDetail getOderDetailByProductID(int product_id){
        Connection con;
        try {
            con = DBContext.makeConnection();
            String sql = "SELECT * FROM [dbo].[OrderDetails] WHERE [product_id] = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, product_id);
            ResultSet rs = ps.executeQuery(); 
            OrderDetail orderdetail = new OrderDetail();

            while (rs.next()) { 

                orderdetail.setOrder_detail_id(rs.getInt("order_detail_id"));
//                oderdetail.setOrderId(productId); chưa có oder id nên cho null luon
                orderdetail.setProduct_id(rs.getInt("product_id"));
                orderdetail.setQuantity(rs.getInt("quantity"));
                orderdetail.setPrice(rs.getInt("price"));
                return orderdetail;
            }
            rs.close();
            ps.close();
            return orderdetail;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
