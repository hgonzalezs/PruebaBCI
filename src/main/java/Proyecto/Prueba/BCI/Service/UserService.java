package Proyecto.Prueba.BCI.Service;

import java.util.List;

import Proyecto.Prueba.BCI.Model.User;

public interface UserService {
	User createUser(User user);
	List<User> getAllUser();
	User UpdateUser(User user);
	User Login(String Email, String Password);
}
