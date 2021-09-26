package com.Hibernate.ProjectHome.DAO;

import com.Hibernate.ProjectHome.Pojo.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends CrudRepository<Product , Integer> {


}
