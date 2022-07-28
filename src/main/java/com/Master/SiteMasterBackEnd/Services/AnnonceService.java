package com.Master.SiteMasterBackEnd.Services;

import java.util.List;

import com.Master.SiteMasterBackEnd.Entities.Annonce;


public interface AnnonceService {
	public List<Annonce> getAllAnnonces();
	public Annonce saveAnnonce(Annonce annonce);

}
