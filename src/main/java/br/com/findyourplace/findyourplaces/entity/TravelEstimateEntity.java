package br.com.findyourplace.findyourplaces.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_travel_estimates")
public class TravelEstimateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "trip_plan_id", nullable = false)
    private TripPlanEntity tripPlan;

    @Column(name = "distance_one_way_km")
    private BigDecimal distanceOneWayKm;

    @Column(name = "total_distance_km")
    private BigDecimal totalDistanceKm;

    @Column(name = "estimated_duration_minutes")
    private Integer estimatedDurationMinutes;

    @Column(name = "fuel_consumption_km_l")
    private BigDecimal fuelConsumptionKmL;

    @Column(name = "fuel_price_per_liter")
    private BigDecimal fuelPricePerLiter;

    @Column(name = "estimated_fuel_liters")
    private BigDecimal estimatedFuelLiters;

    @Column(name = "estimated_fuel_cost")
    private BigDecimal estimatedFuelCost;

    @Column(name = "estimated_toll_cost")
    private BigDecimal estimatedTollCost;

    @Column(name = "estimated_ticket_cost")
    private BigDecimal estimatedTicketCost;

    @Column(name = "estimated_accommodation_cost")
    private BigDecimal estimatedAccommodationCost;

    @Column(name = "estimated_food_cost")
    private BigDecimal estimatedFoodCost;

    @Column(name = "emergency_reserve")
    private BigDecimal emergencyReserve;

    @Column(name = "total_estimated_cost")
    private BigDecimal totalEstimatedCost;

    @Column(name = "currency", length = 3)
    private String currency = "BRL";

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public TravelEstimateEntity() {
    }

    public TravelEstimateEntity(UUID id, TripPlanEntity tripPlan, BigDecimal distanceOneWayKm, BigDecimal totalDistanceKm, Integer estimatedDurationMinutes, BigDecimal fuelConsumptionKmL, BigDecimal fuelPricePerLiter, BigDecimal estimatedFuelLiters, BigDecimal estimatedFuelCost, BigDecimal estimatedTollCost, BigDecimal estimatedTicketCost, BigDecimal estimatedAccommodationCost, BigDecimal estimatedFoodCost, BigDecimal emergencyReserve, BigDecimal totalEstimatedCost, String currency, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.tripPlan = tripPlan;
        this.distanceOneWayKm = distanceOneWayKm;
        this.totalDistanceKm = totalDistanceKm;
        this.estimatedDurationMinutes = estimatedDurationMinutes;
        this.fuelConsumptionKmL = fuelConsumptionKmL;
        this.fuelPricePerLiter = fuelPricePerLiter;
        this.estimatedFuelLiters = estimatedFuelLiters;
        this.estimatedFuelCost = estimatedFuelCost;
        this.estimatedTollCost = estimatedTollCost;
        this.estimatedTicketCost = estimatedTicketCost;
        this.estimatedAccommodationCost = estimatedAccommodationCost;
        this.estimatedFoodCost = estimatedFoodCost;
        this.emergencyReserve = emergencyReserve;
        this.totalEstimatedCost = totalEstimatedCost;
        this.currency = currency;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TripPlanEntity getTripPlan() {
        return tripPlan;
    }

    public void setTripPlan(TripPlanEntity tripPlan) {
        this.tripPlan = tripPlan;
    }

    public BigDecimal getDistanceOneWayKm() {
        return distanceOneWayKm;
    }

    public void setDistanceOneWayKm(BigDecimal distanceOneWayKm) {
        this.distanceOneWayKm = distanceOneWayKm;
    }

    public BigDecimal getTotalDistanceKm() {
        return totalDistanceKm;
    }

    public void setTotalDistanceKm(BigDecimal totalDistanceKm) {
        this.totalDistanceKm = totalDistanceKm;
    }

    public Integer getEstimatedDurationMinutes() {
        return estimatedDurationMinutes;
    }

    public void setEstimatedDurationMinutes(Integer estimatedDurationMinutes) {
        this.estimatedDurationMinutes = estimatedDurationMinutes;
    }

    public BigDecimal getFuelConsumptionKmL() {
        return fuelConsumptionKmL;
    }

    public void setFuelConsumptionKmL(BigDecimal fuelConsumptionKmL) {
        this.fuelConsumptionKmL = fuelConsumptionKmL;
    }

    public BigDecimal getFuelPricePerLiter() {
        return fuelPricePerLiter;
    }

    public void setFuelPricePerLiter(BigDecimal fuelPricePerLiter) {
        this.fuelPricePerLiter = fuelPricePerLiter;
    }

    public BigDecimal getEstimatedFuelLiters() {
        return estimatedFuelLiters;
    }

    public void setEstimatedFuelLiters(BigDecimal estimatedFuelLiters) {
        this.estimatedFuelLiters = estimatedFuelLiters;
    }

    public BigDecimal getEstimatedFuelCost() {
        return estimatedFuelCost;
    }

    public void setEstimatedFuelCost(BigDecimal estimatedFuelCost) {
        this.estimatedFuelCost = estimatedFuelCost;
    }

    public BigDecimal getEstimatedTollCost() {
        return estimatedTollCost;
    }

    public void setEstimatedTollCost(BigDecimal estimatedTollCost) {
        this.estimatedTollCost = estimatedTollCost;
    }

    public BigDecimal getEstimatedTicketCost() {
        return estimatedTicketCost;
    }

    public void setEstimatedTicketCost(BigDecimal estimatedTicketCost) {
        this.estimatedTicketCost = estimatedTicketCost;
    }

    public BigDecimal getEstimatedAccommodationCost() {
        return estimatedAccommodationCost;
    }

    public void setEstimatedAccommodationCost(BigDecimal estimatedAccommodationCost) {
        this.estimatedAccommodationCost = estimatedAccommodationCost;
    }

    public BigDecimal getEstimatedFoodCost() {
        return estimatedFoodCost;
    }

    public void setEstimatedFoodCost(BigDecimal estimatedFoodCost) {
        this.estimatedFoodCost = estimatedFoodCost;
    }

    public BigDecimal getEmergencyReserve() {
        return emergencyReserve;
    }

    public void setEmergencyReserve(BigDecimal emergencyReserve) {
        this.emergencyReserve = emergencyReserve;
    }

    public BigDecimal getTotalEstimatedCost() {
        return totalEstimatedCost;
    }

    public void setTotalEstimatedCost(BigDecimal totalEstimatedCost) {
        this.totalEstimatedCost = totalEstimatedCost;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
