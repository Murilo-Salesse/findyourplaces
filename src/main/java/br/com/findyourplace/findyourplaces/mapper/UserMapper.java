package br.com.findyourplace.findyourplaces.mapper;

import org.springframework.stereotype.Component;

import br.com.findyourplace.findyourplaces.controller.dto.response.CreateAdminResponseDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.CreateUserResponseDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.ListInfosUserDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.UpdateProfileInfosResponseDTO;
import br.com.findyourplace.findyourplaces.entity.UserEntity;

@Component
public class UserMapper {

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

    public ListInfosUserDTO toProfileResponse(UserEntity user) {
		
		return new ListInfosUserDTO(user.getId(),
			    user.getName(), 
			    user.getEmail(),
			    user.getPhone(), 
			    user.getStatus(), 
			    user.getCreatedAt());
	}

    public CreateAdminResponseDTO toAdminResponse(UserEntity user) {
        return new CreateAdminResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getPhone(),
                user.getStatus(), user.getCreatedAt());
    }
}
