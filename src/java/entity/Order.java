/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author ASUS
 */
public class Order {
    private int oder_id;
    private int user_id;
    //con cai order date ko biet lay kieu DL gi
    private double total_amount;
    private String shipping_address;
    private String status;

    public Order() {
    }

    public Order(int oder_id, int user_id, double total_amount, String shipping_address, String status) {
        this.oder_id = oder_id;
        this.user_id = user_id;
        this.total_amount = total_amount;
        this.shipping_address = shipping_address;
        this.status = status;
    }

    public int getOder_id() {
        return oder_id;
    }

    public void setOder_id(int oder_id) {
        this.oder_id = oder_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
