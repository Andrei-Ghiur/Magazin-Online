package com.Hibernate.ProjectHome.Service;

import com.Hibernate.ProjectHome.DAO.CartDAO;
import com.Hibernate.ProjectHome.DAO.UserDAO;
import com.Hibernate.ProjectHome.Pojo.Product;
import com.Hibernate.ProjectHome.Pojo.User;
import com.Hibernate.ProjectHome.Security.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    UserSession userSession;

    @Autowired
    CartDAO cartDAO;

    @Autowired
    UserDAO userDAO;

    public void addToCart(Product product){
        List<User> userList = userDAO.findById(userSession.getId());

        List<Product> products = new ArrayList<>();

    }


}
