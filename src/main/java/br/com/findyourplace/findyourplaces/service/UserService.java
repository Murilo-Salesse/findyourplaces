package br.com.findyourplace.findyourplaces.service;

import java.util.List;
import java.util.UUID;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import br.com.findyourplace.findyourplaces.controller.dto.request.CreateUserRequestDTO;
import br.com.findyourplace.findyourplaces.controller.dto.request.UpdateUserRequestDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.CreateUserResponseDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.ListAllUsersResponseDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.ListInfosUserDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.UpdateProfileInfosResponseDTO;
import br.com.findyourplace.findyourplaces.exceptions.DuplicateUserException;
import br.com.findyourplace.findyourplaces.exceptions.UserNotFoundException;
import br.com.findyourplace.findyourplaces.factory.UserFactory;
import br.com.findyourplace.findyourplaces.mapper.UserMapper;
import br.com.findyourplace.findyourplaces.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final UserFactory userFactory;

	public UserService(UserRepository userRepository, UserMapper userMapper, UserFactory userFactory) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.userFactory = userFactory;
	}

	public CreateUserResponseDTO createUser(CreateUserRequestDTO req) {

		var existsUser = this.userRepository.findByNameOrEmail(req.name(), req.email());
		if (!existsUser.isEmpty()) {
			throw new DuplicateUserException("Usuário já cadastrado", "Nome ou e-mail já cadastrado.");
		}

		var user = userFactory.create(req.name(), req.email(), req.password(), req.phone(), "USER");

		userRepository.save(user);

		return userMapper.toResponse(user);
	}


	@Transactional
	public UpdateProfileInfosResponseDTO update(UUID userId, UpdateUserRequestDTO req) {

		var user = userRepository.findById(userId)
				.orElseThrow(() ->
						new UserNotFoundException("Usuário não encontrado", "O usuário informado não existe.")
				);

		if (req.name() != null && !req.name().isBlank()) {
			user.setName(req.name());
		}

		if (req.phone() != null && !req.phone().isBlank()) {
			user.setPhone(req.phone());
		}

		return userMapper.toUpdateResponse(user);
	}

	public ListInfosUserDTO findProfile(UUID userId) {

		var user = this.userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(
						"Usuário não encontrado", "O usuário informado não existe."));

		return userMapper.toProfileResponse(user);
	}


	public List<ListAllUsersResponseDTO> listAll() {

		return this.userRepository
				.findAll()
				.stream()
				.map(ListAllUsersResponseDTO::fromEntity).toList();
	}
}
