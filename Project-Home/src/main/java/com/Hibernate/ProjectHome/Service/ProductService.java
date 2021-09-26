package com.Hibernate.ProjectHome.Service;

import com.Hibernate.ProjectHome.DAO.ProductDAO;
import com.Hibernate.ProjectHome.Pojo.Category;
import com.Hibernate.ProjectHome.Pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductDAO productDAO;

    public String saveProduct(String name ,double price , String category , int quantity){
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        Category productCategory = new Category();
        productCategory.setName(category);
        product.setCategory(productCategory);
        productDAO.save(product);
        return "produsul" + product.getName() + "a fost salvat";
    }

    public String deleteProductById(int id){
        productDAO.deleteById(id);
        return "Produsul a fost sters";
    }

   public List<Product> findAll(){
       List<Product> productList = (List<Product>) productDAO.findAll();
       return productList;
   }

   public Product findById(int id){
        return productDAO.findById(id).get();
   }
}
