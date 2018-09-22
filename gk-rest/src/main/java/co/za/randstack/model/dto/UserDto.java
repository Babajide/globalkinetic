package co.za.randstack.model.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

/*
User DTO with bean validation
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    
    
    @NotNull(message = "phoneNumber cannot be null!")
    private String phoneNumber;
    @NotNull(message = "password cannot be null!")
    private String password;
    @NotNull(message = "userName cannot be null!")
    private String userName;
    
    
    public UserDto(){}
    
    public UserDto( String userName, String phoneNumber) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
