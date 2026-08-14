package br.com.findyourplace.findyourplaces.repository;

import org.springframework.stereotype.Repository;

import br.com.findyourplace.findyourplaces.entity.VehicleEntity;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, UUID>{

}
 