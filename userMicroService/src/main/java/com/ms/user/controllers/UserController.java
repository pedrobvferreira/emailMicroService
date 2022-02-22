package com.ms.user.controllers;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.user.dtos.UserDto;
import com.ms.user.models.UserModel;
import com.ms.user.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/save")
    public ResponseEntity<UserModel> createUser(@RequestBody @Valid UserDto userDto) {
		try {
			UserModel userModel = new UserModel();
			BeanUtils.copyProperties(userDto, userModel);
			userService.createUser(userModel);
			return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
    }

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		userService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

}
