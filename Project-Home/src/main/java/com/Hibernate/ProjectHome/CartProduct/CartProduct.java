package com.Hibernate.ProjectHome.CartProduct;

import com.Hibernate.ProjectHome.Pojo.Product;

public class CartProduct extends Product {

    private int cartQuantity;
    private double cartSum;

    public int getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(int cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    public double getCartSum() {
        return cartSum;
    }

    public void setCartSum(double cartSum) {
        this.cartSum = cartSum;
    }
}
