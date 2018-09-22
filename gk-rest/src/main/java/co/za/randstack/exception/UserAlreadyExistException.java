package co.za.randstack.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
Used on indicate conflict in state
 */
@ResponseStatus(value = HttpStatus.CONFLICT, reason = "User already exist.")
public class UserAlreadyExistException extends Exception {
    
    public UserAlreadyExistException(String message) {
        super(message);
    }
}

