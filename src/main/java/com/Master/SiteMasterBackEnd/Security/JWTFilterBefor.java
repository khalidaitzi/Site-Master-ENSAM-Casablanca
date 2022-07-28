package com.Master.SiteMasterBackEnd.Security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;


//pour verifier lo token et les authorités d'un utilisateur ce qu'on apple jwt authorisation filter
public class JWTFilterBefor extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		response.addHeader("Access-Control-Allow-Origin","*");
		response.addHeader("Access-Control-Allow-Headers","Origin, Accept, X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,authorization");
		response.addHeader ("Access-Control-Expose-Headers","Access-Control-Allow-Origin,Access-Control-Allow-Credentials, authorization");
		response.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,PATCH");
		if(request .getMethod (). equals("OPTIONS")) {
		response.setStatus(HttpServletResponse.SC_OK);
		}
		else if(request.getRequestURI().equals("/login")) {
			filterChain.doFilter(request, response);
			return;
		}
		else{
		String jwt=request.getHeader(SecutiryParams.HEADER_NAME);
		if(jwt==null || !jwt.startsWith(SecutiryParams.HEADER_PRIFIX))
		{
			filterChain.doFilter(request, response);
			return;
		}
		//l'algorithm utiliser pour verifier le token
		JWTVerifier verifier=JWT.require(Algorithm.HMAC256(SecutiryParams.SECRET)).build();
		//pour suprimer le header ignorer les Header-prefix.length() premiers caractères et retourner une variable de type decodeJWT qu'il contient le contenu
		DecodedJWT decodedJWT=verifier.verify(jwt.substring(SecutiryParams.HEADER_PRIFIX.length()));
		String username=decodedJWT.getSubject();
		List<String> roles=decodedJWT.getClaims().get("roles").asList(String.class);
		Collection<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		roles.forEach(rn->{
			authorities.add(new SimpleGrantedAuthority(rn));
		});
		//mot de pass null hitach fach kanverifier jwt mafihch lmot de pass
		UsernamePasswordAuthenticationToken user=new UsernamePasswordAuthenticationToken(username,null,authorities);
		//et après je vais authentifier cet utilisateur setAuthentification
		SecurityContextHolder.getContext().setAuthentication(user);
		// je vais lui dire tu peut passer c'est a dire tu pass au filtre suivant
		filterChain.doFilter(request, response);
		
	}
	}
}
