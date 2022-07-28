package com.Master.SiteMasterBackEnd.Services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Master.SiteMasterBackEnd.Entities.Role;
import com.Master.SiteMasterBackEnd.Entities.User;
import com.Master.SiteMasterBackEnd.Repository.RoleRepository;
import com.Master.SiteMasterBackEnd.Repository.UserRepository;


@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository appUserRepository;
	@Autowired
	private RoleRepository appRoleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	        return bCryptPasswordEncoder;
	    }

	@Override
	public User saveUser(String firstname, String lastname, String email, String password, String role, boolean active,
			Date birthdDay, String etablissement, String grade, String tel, String specialite,
			String themeDeRecherche) {
		User appUser=this.appUserRepository.findByEmail(email);
		if(appUser!=null) throw new RuntimeException("User already exists !");
		//if(!password.equals(confirmedPassword)) throw new RuntimeException("Pleas confirm your password");
		User user=new User();
		user.setEmail(email);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setActivated(active);
		user.setPassword(bCryptPasswordEncoder.encode(password));
		user.setBirthdDay(birthdDay);
		user.setEtablissement(etablissement);
		user.setGrade(grade);
		user.setTel(tel);
		user.setSpecialite(specialite);
		user.setThemeDeRecherche(themeDeRecherche);
		appUserRepository.save(user);
		
		this.addRoleToUser(email, role);
		if(role.equals("ADMIN"))
			this.addRoleToUser(email, "USER");
		return user;
	}

	@Override
	public Role saveRole(Role role) {
		Role appRole=appRoleRepository.save(role);
		return appRole;
	}

	@Override
	public User loadUserByEmail(String email) {
		// TODO Auto-generated method stub
		User user=this.appUserRepository.findByEmail(email);
		return user;
	}

	@Override
	public void addRoleToUser(String email, String rolename) {
		Role appRole=appRoleRepository.findByRoleName(rolename);
		User user=this.appUserRepository.findByEmail(email);
		user.getRoles().add(appRole);
		
	}

}
