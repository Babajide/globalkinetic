/*
package co.za.randstack.gk.gk;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import co.za.randstack.model.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GkRestApplicationTests {
    
    
    @Autowired
    protected ObjectMapper mapper;
    @Autowired
    protected MockMvc mvc;
    
    
    
    @Test
    public void when_request_non_public_uri_return_302() throws Exception {
  
        ResultActions response =  httpPost("/api/abc", "", StringUtils.EMPTY);
        response.andExpect(MockMvcResultMatchers.status().is3xxRedirection());
        
        
    }
    @Test
    public void when_invalid_user_request_return_400() throws Exception {
        User user =  User.builder()
                .password(RandomStringUtils.randomAlphanumeric(10))
                .phoneNumber(RandomStringUtils.randomNumeric(10))
                .build();
        
        ResultActions response =  httpPost("/api/user/register", mapper.writeValueAsString(user), StringUtils.EMPTY);
        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
        
        
    }
       @Test
    public void when_valid_user_request_return_201() throws Exception {
        User user =  User.builder()
                .userName(RandomStringUtils.randomAlphabetic(10))
                .password(RandomStringUtils.randomAlphanumeric(10))
                .phoneNumber(RandomStringUtils.randomNumeric(10))
                .build();
        
        ResultActions response =  httpPost("/api/user/register", mapper.writeValueAsString(user), StringUtils.EMPTY);
        response.andExpect(MockMvcResultMatchers.status().isCreated());
        
        
    }     @Test
    public void when_valid_login_user_request_return_200() throws Exception {
        User user =  User.builder()
                .userName(RandomStringUtils.randomAlphabetic(10))
                .password(RandomStringUtils.randomAlphanumeric(10))
                .phoneNumber(RandomStringUtils.randomNumeric(10))
                .build();
        ResultActions response =  httpPost("/api/user/register", mapper.writeValueAsString(user), StringUtils.EMPTY);
        response.andExpect(MockMvcResultMatchers.status().isCreated());
        
        //login
        User login =  User.builder()
                .userName(user.getUserName())
                .password(user.getPassword())
                .build();
        //ResultActions loginResponse =  httpPost("/login", mapper.writeValueAsString(login), StringUtils.EMPTY);
        
        
        ResultActions loginResponse  = mvc.perform(post("/login")
                //.header("Authorization" , sessionID)
                .contentType("application/x-www-form-urlencoded")
                .content(mapper.writeValueAsString(login))
                .accept(MediaType.APPLICATION_JSON));
       
        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    private  ResultActions httpPost(String endPoint, String payload, String sessionID) throws Exception {
        return mvc.perform(post(endPoint)
                .header("Authorization" , sessionID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload)
                .accept(MediaType.APPLICATION_JSON));
    }
    
    
}
*/
