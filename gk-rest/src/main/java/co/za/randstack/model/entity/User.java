package co.za.randstack.model.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;


@Entity
public class User extends AbstractModel implements Comparable {
    
    @NotNull(message = "phoneNumber cannot be null!")
    private String phoneNumber;
    @NotNull(message = "password cannot be null!")
    private String password;
    
    private boolean authenticated;
    
    public User(){}
    
    public boolean isAuthenticated() {
        return authenticated;
    }
    
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public User (String phoneNumber, String userName, String password){
        
        this.password = password;
        this.phoneNumber = phoneNumber;
        super.setUserName(userName);
    }
    
    public static UserBuilder builder() {
        return new UserBuilder();
    }
    
    @Override
    public int compareTo(Object o) {
        User u = (User) o;
        return Long.compare(getId(), u.getId());
    }
    
    public static class UserBuilder {
        
        private String phoneNumber;
        private String userName;
        private String password;
        
        UserBuilder() {
        }
        
        public UserBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
        
        public UserBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }
        
        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }
        
        public User build() {
            return new User(phoneNumber, userName, password);
        }
        
        public String toString() {
            return "User.UserBuilder(phoneNumber=" + this.phoneNumber + ", password=" + this.password + ")";
        }
    }
    
   
}
