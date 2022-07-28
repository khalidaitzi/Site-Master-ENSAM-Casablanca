package com.Master.SiteMasterBackEnd.Security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import com.Master.SiteMasterBackEnd.Services.UserService;

@Service
public class UserDetailsServcieImpl implements UserDetailsService{
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.Master.SiteMasterBackEnd.Entities.User appUser=userService.loadUserByEmail(username);
		if(appUser==null) throw new UsernameNotFoundException("cet utilisateur n'existe pas");
		//cette collection pour getRoles(authorities)
		Collection<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		//pour chaque role je vais ajouter se role a la collection authorities
		appUser.getRoles().forEach(r->{
			authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
		});
		System.err.println(appUser.getPassword());
		//pour comparer les informations
		return new User(appUser.getEmail(),appUser.getPassword(),authorities);
	}

}
