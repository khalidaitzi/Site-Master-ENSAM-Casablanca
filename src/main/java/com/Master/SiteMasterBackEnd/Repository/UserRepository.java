package com.Master.SiteMasterBackEnd.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.Master.SiteMasterBackEnd.Entities.User;

@RepositoryRestResource(path="users")
public interface UserRepository extends JpaRepository<User, Long> {
	 @RestResource(path = "/byEmail")
		public User findByEmail(@Param("email")String email);
	

}
