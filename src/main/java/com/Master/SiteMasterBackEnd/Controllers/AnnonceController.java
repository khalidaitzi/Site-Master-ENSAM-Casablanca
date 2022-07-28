package com.Master.SiteMasterBackEnd.Controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Master.SiteMasterBackEnd.Entities.Annonce;
import com.Master.SiteMasterBackEnd.Services.AnnonceService;

import lombok.Data;

@RestController
public class AnnonceController {
	@Autowired
	AnnonceService annonceService;
	
	@GetMapping("/annonces")
	public List<Annonce> getAll()
	{
		return annonceService.getAllAnnonces();
	}
	@PostMapping("/annonces")
	public Annonce register(@RequestBody AnnonceForm annonceForm)
	{
		
		//System.err.println(userForm);
		return annonceService.saveAnnonce(new Annonce(null,
				annonceForm.getTitle(),
				annonceForm.getContenu(),
				annonceForm.getDate(),
				annonceForm.getType()));
	}

}

	@Data
	class AnnonceForm{
		private String title;
		private String contenu;
		private Date date;
		private String type;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getContenu() {
			return contenu;
		}
		public void setContenu(String contenu) {
			this.contenu = contenu;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		
	}
