package br.com.findyourplace.findyourplaces.service;

import java.util.List;
import java.util.UUID;

import br.com.findyourplace.findyourplaces.controller.dto.request.UpdateVehicleRequestDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.UpdateVehicleResponseDTO;
import org.springframework.stereotype.Service;

import br.com.findyourplace.findyourplaces.controller.dto.request.CreateVehicleRequestDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.CreateVehicleResponseDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.ListVehiclesResponseDTO;
import br.com.findyourplace.findyourplaces.entity.UserEntity;
import br.com.findyourplace.findyourplaces.entity.VehicleEntity;
import br.com.findyourplace.findyourplaces.enums.UserStatus;
import br.com.findyourplace.findyourplaces.exceptions.EntityNotFoundException;
import br.com.findyourplace.findyourplaces.exceptions.InactiveUserException;
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

	public VehicleService(VehicleRepository vehicleRepository, UserRepository userRepository,
			VehicleMapper vehicleMapper) {
		super();
		this.vehicleRepository = vehicleRepository;
		this.userRepository = userRepository;
		this.vehicleMapper = vehicleMapper;
	}

	@Transactional
	public CreateVehicleResponseDTO createVehicle(CreateVehicleRequestDTO req, UUID userId) {

		var user = findUserOrThrow(userId);

		var userActive = userRepository.existsByIdAndStatus(userId, UserStatus.ACTIVE);
		if (!userActive) {
			throw new InactiveUserException("Usuário inativo", "O usuário informado não está ativo.");
		}

		var vehicle = vehicleMapper.toEntity(req, user);
		vehicleRepository.save(vehicle);

		return vehicleMapper.toResponse(vehicle);
	}

	public List<ListVehiclesResponseDTO> findAllByUserId(UUID userId) {

		findUserOrThrow(userId);

		return this.vehicleRepository.findAllByUserId(userId).stream().map(vehicleMapper::toListResponse)
				.toList();

	}

	public ListVehiclesResponseDTO findById(UUID vehicleId, UUID userId) {

		findUserOrThrow(userId);
		return vehicleMapper.toListResponse(findVehicleOrThrow(vehicleId, userId));


	}

	@Transactional
	public UpdateVehicleResponseDTO update(UpdateVehicleRequestDTO req,
											UUID vehicleId,
											UUID userId) {

		var vehicle = findVehicleOrThrow(vehicleId, userId);
		vehicleMapper.updateEntity(req, vehicle);

		return vehicleMapper.toUpdateResponse(vehicle);
	}

	@Transactional
	public void delete(UUID vehicleId, UUID userId) {

		var vehicle = findVehicleOrThrow(vehicleId, userId);

		vehicleRepository.delete(vehicle);
	}

	private UserEntity findUserOrThrow(UUID userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(
						"Usuário não encontrado", "O usuário informado não existe."));
	}

	private VehicleEntity findVehicleOrThrow(UUID vehicleId, UUID userId) {
		return vehicleRepository.findByIdAndUserId(vehicleId, userId)
				.orElseThrow(() -> new EntityNotFoundException(
						"Veículo não encontrado", "O veículo informado não existe ou não pertence ao usuário."));
	}
}
