package com.Hibernate.ProjectHome.Controller;

import com.Hibernate.ProjectHome.CartProduct.CartProduct;
import com.Hibernate.ProjectHome.DAO.ProductDAO;
import com.Hibernate.ProjectHome.DAO.UserDAO;
import com.Hibernate.ProjectHome.Exception.UserException;
import com.Hibernate.ProjectHome.Pojo.Product;
import com.Hibernate.ProjectHome.Security.UserSession;
import com.Hibernate.ProjectHome.Service.ProductService;
import com.Hibernate.ProjectHome.Service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class UserControlle {

    @Autowired
    UserDAO userDAO;

    @Autowired
    UserService userService;

    @Autowired
    UserSession userSession;

    @Autowired
    ProductDAO productDAO;

    @Autowired
    ProductService productService;


    List<Product> productList;

    @GetMapping("/register")
    public ModelAndView register(){
        return new ModelAndView("register-form");
    }

    @GetMapping("/register-form")
   public ModelAndView registerAction(@RequestParam("email") String email ,
                                      @RequestParam("password") String password ,
                                      @RequestParam("password-again") String password2 ,
                                      @RequestParam("firstname") String firstname ,
                                      @RequestParam("lastname") String lastname ,
                                      @RequestParam("address") String address ,
                                      @RequestParam("username") String username){
    ModelAndView modelAndView = new ModelAndView("register-form");

    if (!userService.checkEmail(email)){
        modelAndView.addObject("message", "Email existent");
        return modelAndView;
    }
    if (!userService.register(email, password, password2, firstname, lastname, address,username)){
        modelAndView.addObject("message" , "Te rugam sa completezi campurile!");
        return modelAndView;
    }

        try {
            userService.checkPassword(password , password2);
        }catch (UserException e){
            modelAndView.addObject("message",e.getMessage());
        }

         return new ModelAndView("login-page");
    }
    //1.AICI MI-AM FACUT O EXCEPTIE PE "REGISTER" PE CARE NU O AFISEAZA.

    @GetMapping("/login")
    public ModelAndView login(){
            return new ModelAndView("login-page");
    }

    @GetMapping("/login-form")
    public ModelAndView loginAction(@RequestParam("email") String email , @RequestParam("password") String password){

        ModelAndView modelAndView = new ModelAndView("login-page");

        if (email.isEmpty() || password.isEmpty()){
            modelAndView.addObject("message" ,"Te rugam sa completezi campurile!");
            return modelAndView;
        }

        if (!userService.login(email, password)){
            modelAndView.addObject("message", "email neinregistrat");
            return modelAndView;
        }else {
            return new ModelAndView("dashboard");
        }


    }

    @GetMapping("/dashboard")
    public ModelAndView dashboard(){
        ModelAndView modelAndView = new ModelAndView("dashboard");
        int itemsCart = 0;
        int id = userSession.getId();
        if (id == 0){
            return new ModelAndView("redirect:/login");
        }
        for (int quantity : userSession.getShhopingCart().values()){
            itemsCart += quantity;
        }

        List<Product> productList = productService.findAll();
        modelAndView.addObject("productList" ,productList);
        return modelAndView;
    }
    // 2.DUPA CE MA LOGEZ AM UN "RETURN" CATRE DASHBOARD UNDE TREBUIA SA IMI ARATE PRODUSELE DOAR CA NU LE ARATA.


    @GetMapping("/addToCart")
    public ModelAndView addToCart(@RequestParam("productId") Integer id){
        ModelAndView modelAndView = new ModelAndView("dashboard");
        int itemsCart = 0;
        userSession.addToCart(id);
        for (int quantity : userSession.getShhopingCart().values()){
            itemsCart += quantity;
        }
        modelAndView.addObject("productList" ,productList);
        modelAndView.addObject("items" , itemsCart);
        return modelAndView;
    }
    //3.IN BD AM 2 TABELE "CART" IN CARE AM "ID" SI "QUANTITY" SI NU SE SALVEAZA NIMIC IN TABELA.
    // SI CEA DE A DOUA TABELA CARE SE NUMESTE "CART_PRODUCTS" IN CARE AM "CART_ID" SI "PRODUCTS_ID" LA FEL NU SE SALVEAZA NIMIC.
    //USER-UL PRODUSELE SI CATEGORIA SE SALVEAZA IN BD
    //SI TE ROG SA IMI SPUI DACA MAI TREBUIE ADAUGAT CEVA SAU MODIFICAT , MULTUMESC.

    @GetMapping("/logout")
    public ModelAndView logout(){
        userSession.setId(0);
        return new ModelAndView("index.html");
    }

    @GetMapping("/show-cart")
    public ModelAndView showCart(){
        ModelAndView modelAndView = new ModelAndView("cart");
        List<CartProduct> cartProducts = new ArrayList<>();
        double totalCartSum = 0;
        for (Map.Entry<Integer , Integer> entry:userSession.getShhopingCart().entrySet()){
            int productId = entry.getKey();
            int quantity = entry.getValue();
            Product product = productService.findById(productId);
            CartProduct cartProduct = new CartProduct();
            cartProduct.setCartQuantity(quantity);
            cartProduct.setId(productId);
            cartProduct.setName(product.getName());
            cartProduct.setCartSum(quantity*product.getPrice());

            totalCartSum += cartProduct.getCartSum();
            cartProducts.add(cartProduct);
        }
        modelAndView.addObject("cartList" ,cartProducts);
        modelAndView.addObject("totalSum",totalCartSum);
        return modelAndView;
    }


}
