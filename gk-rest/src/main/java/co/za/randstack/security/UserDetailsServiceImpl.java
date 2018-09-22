package co.za.randstack.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.za.randstack.model.entity.User;
import co.za.randstack.repository.UserRepository;

import static java.util.Collections.emptyList;


/**
 * Used to authenticate user but comparing credentials passed vs saved.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private UserRepository userRepository;
    @Autowired
    public UserDetailsServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public UserDetailsServiceImpl() {
    
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new  org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), emptyList());
    }
}
