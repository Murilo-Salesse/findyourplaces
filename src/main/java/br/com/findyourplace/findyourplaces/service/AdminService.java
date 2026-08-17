package br.com.findyourplace.findyourplaces.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.findyourplace.findyourplaces.controller.dto.request.CreateAdminRequestDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.CreateAdminResponseDTO;
import br.com.findyourplace.findyourplaces.entity.RoleEntity;
import br.com.findyourplace.findyourplaces.entity.UserEntity;
import br.com.findyourplace.findyourplaces.exceptions.AdminException;
import br.com.findyourplace.findyourplaces.repository.RoleRepository;
import br.com.findyourplace.findyourplaces.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AdminService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;

	public AdminService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}

	public CreateAdminResponseDTO setupAdmin(CreateAdminRequestDTO req) {

		var qtdUsers = this.userRepository.count();
		if (qtdUsers > 0) {
			throw new AdminException("Problema ao registrar ADMIN.",
					"ADMIN só pode ser criado quando não há usuários no sistema.");
		}

		RoleEntity roles = this.roleRepository.findByName("ADMIN")
				.orElseThrow(() -> new EntityNotFoundException("Role com esse nome não encontrado"));

		String hashedPassword = this.passwordEncoder.encode(req.password());

		var newUser = new UserEntity();

		newUser.setName(req.name());
		newUser.setEmail(req.email());
		newUser.setPasswordHash(hashedPassword);
		newUser.setPhone(req.phone());
		newUser.getRoles().add(roles);


		this.userRepository.save(newUser);

		return new CreateAdminResponseDTO(newUser.getId(), newUser.getName(), newUser.getEmail(), newUser.getPhone(),
				newUser.getStatus(), newUser.getCreatedAt());
	}

}
