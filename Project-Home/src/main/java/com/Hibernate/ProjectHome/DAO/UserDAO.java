package com.Hibernate.ProjectHome.DAO;


import com.Hibernate.ProjectHome.Pojo.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDAO extends CrudRepository<User , Integer> {

    List<User> findByEmail(String email);
    List<User> findByUsername(String username);
    List<User> findById(int id);
//    User findByUsernameAndPassword(String username, String passowrd);
}
