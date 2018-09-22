package co.za.randstack.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;


@MappedSuperclass
public abstract class AbstractModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "userName cannot be null!")
    @Column(unique=true)
    private String userName;
    private Date createDateTime;
    
    
    @PrePersist
    public void audit(){
        if(getId() == null){
            setCreateDateTime(new Date());
        }
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public Date getCreateDateTime() {
        return createDateTime;
    }
    
    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }
}