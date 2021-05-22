package Proyecto.Prueba.BCI.Controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Proyecto.Prueba.BCI.Model.ErrorControl;
import Proyecto.Prueba.BCI.Model.User;
import Proyecto.Prueba.BCI.Security.GenerateJWT;
import Proyecto.Prueba.BCI.Service.UserService;
import Proyecto.Prueba.BCI.Validations.Validate;

@RestController
@Validated
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("private/user/getall")
	public ResponseEntity<List<User>> getAllUser(){
		return ResponseEntity.ok().body(userService.getAllUser());
	}

	@PostMapping("public/user/create")
	public ResponseEntity<?> createUser(@Valid @RequestBody User user){
		
		List<String> pass = Validate.ValidatePassword(user.getPassword());
		
		if(pass != null) {
			ErrorControl error = new ErrorControl(pass.iterator().next());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		}
		

		User userDb = userService.createUser(user);
		if(userDb == null) {
			ErrorControl error = new ErrorControl("Usuario ya se encuentra creado");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		}
		return ResponseEntity.ok().body(userDb);		
	}
	
	@PutMapping("private/user/update/{id}")
	public ResponseEntity<?> updateUser(@PathVariable long id,@Valid @RequestBody User user){
		
		List<String> pass = Validate.ValidatePassword(user.getPassword());
		
		if(pass != null) {
			ErrorControl error = new ErrorControl(pass.iterator().next());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		}
		user.setId(id);
			
		User userDb = userService.UpdateUser(user);
		if(userDb == null) {
			ErrorControl error = new ErrorControl("No se actualizo");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		}
		return ResponseEntity.ok().body(userDb);		
	}
	
	@PostMapping("public/user/login")
	public ResponseEntity<?> login(@RequestParam String Email, @RequestParam String Password){
		
			
		User userDb = userService.Login(Email, Password);
		if(userDb == null) {
			ErrorControl error = new ErrorControl("Password y/o Email incorrectos");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		}
		String token = GenerateJWT.getJWTToken(Email);
		userDb.setToken(token); 
		
		return ResponseEntity.ok().body(userDb);		
	}
}
