package com.Master.SiteMasterBackEnd.Security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Master.SiteMasterBackEnd.Services.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

// C'EST LE ACCESS TOKEN
public class JWTFilter extends UsernamePasswordAuthenticationFilter {

	//@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	public JWTFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}
	
	// cette methode et apeller quand un utilisateur et entrain de s'authentifier
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			com.Master.SiteMasterBackEnd.Entities.User user = null;
			user = new ObjectMapper().readValue(request.getInputStream(), com.Master.SiteMasterBackEnd.Entities.User.class);
			//retourner a spring security un objet authentificationmanager quii contient les informations de l'utilisateur
			//recuperer email et pass avec request.getInputStream et le stockés dans l'objet UsernamePasswordAuthenticationToken
			
			return authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Probleme in request content");
		}

	}
	
	//lorsque l'authetification est faite avec succè on va appler cette methode
	//spring security quand il apple cette methode il vous transmet un paramètre authResult de type authentication 
	//dans lequel vous avez le résultat de l'authentification
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		//principal permet de retourner l'utulisateur qui est authentifier
		User user = (User) authResult.getPrincipal();//(User) car getPrincipal() elle retourn un objet de type object
		List<String> roles = new ArrayList<String>();
		//recuperer les rôles
		user.getAuthorities().forEach(au -> {
			roles.add(au.getAuthority());
		});
		//maintenant en va génerer le token
		//c'est jwt access token
		//username dans subject (payload)
		//withIssuer c'est le nom de lapplication qui générer le token
		//witharrayclaim pour enregisterer les roles de lur=tilisateur, en doit préciser son nom qui est dans ce cas "roles"
		String jwt = JWT.create().withIssuer(request.getRequestURI()).withSubject(user.getUsername())
				.withArrayClaim("roles", roles.toArray(new String[roles.size()]))//.withExpiresAt(new Date(System.currentTimeMillis+5*60*1000)) pour preciser la date d'expiration qui est 5minutes
 				.sign(Algorithm.HMAC256("secret1"));//pour choisir l'algorithm de filtrage qui est dans ce cas HMAC256 qu'il demande une clé privé bach ysawb la signature li hya secret1
		//et je peut envouyez ca dans un header qui appler Authorisation et la valeur c'est JWT
		response.addHeader("Authorization", jwt);
		//on peut ajouter LE REFRECH TOKEN qui a une long durée pour données a l'utilisateur un ai=utre token de 5minutes q'on a preciser dans l'ACCESS TOKEN pour resoudre le problem de la revocation de token
		//Spring Security & JWT - Musclez vos compétences en Sécurité des Apps web minute 2h 45min
	}

}
