package co.za.randstack.service.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import co.za.randstack.exception.UserAlreadyExistException;
import co.za.randstack.model.entity.User;
import co.za.randstack.repository.UserRepository;
import co.za.randstack.service.UserService;
import lombok.extern.slf4j.Slf4j;


/*
service layer to manage User
*/

@Slf4j
@Service
public class UserServiceImpl implements UserService {
   
   
   private SessionRegistry sessionRegistry;
   
   private UserRepository userRepository;
   @Autowired
   public UserServiceImpl(final UserRepository userRepository, final SessionRegistry sessionRegistry){
        this.userRepository = userRepository;
        this.sessionRegistry = sessionRegistry;
    }
    
    @Override
    public User register(User user) throws UserAlreadyExistException {
       log.debug("entered method register with user {}", user.getPhoneNumber());
       try{
            return userRepository.save(user);
    
        }catch (DataIntegrityViolationException ce){
           log.error("error occurred whilst persisting user {}", user.getPhoneNumber());
           throw new UserAlreadyExistException(ce.getMessage());
        }
       
    }
    
    @Override
    public List<User> authenticatedUsers() {
        log.debug("entered method authenticatedUsers() at {}", System.currentTimeMillis());
        List<User> list = new ArrayList<>();
        userRepository.findAll().forEach(user-> {
            if(user.isAuthenticated()){
                list.add(user);
            }
        });
        log.debug("Found total {} users", list.size());
        return list.stream().filter(User::isAuthenticated).collect(Collectors.toList());
    }
    

    
    //Lambda is better used here but I have used loops for brevity.
    @Override
    public Set<User> recentLogin() {
        Instant minutesAgo = Instant.now().minus(5, ChronoUnit.MINUTES);
        Set<User> recentLogins = new TreeSet<>();
        for (Object principal : sessionRegistry.getAllPrincipals()) {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) principal;
            for (SessionInformation si : sessionRegistry.getAllSessions(user, false)) {
                if (si.getLastRequest().compareTo(Date.from(minutesAgo)) >= 0 ) {
                    recentLogins.add(userRepository.findByUserName(user.getUsername()));
                
                }
            }
        }
        return recentLogins;
    }
    
    @Override
    @Transactional
    public User update(String userName) {
       User user = userRepository.findByUserName(userName);
       user.setAuthenticated(Boolean.TRUE);
       return user;
    }
    
    @Override
    public void logout(String sessionId) {
        log.debug("About to log {} out", sessionId);
        sessionRegistry.removeSessionInformation(sessionId);
    }
}
