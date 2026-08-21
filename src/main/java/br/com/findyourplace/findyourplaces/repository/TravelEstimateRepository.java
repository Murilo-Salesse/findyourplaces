package br.com.findyourplace.findyourplaces.repository;

import br.com.findyourplace.findyourplaces.entity.TravelEstimateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TravelEstimateRepository extends JpaRepository<TravelEstimateEntity, UUID> {

    Optional<TravelEstimateEntity> findByTripPlanId(UUID tripPlanId);
}
