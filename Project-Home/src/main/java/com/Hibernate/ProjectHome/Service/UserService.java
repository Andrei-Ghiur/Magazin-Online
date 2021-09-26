package com.Hibernate.ProjectHome.Service;

import com.Hibernate.ProjectHome.DAO.CartDAO;
import com.Hibernate.ProjectHome.DAO.UserDAO;
import com.Hibernate.ProjectHome.Exception.UserException;
import com.Hibernate.ProjectHome.Pojo.User;
import com.Hibernate.ProjectHome.Security.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    UserSession userSession;

    @Autowired
    CartDAO cartDAO;

    public List<User> findByEmail(String email){
        return userDAO.findByEmail(email);
    }

    public boolean checkEmail(String email){
        List<User> emailList = userDAO.findByEmail(email);
        if (emailList.size() > 0){
            return false;
        }
        return true;
    }

    public boolean register(String email , String password , String password2 , String firstname , String lastname , String address , String username){
        if (email.isEmpty() || password.isEmpty() || password2.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || address.isEmpty() || username.isEmpty()){
            return  false;
        }else if (!password.equals(password2)){
            return false;
        }else {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setPassword2(password2);
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setAddress(address);
            user.setUsername(username);
            userDAO.save(user);
            return true;
        }

    }

    public void checkPassword(String password , String password2) throws UserException {
        if (!password.equals(password2) ){
            throw new UserException("Parolele nu corespund!");
        }
    }

    public boolean login(String email , String password){
        List<User> userList = userDAO.findByEmail(email);
        if (userList.size() == 0 || userList.size() > 1){
            return false;
        }
        if (userList.size() == 1){
            User user = userList.get(0);
            if (!user.getPassword().equals(password)){
                return false;
            }else {
                userSession.setId(user.getId());
                return true;
            }
        }
        return false;
    }


}
