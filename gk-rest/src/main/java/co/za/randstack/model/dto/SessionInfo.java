package co.za.randstack.model.dto;

import lombok.Value;

/*
Represents session info
 */
@Value
public class SessionInfo {
    private Long id;
    private String token;
    
    public SessionInfo(Long id, String token) {
        this.id = id;
        this.token = token;
    }
}
