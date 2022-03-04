package com.ms.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.user.models.UserModel;

/**
 * @author Pedro Ferreira
 **/
@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{

}
