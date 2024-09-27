/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ProductDAO;
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
        HttpSession session = request.getSession();
        List<Product> products = (List<Product>) session.getAttribute("cart");
        
        request.setAttribute("products", products);
        
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession();
        List<Product> products = (List<Product>) session.getAttribute("cart");
        
        String action = request.getParameter("action");
        int id = Integer.parseInt(request.getParameter("id"));
        
        if (products == null) {
            products = new ArrayList<>();
        } else {
            
            switch (action) {
                case "add" -> {
                    ProductDAO p = new ProductDAO();
                    Product product;
                    boolean isExist = products.stream().anyMatch(pro -> pro.getProduct_id() == id);
                    
                    if (isExist) {
                        product = products.stream().filter(prod -> prod.getProduct_id() == id)
                                .findFirst().orElse(null);
                        
                        product.setStock_quantity(
                                product.getStock_quantity() + 1
                        );
                    } else {
                        product = p.findById(id);
                        product.setStock_quantity(1);
                        products.add(product);
                    }
                    out.print(product.getStock_quantity());
                }
                
                case "remove" -> {
                    products.removeIf(p -> p.getProduct_id() == id);
                }
            }
        }
        session.setAttribute("cart", products);
        processRequest(request, response);
    }
    
}
