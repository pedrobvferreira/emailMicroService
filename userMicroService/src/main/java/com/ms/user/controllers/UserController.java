package com.ms.user.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

@RestController
@RequestMapping("/api")
public class UserController {
	
	private UserService userService;
	private PagedResourcesAssembler<UserDto> assembler;
	
	@Autowired
	public UserController(UserService userService, PagedResourcesAssembler<UserDto> assembler) {
		this.userService = userService;
		this.assembler = assembler;
	}
	
	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;

		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));
		
		Page<UserDto> users = userService.findAll(pageable);
		users.stream().forEach(p -> p.add(linkTo(methodOn(UserController.class).getUserById(p.getId())).withSelfRel()));
		
		PagedModel<EntityModel<UserDto>> pagedModel = assembler.toModel(users);
		
		return ResponseEntity.status(HttpStatus.OK).body(pagedModel);
	}
	
	@GetMapping(value = "/users/{id}")
	public UserDto getUserById(@PathVariable("id") Long id) {
		var userDto = userService.findById(id);
		userDto.add(linkTo(methodOn(UserController.class).getUserById(id)).withSelfRel());
		return userDto;
	}
	
	@PostMapping(value = "/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto) {
		try {
			var user = userService.saveUser(userDto);
			userDto.add(linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel());
			
			return ResponseEntity.status(HttpStatus.CREATED).body(user);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
    }
	
	@PutMapping(value = "/users/{id}")
	public ResponseEntity<?> updateUser(@Valid @RequestBody UserDto userDto) {
		var user = userService.updateUser(userDto);
		userDto.add(linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel());
		
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@DeleteMapping(value = "/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){	
		userService.deleteUser(id);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

}
