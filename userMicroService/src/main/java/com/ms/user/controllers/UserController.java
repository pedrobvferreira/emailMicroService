package com.ms.user.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ms.user.dtos.UserDto;
import com.ms.user.services.UserService;
import com.ms.user.utils.StringUtils;

/**
 * @author Pedro Ferreira
 **/
@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService userService;
	private PagedResourcesAssembler<UserDto> assembler;
	
	@Autowired
	public UserController(UserService userService, PagedResourcesAssembler<UserDto> assembler) {
		this.userService = userService;
		this.assembler = assembler;
	}
	
	@GetMapping(value = "", produces = {"application/json","application/xml"})
	public ResponseEntity<?> getAllUsers(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "5") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;

		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "name"));
		
		Page<UserDto> users = userService.getAllUsers(pageable);
		users.stream().forEach(p -> p.add(linkTo(methodOn(UserController.class).getUserById(p.getId())).withSelfRel()));
		
		PagedModel<EntityModel<UserDto>> pagedModel = assembler.toModel(users);
		
		return ResponseEntity.status(HttpStatus.OK).body(pagedModel);
	}
	
	@GetMapping(value = "/{id}", produces = {"application/json","application/xml"})
	public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
		var user = userService.getUserById(id);
		user.add(linkTo(methodOn(UserController.class).getUserById(id)).withSelfRel());
		
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@PostMapping(value = "", produces = {"application/json", "application/xml"}, 
			consumes = {"application/json", "application/xml"})
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto) {
		try {
			if(StringUtils.isNotNullOrEmpty(userDto.getPassword())) {
				var user = userService.saveUser(userDto);
				user.add(linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel());
				
				return ResponseEntity.status(HttpStatus.CREATED).body(user);
			}else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
    }
	
	@PutMapping(value = "/{id}", produces = {"application/json", "application/xml"}, 
			consumes = {"application/json", "application/xml"})
	public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserDto userDto) {
		var user = userService.getUserById(id);
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		if(StringUtils.isNotNullOrEmpty(userDto.getPassword()) && userDto.getPassword()!=user.getPassword()) {
			user.setPassword(userDto.getPassword());
		}
		userService.updateUser(user);
		user.add(linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel());
		
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id){	
		userService.deleteUser(id);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

}
