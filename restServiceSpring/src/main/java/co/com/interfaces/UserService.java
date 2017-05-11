package co.com.interfaces;

import java.util.List;

import co.com.dto.User;


public interface UserService {
    User findById(Long id);
    
    List<User> findAll();
 
    User save(User user);
 
    User update(User user);
 
    void delete(Long id);
}

