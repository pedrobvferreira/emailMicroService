package com.ms.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ms.user.dtos.EmailDto;
import com.ms.user.dtos.UserDto;
import com.ms.user.exceptions.ResourceNotFoundException;
import com.ms.user.models.EmailModel;
import com.ms.user.models.UserModel;
import com.ms.user.repositories.EmailRepository;
import com.ms.user.repositories.UserRepository;

/**
 * @author Pedro Ferreira
 **/
@Service
public class UserService {
	
	UserRepository userRepository;
	EmailRepository emailRepository;
	
	@Autowired
	public UserService(UserRepository userRepository, EmailRepository emailRepository) {
		this.userRepository = userRepository;
	}
	
	public UserDto saveUser(UserDto userDto) {
		final var userModel = userRepository.save(UserModel.convert(userDto));
		
		EmailDto emailDto = new EmailDto();
		emailDto.setEmailFrom("companhia@gmail.com");
		emailDto.setEmailTo(userDto.getEmail());
		emailDto.setSubject("Criação do Email");
		emailDto.setBody("MicroServico de Envio de Email");
		userDto.setEmailSenderDto(emailDto);
		emailRepository.save(EmailModel.convert(emailDto));
		
		UserDto userDtoReturn = UserDto.convert(userModel);
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
