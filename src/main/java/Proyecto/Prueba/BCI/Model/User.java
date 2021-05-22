package Proyecto.Prueba.BCI.Model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.*;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "user")

public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	
	@NotNull(message = "Debe ingresar un nombre")
	@Column(name = "Name")
	private String Name;
	
	@Column(name = "Email")
	@NotNull(message = "Debe ingresar un email")
	@Email
	private String Email;
	
	@NotNull(message = "Debe ingresar una password")
	@Column(name = "Password")
	private String Password;
	
	@CreationTimestamp
	private Date CreatedUser;
	
	@CreationTimestamp
	private Date UpdatedUser;
	
	@CreationTimestamp
	private Date LastLoginUser;

	@NotNull(message = "Debe ingresar los datos de telefono")
	@OneToMany(targetEntity = Phone.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "Id")
	private List<Phone> phone;

	private Boolean IsActive = true;
	
	private String Token = "";

	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}

	public long getId() {
		return Id;
	}

	public Boolean getIsActive() {
		return IsActive;
	}

	public void setIsActive(Boolean isActive) {
		IsActive = isActive;
	}
	
	
	public void setId(long id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public Date getCreatedUser() {
		return CreatedUser;
	}

	public void setCreatedUser(Date createdUser) {
		CreatedUser = createdUser;
	}

	public Date getUpdatedUser() {
		return UpdatedUser;
	}

	public void setUpdatedUser(Date updatedUser) {
		UpdatedUser = updatedUser;
	}

	public Date getLastLoginUser() {
		return LastLoginUser;
	}

	public void setLastLoginUser(Date lastLoginUser) {
		LastLoginUser = lastLoginUser;
	}

	public List<Phone> getPhone() {
		return phone;
	}

	public void setPhone(List<Phone> phone) {
		this.phone = phone;
	}

}
