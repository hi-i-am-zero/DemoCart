/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Product;
import java.awt.color.ProfileDataException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class ProductDAO extends DBContext {

    public List<Product> findAll() {
        List<Product> listFound = new ArrayList<>();
        //- connect with DB
        Connection con;
        try {
            con = DBContext.makeConnection();
            //- chuẩn bị câu lệnh SQL
            String sql = "SELECT * FROM [dbo].[Products]";

            //- Tạo đối tượng PrepareStatement
            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            //- Set parameter ( optional )
            //- Thực thi câu lệnh
            ResultSet rs = ps.executeQuery();
            //- trả về kết quả
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString("product_name").trim();
                float price = rs.getFloat("price");
                int stock_quantity = rs.getInt("stock_quantity");
                String category = rs.getString("category");
                String created_at = rs.getString("created_at");
                Product product = new Product(product_id, product_name, price, stock_quantity, category, created_at);

                listFound.add(product);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }

        return listFound;
    }

//    public static void main(String[] args) {
//        for (Product product : new ProductDAO().findAll()) {
//            System.out.println(product.getProduct_id());
//        }
//    }
    public Product findById(int product_id) {
        //- connect with DB
        Connection con;
        try {
            con = DBContext.makeConnection();
            //- chuẩn bị câu lệnh SQL
            String sql = "SELECT * FROM [dbo].[Products] WHERE [product_id] = ? ";

            //- Tạo đối tượng PrepareStatement
            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            //- Set parameter ( optional )
            ps.setObject(1, product_id);
            //- Thực thi câu lệnh
            ResultSet rs = ps.executeQuery();
            //- trả về kết quả
            while (rs.next()) {
                String product_name = rs.getString("product_name").trim();
                float price = rs.getFloat("price");
                int stock_quantity = rs.getInt("stock_quantity");
                String category = rs.getString("category");
                String created_at = rs.getString("created_at");
                return new Product(product_id, product_name, price, stock_quantity, category, created_at);

            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }

        return null;
    }

//    public static void main(String[] args) {
//        ProductDAO p = new ProductDAO();
//        System.out.println(p.findById(1));
//    }
    public List<Product> findByName(String keyword) {
        List<Product> listFound = new ArrayList<>();
        //- connect with DB
        Connection con;
        try {
            con = DBContext.makeConnection();
            //- chuẩn bị câu lệnh SQL
            String sql = "SELECT * FROM [dbo].[Products] WHERE [product_name] LiKE ? ";

            //- Tạo đối tượng PrepareStatement
            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            //- Set parameter ( optional )
            ps.setString(1, "%" + keyword + "%");
            //- Thực thi câu lệnh
            ResultSet rs = ps.executeQuery();
            //- trả về kết quả
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString("product_name").trim();
                float price = rs.getFloat("price");
                int stock_quantity = rs.getInt("stock_quantity");
                String category = rs.getString("category");
                String created_at = rs.getString("created_at");
                Product product = new Product(product_id, product_name, price, stock_quantity, category, created_at);

                listFound.add(product);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
        return listFound;
    }

    public void insert(Product product) {
        //- connect with DB
        Connection con;
        try {
            con = DBContext.makeConnection();
            String sql = "INSERT INTO [dbo].[Products]\n"
                    + "           ([product_name]\n"
                    + "           ,[price]\n"
                    + "           ,[stock_quantity]\n"
                    + "           ,[category]\n"
                    + "           ,[created_at])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,GETDATE())";
            //- Tạo đối tượng PrepareStatement
            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            //set parameter
            ps.setObject(1, product.getProduct_name());
            ps.setObject(2, product.getPrice());
            ps.setObject(3, product.getStock_quantity());
            ps.setObject(4, product.getCategory());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(Product product) {
        //- connect with DB
        Connection con;
        try {
            con = DBContext.makeConnection();
            String sql = "UPDATE [dbo].[Products]\n"
                    + "   SET [product_name] = ?\n"
                    + "      ,[price] = ?\n"
                    + "      ,[stock_quantity] = ?\n"
                    + "      ,[category] = ?\n"
                    + "      ,[created_at] = GETDATE()\n"
                    + " WHERE [product_id] = ?";
            //- Tạo đối tượng PrepareStatement
            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            //set parameter
            ps.setObject(1, product.getProduct_name());
            ps.setObject(2, product.getPrice());
            ps.setObject(3, product.getStock_quantity());
            ps.setObject(4, product.getCategory());
            ps.setObject(5, product.getProduct_id());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
//    public static void main(String[] args) {
//        ProductDAO dao = new ProductDAO();
//         // Tạo một đối tượng Product mẫu để cập nhật
//    Product product = new Product();
//    product.setProduct_id(6); // Giả sử ID của sản phẩm là 1
//    product.setProduct_name("Sản phẩm mới");
//    product.setPrice((float) 50000.0); // Giá mới của sản phẩm
//    product.setStock_quantity(10); // Số lượng tồn kho
//    product.setCategory("Danh mục mới"); // Danh mục mới
//
//    // Gọi phương thức update để cập nhật sản phẩm vào DB
//    try {
//        dao.update(product);
//        System.out.println("Cập nhật sản phẩm thành công!");
//    } catch (Exception e) {
//        e.printStackTrace();
//        System.out.println("Cập nhật sản phẩm thất bại.");
//    }
//    }

    public void deleteById(Product product) {
        Connection con;
        //ket noi DB
        //tao cau lenh SQL
        try {
            con = DBContext.makeConnection();
            String sql = "DELETE FROM [dbo].[Products]\n"
                    + "      WHERE [product_id] = ?";
            //tao doi tuong prepared statement(them generated key vao tham so thu 2)
            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            //set parameter            
            ps.setObject(1, product.getProduct_id());
            //thuc thi cau lenh
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        // Tạo một đối tượng Product với ID cần xóa
//        Product product = new Product();
//        product.setProduct_id(32); // Giả sử ID của sản phẩm cần xóa là 1
//
//        // Tạo đối tượng của lớp chứa hàm deleteById (ví dụ lớp tên ProductDAO)
//        ProductDAO productDAO = new ProductDAO(); // Thay ProductDAO bằng tên lớp thực tế của bạn
//
//        // Gọi phương thức deleteById để xóa sản phẩm khỏi DB
//        try {
//            productDAO.deleteById(product);
//            System.out.println("Thao tác xóa đã được thực hiện.");
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Có lỗi xảy ra khi xóa sản phẩm.");
//        }
//    }
}
