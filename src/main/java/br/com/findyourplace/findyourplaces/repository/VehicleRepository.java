package br.com.findyourplace.findyourplaces.repository;

import org.springframework.stereotype.Repository;

import br.com.findyourplace.findyourplaces.entity.VehicleEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, UUID>{

	List<VehicleEntity> findAllByUserId(UUID userId);
	Optional<VehicleEntity> findByIdAndUserId(UUID vehicleId, UUID userId);
}
 