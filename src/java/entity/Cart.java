/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class Cart {
     private int user_id;
    private double total;
    private List<CartItem> list;

    public Cart() {
        list = new ArrayList<>();
    }

    public Cart(int user_id, double total, List<CartItem> list) {
        this.user_id = user_id;
        this.total = total;
        this.list = list != null ? list : new ArrayList<>();
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<CartItem> getList() {
        return list;
    }

    public void setList(List<CartItem> list) {
        this.list = list;
    }

    public void addItem(CartItem item) {
        list.add(item);
    }

    public void removeCart(int cartID) {
        Iterator<CartItem> iterator = list.iterator();
        while (iterator.hasNext()) {
            CartItem cartItem = iterator.next();
            if (cartID == cartItem.getCartId()) {
                iterator.remove();
            }
        }
    }

    public void increaseProductQuantity(int product_id) {
        for (CartItem cartItem : list) {
            if (product_id == cartItem.getProduct_id()) {
                cartItem.setTotalItem(cartItem.getTotalItem() + 1);
            }
        }
    }

public void decreaseProductQuantity(int product_id) {
    Iterator<CartItem> iterator = list.iterator();
    while (iterator.hasNext()) {
        CartItem cartItem = iterator.next();
        if (product_id == cartItem.getProduct_id()) {
            if (cartItem.getTotalItem() > 0) {
                cartItem.setTotalItem(cartItem.getTotalItem() - 1);
            }
            if (cartItem.getTotalItem() <= 0) {
                iterator.remove(); // Xóa phần tử khỏi danh sách
            }
        }
    }
}
}
