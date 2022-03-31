package com.ms.test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.user.controllers.UserController;
import com.ms.user.dtos.UserDto;
import com.ms.user.services.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	public static String endpoint = "/api/users";
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@Test
	public void getUserByIdTest() throws Exception {
		var userDto = new UserDto(1L, "Pedro", "pedrobvf@hotmail.com");
		when(userService.getUserById(anyLong())).thenReturn(userDto);
		
		mockMvc.perform(get(endpoint + "/{id}", 1L))
    	.andExpect(jsonPath("$.name").value("Pedro"))
    	.andExpect(jsonPath("$.email").value("pedrobvf@hotmail.com"))
        .andDo(print())
    	.andExpect(status().isOk())
    	.andReturn();
	}
	
	@Test
	public void createUserTest() throws Exception {
		var userDto = new UserDto(1L, "Filipe Fernandes", "filipe_fernandes@gmail.com");
		when(userService.saveUser(any(UserDto.class))).thenReturn(userDto);
		
		//mock request "/user
        mockMvc.perform(post(endpoint)
        	.content(new ObjectMapper().writeValueAsString(userDto))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.name").value("Filipe Fernandes"))
            .andExpect(jsonPath("$.email").value("filipe_fernandes@gmail.com"))
            .andDo(print())
            .andExpect(status().isCreated())
            .andReturn();
	}
	
	@Test
	public void updateUserTest() throws Exception {
		var userDto = new UserDto(1L, "Pedro", "pedrobvf@hotmail.com");
		when(userService.getUserById(anyLong())).thenReturn(userDto);
		
		var updatedUserDto = new UserDto(3L, "David Landup", "david_landup@hotmail.com");
		when(userService.updateUser(any(UserDto.class))).thenReturn(updatedUserDto);
        
        //mock update "/user
        mockMvc.perform(put(endpoint + "/{id}", 1)
        	.content(new ObjectMapper().writeValueAsString(updatedUserDto))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.name").value("David Landup"))
            .andExpect(jsonPath("$.email").value("david_landup@hotmail.com"))
            .andDo(print())
            .andExpect(status().isOk())
        	.andReturn();
	}
	
	@Test
	public void deleteUserByIdTest() throws Exception {
		var userDto = new UserDto(1L, "Filipe Fernandes", "filipe_fernandes@gmail.com");
		doNothing().when(userService).deleteUser(userDto.getId());
		
        mockMvc.perform(delete(endpoint + "/{id}", 1)
	        .contentType(MediaType.APPLICATION_JSON)
	        .accept(MediaType.APPLICATION_JSON))
        	.andDo(print())
        	.andExpect(status().isOk())
        	.andReturn();
	}
}
