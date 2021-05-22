package Proyecto.Prueba.BCI.Controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;

import Proyecto.Prueba.BCI.Model.Phone;
import Proyecto.Prueba.BCI.Model.User;
import Proyecto.Prueba.BCI.Repository.UserRepository;
import Proyecto.Prueba.BCI.Security.GenerateJWT;
import Proyecto.Prueba.BCI.Service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class UserControllerTest {
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
    @Test
    void getAllUser_Ok() throws Exception {
    	
    	User user1 = new User();
    	user1.setId(1);
    	user1.setName("Jose");
    	user1.setEmail("Prueba@Prueba");
    	user1.setPassword("Prueba123");
    	user1.setToken("");
    	Date now = new Date();
    	user1.setCreatedUser(now);
    	user1.setLastLoginUser(now);
    	user1.setUpdatedUser(now);
    	
    	
    	Phone phone = new Phone();
    	phone.setId(1);
    	phone.setCityCode(1);
    	phone.setCountryCode(1);
    	phone.setNumber(1);
    	List<Phone> phones = Arrays.asList(phone);
    
   
    	user1.setPhone(phones);
    	
        Mockito.when(userService.getAllUser()).thenReturn(Arrays.asList(user1));
        
        String token = GenerateJWT.getJWTToken(user1.getEmail());
        user1.setToken(token); 
        
        mockMvc.perform(MockMvcRequestBuilders.get("/private/user/getall")
        		.header(HttpHeaders.AUTHORIZATION, user1.getToken())
        		.contentType(MediaType.APPLICATION_JSON))
        		.andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    void getAllUser_ForBidden() throws Exception {
    	
    	User user1 = new User();
    	user1.setId(1);
    	user1.setName("Jose");
    	user1.setEmail("Prueba@Prueba");
    	user1.setPassword("Prueba123");
    	user1.setToken("");
    	Date now = new Date();
    	user1.setCreatedUser(now);
    	user1.setLastLoginUser(now);
    	user1.setUpdatedUser(now);
    	
    	
    	Phone phone = new Phone();
    	phone.setId(1);
    	phone.setCityCode(1);
    	phone.setCountryCode(1);
    	phone.setNumber(1);
    	List<Phone> phones = Arrays.asList(phone);
    
   
    	user1.setPhone(phones);
    	
        Mockito.when(userService.getAllUser()).thenReturn(Arrays.asList(user1));
        
        
        mockMvc.perform(MockMvcRequestBuilders.get("/private/user/getall")
        		.header(HttpHeaders.AUTHORIZATION, user1.getToken())
        		.contentType(MediaType.APPLICATION_JSON))
        		.andExpect(MockMvcResultMatchers.status().isForbidden());
    }
    
    @Test
    void createUser_ok() throws Exception {

    	User user1 = new User();
    	user1.setId(1);
    	user1.setName("Jose");
    	user1.setEmail("Prueba@Prueba");
    	user1.setPassword("Prueba123");
    	user1.setToken("");
    	Date now = new Date();
    	user1.setCreatedUser(now);
    	user1.setLastLoginUser(now);
    	user1.setUpdatedUser(now);
    	
    	
    	Phone phone = new Phone();
    	phone.setId(1);
    	phone.setCityCode(1);
    	phone.setCountryCode(1);
    	phone.setNumber(1);
    	List<Phone> phones = Arrays.asList(phone);
    
    	user1.setPhone(phones);
    	
    	User user2 = new User();
    	user2.setId(2);
    	user2.setName("Jose");
    	user2.setEmail("Prueba@Prueba");
    	user2.setPassword("Prueba123");
    	user2.setToken("");
    	user2.setCreatedUser(now);
    	user2.setLastLoginUser(now);
    	user2.setUpdatedUser(now);
    	
    	
    	Phone phone2 = new Phone();
    	phone2.setId(2);
    	phone.setCityCode(1);
    	phone2.setCountryCode(1);
    	phone2.setNumber(1);
    	List<Phone> phones2 = Arrays.asList(phone2);
   
    	user2.setPhone(phones2);
    	
    	Mockito.when(userService.createUser(user1)).thenReturn(user2);

        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(user1);
        
        
        mockMvc.perform(MockMvcRequestBuilders.post("/public/user/create")
        		.content(requestJson)
        		.contentType(MediaType.APPLICATION_JSON))
        		.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void createUser_BadRequest() throws Exception {

    	User user1 = new User();
    	user1.setToken("");
    	Date now = new Date();
    	user1.setCreatedUser(now);
    	user1.setLastLoginUser(now);
    	user1.setUpdatedUser(now);
    	
    	
    	Phone phone = new Phone();
    	phone.setId(2);
    	phone.setCityCode(1);
    	phone.setCountryCode(1);
    	phone.setNumber(1);
    	List<Phone> phones = Arrays.asList(phone);
    
   
    	user1.setPhone(phones);

    	Mockito.when(userService.createUser(user1)).thenReturn(user1);

        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(user1);
        
        
        mockMvc.perform(MockMvcRequestBuilders.post("/public/user/create")
        		.content(requestJson)
        		.contentType(MediaType.APPLICATION_JSON))
        		.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    void updateUser_ok() throws Exception {

    	User user1 = new User();
    	user1.setId(2);
    	user1.setName("Jose");
    	user1.setEmail("Prueba@Prueba");
    	user1.setPassword("Prueba123");
    	user1.setToken("");
    	Date now = new Date();
    	user1.setCreatedUser(now);
    	user1.setLastLoginUser(now);
    	user1.setUpdatedUser(now);
    	
    	
    	Phone phone = new Phone();
    	phone.setId(2);
    	phone.setCityCode(1);
    	phone.setCountryCode(1);
    	phone.setNumber(1);
    	List<Phone> phones = Arrays.asList(phone);
    
   
    	user1.setPhone(phones);

    	Mockito.when(userService.UpdateUser(user1)).thenReturn(user1);

        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(user1);
        
        String token = GenerateJWT.getJWTToken(user1.getEmail());
        user1.setToken(token); 
        
        
        mockMvc.perform(MockMvcRequestBuilders.put("/private/user/update/2")
        		.header(HttpHeaders.AUTHORIZATION, user1.getToken())
        		.content(requestJson)
        		.contentType(MediaType.APPLICATION_JSON))
        		.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void updateUser_BadRequest() throws Exception {

    	User user1 = new User();
    	user1.setToken("");
    	Date now = new Date();
    	user1.setCreatedUser(now);
    	user1.setLastLoginUser(now);
    	user1.setUpdatedUser(now);
    	
    	
    	Phone phone = new Phone();
    	phone.setId(2);
    	phone.setCityCode(1);
    	phone.setCountryCode(1);
    	phone.setNumber(1);
    	List<Phone> phones = Arrays.asList(phone);
    
   
    	user1.setPhone(phones);

    	Mockito.when(userService.UpdateUser(user1)).thenReturn(user1);

        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(user1);
        
        String token = GenerateJWT.getJWTToken(user1.getEmail());
        user1.setToken(token); 
        
        
        mockMvc.perform(MockMvcRequestBuilders.put("/private/user/update/2")
        		.header(HttpHeaders.AUTHORIZATION, user1.getToken())
        		.content(requestJson)
        		.contentType(MediaType.APPLICATION_JSON))
        		.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    void login_ok() throws Exception {

    	String email = "Prueba@Prueba";
    	String pass = "Prueba123";
    	
    	User user1 = new User();
    	user1.setToken("");
    	Date now = new Date();
    	user1.setCreatedUser(now);
    	user1.setLastLoginUser(now);
    	user1.setUpdatedUser(now);
    	
    	
    	Phone phone = new Phone();
    	phone.setId(2);
    	phone.setCityCode(1);
    	phone.setCountryCode(1);
    	phone.setNumber(1);
    	List<Phone> phones = Arrays.asList(phone);
    
   
    	user1.setPhone(phones);

    	Mockito.when(userService.Login(email,pass)).thenReturn(user1);

        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(user1);
        
        
        mockMvc.perform(MockMvcRequestBuilders.post("/public/user/login")
        		.param("Email", email)
                .param("Password", pass)  
        		.contentType(MediaType.APPLICATION_JSON))
        		.andExpect(MockMvcResultMatchers.status().isOk());
    }
}
