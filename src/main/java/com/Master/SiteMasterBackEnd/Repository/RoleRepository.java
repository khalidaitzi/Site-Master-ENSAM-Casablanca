package com.Master.SiteMasterBackEnd.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.Master.SiteMasterBackEnd.Entities.Role;

@RepositoryRestResource
public interface RoleRepository extends JpaRepository<Role, Long>{
	public Role findByRoleName(String rolename);

}
