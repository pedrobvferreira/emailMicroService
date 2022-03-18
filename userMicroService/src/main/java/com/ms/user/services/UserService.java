package com.ms.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ms.user.consumers.UserSendMessage;
import com.ms.user.dtos.UserDto;
import com.ms.user.exceptions.ResourceNotFoundException;
import com.ms.user.models.UserModel;
import com.ms.user.repositories.UserRepository;

/**
 * @author Pedro Ferreira
 **/
@Service
public class UserService {
	
	UserRepository userRepository;
	UserSendMessage userSendMessage;
	
	@Autowired
	public UserService(UserRepository userRepository, UserSendMessage userSendMessage) {
		this.userRepository = userRepository;
		this.userSendMessage = userSendMessage;
	}
	
	public UserDto saveUser(UserDto userDto) {
		UserDto userDtoReturn = UserDto.convert(userRepository.save(UserModel.convert(userDto)));
		userSendMessage.sendMessage(userDtoReturn);
		
		return userDtoReturn;
	}
	
	public UserDto findById(Long id) {
		final var userModel = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
		
		return UserDto.convert(userModel);
	}
	
	public Page<UserDto> findAll(Pageable pageable) {
		var page = userRepository.findAll(pageable);
		return page.map(this::convertToUserDto);
	}
	
	private UserDto convertToUserDto(UserModel userModel) {
		return UserDto.convert(userModel);
	}
	
	public UserDto updateUser(UserDto userDto) {
		final var userModel = userRepository.findById(userDto.getId());
		
		if(!userModel.isPresent()) {
			new ResourceNotFoundException("No records found for this ID");
		}
		
		return UserDto.convert(userRepository.save(UserModel.convert(userDto)));
	}
	
	public void deleteUser(Long id) {
		final var userModel = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));

		userRepository.delete(userModel);
	}
}
