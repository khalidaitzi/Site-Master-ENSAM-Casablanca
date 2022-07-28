package com.Master.SiteMasterBackEnd.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Master.SiteMasterBackEnd.Entities.Message;

public interface MessageRepository  extends JpaRepository<Message, Long>{

}
