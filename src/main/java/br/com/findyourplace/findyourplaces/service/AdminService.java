package br.com.findyourplace.findyourplaces.service;

import org.springframework.stereotype.Service;

import br.com.findyourplace.findyourplaces.controller.dto.request.CreateAdminRequestDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.CreateAdminResponseDTO;
import br.com.findyourplace.findyourplaces.exceptions.AdminException;
import br.com.findyourplace.findyourplaces.factory.UserFactory;
import br.com.findyourplace.findyourplaces.mapper.UserMapper;
import br.com.findyourplace.findyourplaces.repository.UserRepository;

@Service
public class AdminService {

	private final UserRepository userRepository;
	private final UserFactory userFactory;
	private final UserMapper userMapper;

	public AdminService(UserRepository userRepository, UserFactory userFactory, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userFactory = userFactory;
		this.userMapper = userMapper;
	}

	public CreateAdminResponseDTO setupAdmin(CreateAdminRequestDTO req) {

		var qtdUsers = this.userRepository.count();
		if (qtdUsers > 0) {
			throw new AdminException("Problema ao registrar ADMIN.",
					"ADMIN só pode ser criado quando não há usuários no sistema.");
		}

		var newUser = userFactory.create(req.name(), req.email(), req.password(), req.phone(), "ADMIN");
		this.userRepository.save(newUser);

		return userMapper.toAdminResponse(newUser);
	}

}
