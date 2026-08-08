package br.com.findyourplace.findyourplaces.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.findyourplace.findyourplaces.controller.dto.request.CreateUserRequestDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.CreateUserResponseDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.ListAllUsersResponseDTO;
import br.com.findyourplace.findyourplaces.entity.UserEntity;
import br.com.findyourplace.findyourplaces.exceptions.CreateEntityException;
import br.com.findyourplace.findyourplaces.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	public CreateUserResponseDTO createUser(CreateUserRequestDTO req) {
		
		var existsUser = this.userRepository.findByNameOrEmail(req.name(), req.email());
		if (!existsUser.isEmpty()) {
			throw new CreateEntityException("Problema ao registrar usuário.", "Usuário já existe na base.");
		}
		
		var newUser = new UserEntity();
		
		newUser.setName(req.name());
		newUser.setEmail(req.email());
		newUser.setPasswordHash(req.password());
		newUser.setPhone(req.phone());
		
		this.userRepository.save(newUser);
		
		return new CreateUserResponseDTO(newUser.getId(),
										 newUser.getName(),
										 newUser.getEmail(),
										 newUser.getPhone(),
										 newUser.getStatus(),
										 newUser.getCreatedAt()); 
	}
	
	public List<ListAllUsersResponseDTO> listAll() {

	    return this.userRepository.findAll()
	            .stream()
	            .map(ListAllUsersResponseDTO::fromEntity)
	            .toList();
	}
}
