package br.com.findyourplace.findyourplaces.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_vehicles")
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "year")
    private Integer year;

    @Column(name = "fuel_type", nullable = false)
    private String fuelType;

    @Column(name = "city_consumption_km_l")
    private BigDecimal cityConsumptionKmL;

    @Column(name = "highway_consumption_km_l", nullable = false)
    private BigDecimal highwayConsumptionKmL;

    @Column(name = "tank_capacity_liters")
    private BigDecimal tankCapacityLiters;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

	public VehicleEntity() {
		super();
	}
    
	public VehicleEntity(UUID id, UserEntity user, String nickname, String brand, String model, Integer year,
			String fuelType, BigDecimal cityConsumptionKmL, BigDecimal highwayConsumptionKmL,
			BigDecimal tankCapacityLiters, Boolean active, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.user = user;
		this.nickname = nickname;
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.fuelType = fuelType;
		this.cityConsumptionKmL = cityConsumptionKmL;
		this.highwayConsumptionKmL = highwayConsumptionKmL;
		this.tankCapacityLiters = tankCapacityLiters;
		this.active = active;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public BigDecimal getCityConsumptionKmL() {
		return cityConsumptionKmL;
	}

	public void setCityConsumptionKmL(BigDecimal cityConsumptionKmL) {
		this.cityConsumptionKmL = cityConsumptionKmL;
	}

	public BigDecimal getHighwayConsumptionKmL() {
		return highwayConsumptionKmL;
	}

	public void setHighwayConsumptionKmL(BigDecimal highwayConsumptionKmL) {
		this.highwayConsumptionKmL = highwayConsumptionKmL;
	}

	public BigDecimal getTankCapacityLiters() {
		return tankCapacityLiters;
	}

	public void setTankCapacityLiters(BigDecimal tankCapacityLiters) {
		this.tankCapacityLiters = tankCapacityLiters;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
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
