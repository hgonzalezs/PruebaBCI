package Proyecto.Prueba.BCI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Proyecto.Prueba.BCI.Model.User;

public interface UserRepository extends JpaRepository<User, Long >{

}
