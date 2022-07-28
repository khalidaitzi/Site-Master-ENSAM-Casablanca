package com.Master.SiteMasterBackEnd.Controllers;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Master.SiteMasterBackEnd.Entities.User;
import com.Master.SiteMasterBackEnd.Repository.UserRepository;
import com.Master.SiteMasterBackEnd.Services.UserService;

import lombok.Data;




@RestController
public class UserController {
	@Autowired
	UserService userService;
	public User register(@RequestBody UserForm userForm)
	{
		
		System.err.println(userForm);
		return userService.saveUser(
				userForm.getFirstname(),
				userForm.getLastname(),
				userForm.getEmail(),
				userForm.getPassword(),
				userForm.getRole(),
				userForm.getActive(),
				userForm.getBirthdDay(),
				userForm.getEtablissement(),
				userForm.getGrade(),
				userForm.getTel(),
				userForm.getSpecialite(),
				userForm.getThemeDeRecherche()
				);
	}

	@Autowired
	private UserRepository repo;
	
	
	}

@Data
class UserForm{
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String role;
	private Boolean active;
	private Date birthdDay;
	private String etablissement;
	private String grade;
	private String tel;
	private String specialite;
	private String themeDeRecherche;
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Date getBirthdDay() {
		return birthdDay;
	}
	public void setBirthdDay(Date birthdDay) {
		this.birthdDay = birthdDay;
	}
	public String getEtablissement() {
		return etablissement;
	}
	public void setEtablissement(String etablissement) {
		this.etablissement = etablissement;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getSpecialite() {
		return specialite;
	}
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}
	public String getThemeDeRecherche() {
		return themeDeRecherche;
	}
	public void setThemeDeRecherche(String themeDeRecherche) {
		this.themeDeRecherche = themeDeRecherche;
	}
	
}

