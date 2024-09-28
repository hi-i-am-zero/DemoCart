/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Timestamp;

/**
 *
 * @author ASUS
 */
public class Product {
    private int product_id;
    private String product_name;
    private double price;
    private int stock_quantity;
    private String category;
    private String created_at;

    public Product() {
    }

    public Product(int product_id, String product_name, double price, int stock_quantity, String category, String created_at) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.price = price;
        this.stock_quantity = stock_quantity;
        this.category = category;
        this.created_at = created_at;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Product{" + "product_id=" + product_id + ", product_name=" + product_name + ", price=" + price + ", stock_quantity=" + stock_quantity + ", category=" + category + ", created_at=" + created_at + '}';
    }
    
}
