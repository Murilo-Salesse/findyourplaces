package br.com.findyourplace.findyourplaces.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.findyourplace.findyourplaces.controller.dto.request.CreateUserRequestDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.CreateUserResponseDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.ListAllUsersResponseDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.ListInfosUserDTO;
import br.com.findyourplace.findyourplaces.entity.RoleEntity;
import br.com.findyourplace.findyourplaces.entity.UserEntity;
import br.com.findyourplace.findyourplaces.exceptions.CreateEntityException;
import br.com.findyourplace.findyourplaces.repository.RoleRepository;
import br.com.findyourplace.findyourplaces.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}
	
	public CreateUserResponseDTO createUser(CreateUserRequestDTO req) {
		
		var existsUser = this.userRepository.findByNameOrEmail(req.name(), req.email());
		if (!existsUser.isEmpty()) {
			throw new CreateEntityException("Problema ao registrar usuário.", "Usuário já existe na base.");
		}
		
		RoleEntity roles = this.roleRepository.findByName("USER")
				.orElseThrow(() -> new EntityNotFoundException("Role com esse nome não encontrado"));
		
	    String hashedPassword = passwordEncoder.encode(req.password());
		var newUser = new UserEntity();
		
		newUser.setName(req.name());
		newUser.setEmail(req.email());
		newUser.setPasswordHash(hashedPassword);
		newUser.setPhone(req.phone());
		newUser.getRoles().add(roles);
		
		this.userRepository.save(newUser);
		
		return new CreateUserResponseDTO(newUser.getId(),
										 newUser.getName(),
										 newUser.getEmail(),
										 newUser.getPhone(),
										 newUser.getStatus(),
										 newUser.getCreatedAt()); 
	}
	
	public ListInfosUserDTO listInfos(UUID userId) {
		
		var user = this.userRepository.findById(userId)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

		return new ListInfosUserDTO(userId, 
								    user.getName(), 
								    user.getEmail(),
								    user.getPhone(), 
								    user.getStatus(), 
								    user.getCreatedAt());
		
	}
	
	public List<ListAllUsersResponseDTO> listAll() {

	    return this.userRepository.findAll()
	            .stream()
	            .map(ListAllUsersResponseDTO::fromEntity)
	            .toList();
	}
}
