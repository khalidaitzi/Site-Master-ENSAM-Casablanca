package com.Master.SiteMasterBackEnd.Services;

import java.util.Date;

import com.Master.SiteMasterBackEnd.Entities.Role;
import com.Master.SiteMasterBackEnd.Entities.User;

public interface UserService {
	public User saveUser(String firstname,
			String lastname,
			String email,
			String password,
			String role,
			boolean active,
			Date birthdDay,
			String etablissement,
			String grade,
			String tel,
			String specialite,
			String themeDeRecherche);
	public Role saveRole(Role role);
	//en va l'utuliser dans UserDetailsServiceImpl 
	public User loadUserByEmail(String email);
	public void addRoleToUser(String email,String rolename);

}
