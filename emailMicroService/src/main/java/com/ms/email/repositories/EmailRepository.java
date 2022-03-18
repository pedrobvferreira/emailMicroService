package com.ms.email.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ms.email.models.EmailModel;

/**
 * @author Pedro Ferreira
 **/
public interface EmailRepository extends JpaRepository<EmailModel, Long>{
	
	@Query("SELECT em FROM EmailModel em where email like :email%") 
	List<EmailModel> findByEmail(@Param("email") String email);

}
