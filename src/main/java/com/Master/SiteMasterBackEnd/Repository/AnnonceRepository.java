package com.Master.SiteMasterBackEnd.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.Master.SiteMasterBackEnd.Entities.Annonce;

@RepositoryRestResource
public interface AnnonceRepository extends JpaRepository<Annonce, Long>{
	

}
