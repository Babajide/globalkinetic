package co.za.randstack.service;

import java.util.List;
import java.util.Set;

import co.za.randstack.exception.UserAlreadyExistException;
import co.za.randstack.model.entity.User;


public interface UserService {
    
    User register(User user) throws UserAlreadyExistException;
    
    List<User> authenticatedUsers();
    
    Set<User> recentLogin();
    
    User update(String userName);
    
    void logout(String sessionId);
}
