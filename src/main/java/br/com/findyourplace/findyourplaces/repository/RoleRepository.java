package br.com.findyourplace.findyourplaces.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.findyourplace.findyourplaces.entity.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, UUID>{

	Optional<RoleEntity> findByName(String name);
}
