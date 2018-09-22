package co.za.randstack.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import co.za.randstack.model.dto.SessionInfo;
import co.za.randstack.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/*
This class is invoked once spring security authenticates a user.
Here we return the user ID and session token so subsequent request must carry this session ID in other to access protected resources.
 */

@Slf4j
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
   
    private UserService userService;
   private ObjectMapper mapper;
   
   @Autowired
   public CustomAuthenticationSuccessHandler(@Lazy final UserService userService, final ObjectMapper mapper ){
       this.userService = userService;
       this.mapper = mapper;
   }
   
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)  {
       User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        log.info("Setting user: {} to authenticated", username);
        co.za.randstack.model.entity.User authenticated = userService.update(username);
        
        
        //close
        try {
            response.getWriter().write(mapper.writeValueAsString(new SessionInfo(authenticated.getId(), request.getSession().getId())));
        } catch (IOException e) {
            log.error("error constructing response", e);
        }
    
    }
    
}