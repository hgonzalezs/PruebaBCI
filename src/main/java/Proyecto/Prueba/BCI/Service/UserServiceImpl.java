package Proyecto.Prueba.BCI.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Proyecto.Prueba.BCI.Model.User;
import Proyecto.Prueba.BCI.Repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
    @PersistenceContext
    EntityManager entityManager;

	@Override
	public User createUser(User user) {
		
		List<User> result = (List<User>) entityManager.createQuery("FROM User WHERE email = '"+user.getEmail()+"'").getResultList();
		
        if(result.isEmpty()) {
        	return userRepository.save(user);
        } else {
        	return null;
        }

	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User UpdateUser(User user) {
		Optional<User> userDB = this.userRepository.findById(user.getId());
		if(userDB.isPresent()) {
			User userUpdate = userDB.get();
			userUpdate.setName(user.getName());
			userUpdate.setEmail(user.getEmail());
			userUpdate.setPassword(user.getPassword());
			Date updateDate = new Date();
			userUpdate.setUpdatedUser(updateDate);
			userRepository.save(userUpdate);
			return userUpdate;
		}else {
			return null;
		}
	}

	@Override
	public User Login(String Email, String Password) {
		List<User> result = (List<User>) entityManager.createQuery("FROM User WHERE email = '"+Email+"' and password = '" + Password +"'").getResultList();
		
       
		if(!result.isEmpty()) {
        	
        	Optional<User> userDB = this.userRepository.findById(result.iterator().next().getId());
        	
    		if(userDB.isPresent()) {
    			
    			User userUpdate = userDB.get();
    			Date updateDate = new Date();
    			userUpdate.setLastLoginUser(updateDate);
    			userRepository.save(userUpdate);

            	return userUpdate;
    		}else {
    			return null;
    		}
        	
        } else {
        	return null;
        }

	}
}
