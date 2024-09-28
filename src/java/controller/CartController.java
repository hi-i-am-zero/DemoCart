/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.OrderDetailDAO;
import dal.ProductDAO;
import entity.Cart;
import entity.CartItem;
import entity.Product;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.PageContext;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class CartController extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CartItemsListController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartItemsListController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int product_id = Integer.parseInt(request.getParameter("id"));
        // Lấy session hiện tại
        HttpSession session = request.getSession();
        // Kiểm tra xem CartDTO đã tồn tại trong session chưa
        Cart cart = (Cart) session.getAttribute("Cart");
        // Nếu chưa có CartDTO trong session, tạo mới CartDTO
        if (cart == null) {
            cart = new Cart();
        }
        ProductDAO productDAO = new ProductDAO();
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        double price = productDAO.findById(product_id).getPrice();
        String name = productDAO.findById(product_id).getProduct_name();
        int quantity = orderDetailDAO.getOderDetailByProductID(product_id).getQuantity();

        CartItem cartitem = new CartItem(1, product_id, name, price, quantity);
        cart.addItem(cartitem);
        session.setAttribute("Cart", cart);
        RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
        dispatcher.forward(request, response);
    }
    
}
