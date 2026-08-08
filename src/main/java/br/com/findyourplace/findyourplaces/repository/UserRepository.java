package br.com.findyourplace.findyourplaces.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.findyourplace.findyourplaces.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID>{

	Optional<UserEntity> findByNameOrEmail(String name, String email);
}
