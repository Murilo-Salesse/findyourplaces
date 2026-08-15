package br.com.findyourplace.findyourplaces.mapper;

import org.springframework.stereotype.Component;

import br.com.findyourplace.findyourplaces.controller.dto.request.CreateUserRequestDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.CreateUserResponseDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.ListInfosUserDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.UpdateProfileInfosResponseDTO;
import br.com.findyourplace.findyourplaces.entity.RoleEntity;
import br.com.findyourplace.findyourplaces.entity.UserEntity;

@Component
public class UserMapper {

	public UserEntity toEntity(CreateUserRequestDTO dto,  
							   RoleEntity roles, 
							   String hashedPassword) {
		
		var user = new UserEntity();
		
		user.setName(dto.name());
		user.setEmail(dto.email());
		user.setPasswordHash(hashedPassword);
		user.setPhone(dto.phone());
		user.getRoles().add(roles);
		
		return user;
	}

	public CreateUserResponseDTO toResponse(UserEntity user) {
		
		return new CreateUserResponseDTO(user.getId(),
				user.getName(),
				user.getEmail(),
				user.getPhone(),
				user.getStatus(),
				user.getCreatedAt()); 
	}
	
	public UpdateProfileInfosResponseDTO toUpdateResponse(UserEntity user) {
		
		return new UpdateProfileInfosResponseDTO(user.getName(), user.getPhone());
	}
	
	public ListInfosUserDTO toListInfosUserReponse(UserEntity user) {
		
		return new ListInfosUserDTO(user.getId(),
			    user.getName(), 
			    user.getEmail(),
			    user.getPhone(), 
			    user.getStatus(), 
			    user.getCreatedAt());
	}
}
