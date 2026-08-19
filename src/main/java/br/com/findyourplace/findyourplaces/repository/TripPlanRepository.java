package br.com.findyourplace.findyourplaces.repository;

import br.com.findyourplace.findyourplaces.entity.TripPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TripPlanRepository extends JpaRepository<TripPlanEntity, UUID> {

    Optional<TripPlanEntity> findByTripId(UUID tripId);
}
