package br.com.findyourplace.findyourplaces.repository;

import br.com.findyourplace.findyourplaces.entity.TripEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TripRepository extends JpaRepository<TripEntity, UUID> {
}
