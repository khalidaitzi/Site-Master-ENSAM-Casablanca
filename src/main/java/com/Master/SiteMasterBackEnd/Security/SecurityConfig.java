package com.Master.SiteMasterBackEnd.Security;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


//pour redifinir la configuration de spring security
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	//@bean c'est a dir vous avez lobjet de type authentificationmanager et vous pouvez l'injecter là oû vous veulez
	@Bean 
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	return super.authenticationManagerBean();
	}
	@Autowired
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//pour preciser comment spring security va chercher les utlisateurs et les rôles
		System.err.println("======================================");
		//quand luser va s'authetinfier utiliser cette methode userdetailService() pour chercher luser apartir de la couche service que j'ai deja créer userService
		//loaduserbyusername dans la classe userDetailsServiceImpl
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
			//csrf utilise les session et gerer au coté serveur mais nous cherchons de la gerer coté client frontend pour utliser jwt
			//donc on desactive csrf pour utiliser le type stateless
			http.csrf().disable();// pour desactiver csrf
			//http.headers().frameOptions().disable(); pour permet l'accès aux diffenrentes frames html
			//session managment stateless
			http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			http.authorizeRequests().antMatchers("/login/**","/register/**","/annonces").permitAll();
			http.authorizeRequests().antMatchers("/appUsers/**","/appRoles/**").hasAuthority("ADMIN");
			http.authorizeRequests().anyRequest().authenticated();//la neccecite d'une authentification(anyrequest)
			http.addFilter(new JWTFilter(authenticationManager()));//pour ajouter a=un filter qui attends un objet authentificationmanager 
			//http.formLogin() qui est par par defaut mais on va le faire dans le coté front end
			//pour ajouter le filtre d'authorisation 
			http.addFilterBefore(new JWTFilterBefor(), UsernamePasswordAuthenticationFilter.class);
	}
	

}
