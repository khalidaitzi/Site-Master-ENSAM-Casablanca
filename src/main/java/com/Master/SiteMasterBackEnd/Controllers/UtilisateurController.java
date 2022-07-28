package com.Master.SiteMasterBackEnd.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Master.SiteMasterBackEnd.Entities.Utilisateur;
import com.Master.SiteMasterBackEnd.Repository.userrepo;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UtilisateurController {
	
	@Autowired
	private userrepo repo;
	
	@PostMapping("/adduser")
	public ResponseEntity<Utilisateur> registerUser(@RequestBody Utilisateur user) {
		
	 System.out.println("Controller called");
	 return ResponseEntity.ok(repo.save(user));
		
		
	}

}