package com.Hibernate.ProjectHome.Security;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;

@SessionScope
@Component
public class UserSession {

    private int id;
    private HashMap<Integer , Integer> shhopingCart = new HashMap<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<Integer, Integer> getShhopingCart() {
        return shhopingCart;
    }

    public void setShhopingCart(HashMap<Integer, Integer> shhopingCart) {
        this.shhopingCart = shhopingCart;
    }

    public void addToCart(int id){
        if (shhopingCart.get(id) != null){
            int currentQuantity = shhopingCart.get(id);
            int newQuantity = currentQuantity + 1;
            shhopingCart.put(id , newQuantity);
        }else {
            shhopingCart.put(id , 1);
        }
    }
}
