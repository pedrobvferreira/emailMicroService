package com.ms.user.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.user.exceptions.ResourceNotFoundException;
import com.ms.user.models.UserModel;
import com.ms.user.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public UserModel createUser(UserModel user) {
		return userRepository.save(user);
	}

	public UserModel updateUser(UserModel user) {
		final var userModel = userRepository.findById(user.getId());
		
		if(!userModel.isPresent()) {
			new ResourceNotFoundException("No records found for this ID");
		}
		
		return userRepository.save(user);
	}
	
	public void  deleteUser(UserModel user) {
		final var userModel = userRepository.findById(user.getId());

		if(!userModel.isPresent()) {
			new ResourceNotFoundException("No records found for this ID");
		}
		
		userRepository.delete(user);
	}
	
	public Optional<UserModel> findById(Long id) {
		return userRepository.findById(id);
	}
	
	public List<UserModel> findAll() {
		var page = userRepository.findAll();
		return page;
	}
}
