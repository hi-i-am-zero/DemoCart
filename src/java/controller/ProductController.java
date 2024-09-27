/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ProductDAO;
import entity.Account;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ProductController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO dao = new ProductDAO();
        HttpSession session = request.getSession();
        List<Product> listProduct = (List<Product>) session.getAttribute("listProduct");
        //kiem tra xem co list product o trong session hay ko 
        //TH1: ko he co list product torng session
        if (listProduct == null) {
            //get du lieu san pham len 
            listProduct = dao.findAll();
        }
        //set du lieu vao trong request
        session.setAttribute("listProduct", listProduct);
        //chuyen sang trang list.jsp
        Account a = (Account) session.getAttribute("account");
        if ("admin".equals(a.getRole().trim())) {
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("list.jsp").forward(request, response);

        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") == null
                ? "" : request.getParameter("action");
        List<Product> listProduct;
        switch (action) {
            case "search":
                listProduct = searchProduct(request, response);
                break;
            case "insert":
                listProduct = insert(request, response);
                break;
            case "update":
                listProduct = update(request, response);
                break;
            case "delete":
                listProduct = delete(request, response);
                break;
            default:
                throw new AssertionError();
        }
        request.getSession().setAttribute("listProduct", listProduct);
        response.sendRedirect("product");
    }

    private List<Product> searchProduct(HttpServletRequest request, HttpServletResponse response) {
        //get ve keyword nguoi dung nhap
        String keyword = request.getParameter("keyword");
        //dua vao keyword do, tim trong DB co product nao chua tu khoa ma
        //nguoi dung nhap ok => List<Product>
        ProductDAO dao = new ProductDAO();
        List<Product> listProduct = dao.findByName(keyword);
        // tra ve cai list
        return listProduct;
    }

    private List<Product> insert(HttpServletRequest request, HttpServletResponse response) {
        ProductDAO dao = new ProductDAO();
        //get ve data
        String name = request.getParameter("product_name");
        float price = Float.parseFloat(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("stock_quantity"));
        String category = request.getParameter("category");

        Product product = new Product();
        product.setProduct_name(name);
        product.setPrice(price);
        product.setStock_quantity(quantity);
        product.setCategory(category);
        //insert vao trong DB
        dao.insert(product);
        //get ve toan bo data moi
        return dao.findAll();
    }

    private List<Product> update(HttpServletRequest request, HttpServletResponse response) {
        ProductDAO dao = new ProductDAO();
        //get ve data
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("product_name");
        float price = Float.parseFloat(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("stock_quantity"));
        String category = request.getParameter("category");

        Product product = new Product();
        product.setProduct_id(id);
        product.setProduct_name(name);
        product.setPrice(price);
        product.setStock_quantity(quantity);
        product.setCategory(category);

        //update vao DB
        dao.update(product);
        //get ve toan bo data moi
        return dao.findAll();
    }

    private List<Product> delete(HttpServletRequest request, HttpServletResponse response) {
        ProductDAO dao = new ProductDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = new Product();
        product.setProduct_id(id);
        dao.deleteById(product);
        return dao.findAll();
    }
}
