package com.Master.SiteMasterBackEnd;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.Master.SiteMasterBackEnd.Entities.Annonce;
import com.Master.SiteMasterBackEnd.Entities.Message;
import com.Master.SiteMasterBackEnd.Entities.Role;
import com.Master.SiteMasterBackEnd.Services.AnnonceService;
import com.Master.SiteMasterBackEnd.Services.MessageService;
import com.Master.SiteMasterBackEnd.Services.UserService;



@SpringBootApplication
public class SiteMasterBackEndApplication implements CommandLineRunner {

	@Autowired
	UserService userService;
	@Autowired
	AnnonceService annonceService;
	@Autowired
	MessageService messageService;
	
	public static void main(String[] args) {
		SpringApplication.run(SiteMasterBackEndApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("===============================START=======================================");
		userService.saveRole(new Role(null,"ADMIN"));
		userService.saveRole(new Role(null,"USER"));
		userService.saveUser("user","user","user@mail.com", "1234","USER",true,new Date(),"Etablissement","Grade","0655443322","Specialite","Theme de recherche");
		userService.saveUser("admin","admin","admin@gmail.com","1234","ADMIN",true,new Date(),"Etablissement","Grade","0655443322","Specialite","Theme de recherche");
		for (int i = 1; i < 11; i++) {
			userService.saveUser("user"+i,"user"+i,"user"+i+"@gmail.com","1234","USER",true,new Date(),"Etablissement","Grade","0655443322","Specialite","Theme de recherche");
		}
		for (int i = 1; i < 3; i++) {
		annonceService.saveAnnonce(new Annonce(null,"Sed do eiusmod tempor incididunt","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolo.",new Date(),"Urgent"));		}
		for (int i = 1; i < 11; i++) {
			messageService.saveMessage(new Message(null,"Nom "+i,"nom"+i+"@mail.com","Message de nom"+i));
		}
		
	}

}
