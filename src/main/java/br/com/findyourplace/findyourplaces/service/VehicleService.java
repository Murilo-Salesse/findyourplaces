package br.com.findyourplace.findyourplaces.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.findyourplace.findyourplaces.controller.dto.request.CreateVehicleRequestDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.CreateVehiclesResponseDTO;
import br.com.findyourplace.findyourplaces.enums.UserStatus;
import br.com.findyourplace.findyourplaces.exceptions.EntityNotFoundException;
import br.com.findyourplace.findyourplaces.exceptions.UserNotFoundException;
import br.com.findyourplace.findyourplaces.mapper.VehicleMapper;
import br.com.findyourplace.findyourplaces.repository.UserRepository;
import br.com.findyourplace.findyourplaces.repository.VehicleRepository;
import jakarta.transaction.Transactional;

@Service
public class VehicleService {

	private final VehicleRepository vehicleRepository;
	private final UserRepository userRepository;
	private final VehicleMapper vehicleMapper;

	public VehicleService(VehicleRepository vehicleRepository, UserRepository userRepository, VehicleMapper vehicleMapper) {
		super();
		this.vehicleRepository = vehicleRepository;
		this.userRepository = userRepository;
		this.vehicleMapper = vehicleMapper;
	}
	
	@Transactional
	public CreateVehiclesResponseDTO createVehicle(CreateVehicleRequestDTO req, UUID userId) {

	    var user = userRepository.findById(userId)
	            .orElseThrow(() -> new UserNotFoundException("Problema ao encontrar usuário.", "Usuário não encontrado."));

	    var userActive = userRepository.existsByIdAndStatus(userId, UserStatus.ACTIVE);

	    if (!userActive) {
	        throw new EntityNotFoundException("Usuário não encontrado.", "Usuário não está ativo.");
	    }

	    var vehicle = vehicleMapper.toEntity(req, user);

	    vehicleRepository.save(vehicle);

	    return vehicleMapper.toResponse(vehicle);
	}
}
