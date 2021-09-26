package com.Hibernate.ProjectHome.Controller;

import com.Hibernate.ProjectHome.Pojo.Product;
import com.Hibernate.ProjectHome.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    ProductService productService;

    @GetMapping("afisare-admin")
    public ModelAndView afisareAdmin(){
        ModelAndView modelAndView = new ModelAndView("products");
        List<Product> productList = productService.findAll();
        modelAndView.addObject("products", productList);
        return modelAndView;
    }

    @GetMapping("/admin/products")
    public String admin(@RequestParam("nume") String nume,
                              @RequestParam("pret") double pret,
                              @RequestParam("category") String category ,
                                @RequestParam("quantity") int quantity){
        productService.saveProduct(nume, pret, category ,quantity);


        return "redirect:/afisare-admin";

    }

    @DeleteMapping("/admin/products/{id}")
        public ModelAndView deleteProduct(@PathVariable("id")int id){
            ModelAndView modelAndView = new ModelAndView("afisare-admin");
            productService.deleteProductById(id);

            return modelAndView;

    }


}
