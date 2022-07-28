package com.Master.SiteMasterBackEnd.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Master.SiteMasterBackEnd.Entities.Utilisateur;


@Repository
public interface userrepo extends JpaRepository<Utilisateur, Long>{

}