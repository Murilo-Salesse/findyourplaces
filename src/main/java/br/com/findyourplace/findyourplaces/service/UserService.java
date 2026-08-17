package br.com.findyourplace.findyourplaces.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.findyourplace.findyourplaces.controller.dto.request.CreateUserRequestDTO;
import br.com.findyourplace.findyourplaces.controller.dto.request.UpdateUserRequestDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.CreateUserResponseDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.ListAllUsersResponseDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.ListInfosUserDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.UpdateProfileInfosResponseDTO;
import br.com.findyourplace.findyourplaces.entity.RoleEntity;
import br.com.findyourplace.findyourplaces.exceptions.CreateEntityException;
import br.com.findyourplace.findyourplaces.mapper.UserMapper;
import br.com.findyourplace.findyourplaces.repository.RoleRepository;
import br.com.findyourplace.findyourplaces.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository,
			UserMapper userMapper) {
		super();
		this.userRepository = userRepository;
		this.userMapper = userMapper;
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

		var user = userMapper.toEntity(req, roles, hashedPassword);

		userRepository.save(user);

		return userMapper.toResponse(user);
	}


	public UpdateProfileInfosResponseDTO update(UUID userId, UpdateUserRequestDTO req) {

		var user = this.userRepository.findById(userId)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

		if (req.name() != null && !req.name().isBlank()) {
			user.setName(req.name());
		}

		if (req.phone() != null && !req.phone().isBlank()) {
			user.setPhone(req.phone());
		}

		this.userRepository.save(user);

		return userMapper.toUpdateResponse(user);
	}

	public ListInfosUserDTO listInfos(UUID userId) {

		var user = this.userRepository.findById(userId)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

		return userMapper.toListInfosUserReponse(user);
	}


	public List<ListAllUsersResponseDTO> listAll() {

		return this.userRepository
				.findAll()
				.stream()
				.map(ListAllUsersResponseDTO::fromEntity).toList();
	}
}
