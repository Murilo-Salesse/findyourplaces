package br.com.findyourplace.findyourplaces.controller.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.findyourplace.findyourplaces.entity.UserEntity;
import br.com.findyourplace.findyourplaces.enums.UserStatus;

public record ListAllUsersResponseDTO(UUID id,
								      String name,
								      String email,
								      String phone,
								      UserStatus status,
								      LocalDateTime createdAt) {
	
    public static ListAllUsersResponseDTO fromEntity(UserEntity user) {
    	
        return new ListAllUsersResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getStatus(),
                user.getCreatedAt()
        );
    }
	
}
