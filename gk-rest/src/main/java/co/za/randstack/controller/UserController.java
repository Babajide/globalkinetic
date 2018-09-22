package co.za.randstack.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.za.randstack.controller.mapper.UserMapper;
import co.za.randstack.exception.UserAlreadyExistException;
import co.za.randstack.model.dto.UserDto;
import co.za.randstack.model.entity.User;
import co.za.randstack.service.UserService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {
    
    private UserService userService;
    
    @Autowired
    public UserController(final UserService userService){
        this.userService = userService;
    }
    
    @GetMapping("/logout/{sessionId}")
    public ResponseEntity logout(@PathVariable("sessionId") String sessionId) throws UserAlreadyExistException {
        log.debug("entered logout for ID {}", sessionId);
        userService.logout(sessionId);
       return new ResponseEntity(HttpStatus.OK);
    }
    
    
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public UserDto add(@RequestBody @Valid UserDto userDto) throws UserAlreadyExistException {
        log.debug("entered method add()");
        User user =  userService.register(UserMapper.toDomainObject(userDto));
       log.info("Added user with username: {}", user.getPhoneNumber());
       return UserMapper.toDto(user);
    }
    
    
    @GetMapping("/users")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Collection<UserDto> users(HttpServletRequest request){
        String ss = request.getSession().getId();
        log.debug("entered method users()");
        return UserMapper.toDtos(userService.authenticatedUsers());
    }
    
    
    @GetMapping("/recent")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Collection<UserDto> recent(){
        log.debug("entered method recent()");
        return UserMapper.toDtos(userService.recentLogin());
    }
    
}
