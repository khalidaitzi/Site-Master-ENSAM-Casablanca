package com.Master.SiteMasterBackEnd.Services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Master.SiteMasterBackEnd.Entities.Annonce;
import com.Master.SiteMasterBackEnd.Repository.AnnonceRepository;


@Service
@Transactional
public class AnnonceServiceImpl implements AnnonceService{
	
	@Autowired
	private AnnonceRepository annonceRepository;
	@Override
	public List<Annonce> getAllAnnonces() {
		
		return annonceRepository.findAll();
	}

	@Override
	public Annonce saveAnnonce(Annonce annonce) {
		
		return annonceRepository.save(annonce);
	}

}
