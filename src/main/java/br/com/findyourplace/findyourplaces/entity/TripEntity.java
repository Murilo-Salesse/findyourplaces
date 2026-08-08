package br.com.findyourplace.findyourplaces.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.findyourplace.findyourplaces.enums.TransportType;
import br.com.findyourplace.findyourplaces.enums.TripStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_trips")
public class TripEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicle;
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "origin_city", nullable = false)
    private String originCity;
    
    @Column(name = "origin_state", nullable = false)
    private String originState;
    
    @Column(name = "origin_latitude")
    private BigDecimal originLatitude;
    
    @Column(name = "origin_longitude")
    private BigDecimal originLongitude;
    
    @Column(name = "destination_city", nullable = false)
    private String destinationCity;
    
    @Column(name = "destination_state", nullable = false)
    private String destinationState;
    
    @Column(name = "destination_latitude")
    private BigDecimal destinationLatitude;
    
    @Column(name = "destination_longitude")
    private BigDecimal destinationLongitude;
    
    @Column(name = "budget", nullable = false)
    private BigDecimal budget;
    
    @Column(name = "travelers_count", nullable = false)
    private Integer travelersCount = 1;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "transport_type", nullable = false)
    private TransportType transportType;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TripStatus status = TripStatus.DRAFT;
    
	@Column(name = "departure_at", nullable = false)
	private LocalDateTime departureAt;
	
	@Column(name = "return_at", nullable = false)
	private LocalDateTime returnAt;
    
	@Column(name = "created_at", nullable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at", nullable = false)
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	public TripEntity(UUID id, UserEntity user, VehicleEntity vehicle, String title, String originCity,
			String originState, BigDecimal originLatitude, BigDecimal originLongitude, String destinationCity,
			String destinationState, BigDecimal destinationLatitude, BigDecimal destinationLongitude, BigDecimal budget,
			Integer travelersCount, TransportType transportType, TripStatus status, LocalDateTime departureAt,
			LocalDateTime returnAt, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.user = user;
		this.vehicle = vehicle;
		this.title = title;
		this.originCity = originCity;
		this.originState = originState;
		this.originLatitude = originLatitude;
		this.originLongitude = originLongitude;
		this.destinationCity = destinationCity;
		this.destinationState = destinationState;
		this.destinationLatitude = destinationLatitude;
		this.destinationLongitude = destinationLongitude;
		this.budget = budget;
		this.travelersCount = travelersCount;
		this.transportType = transportType;
		this.status = status;
		this.departureAt = departureAt;
		this.returnAt = returnAt;
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

	public VehicleEntity getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleEntity vehicle) {
		this.vehicle = vehicle;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginCity() {
		return originCity;
	}

	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}

	public String getOriginState() {
		return originState;
	}

	public void setOriginState(String originState) {
		this.originState = originState;
	}

	public BigDecimal getOriginLatitude() {
		return originLatitude;
	}

	public void setOriginLatitude(BigDecimal originLatitude) {
		this.originLatitude = originLatitude;
	}

	public BigDecimal getOriginLongitude() {
		return originLongitude;
	}

	public void setOriginLongitude(BigDecimal originLongitude) {
		this.originLongitude = originLongitude;
	}

	public String getDestinationCity() {
		return destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	public String getDestinationState() {
		return destinationState;
	}

	public void setDestinationState(String destinationState) {
		this.destinationState = destinationState;
	}

	public BigDecimal getDestinationLatitude() {
		return destinationLatitude;
	}

	public void setDestinationLatitude(BigDecimal destinationLatitude) {
		this.destinationLatitude = destinationLatitude;
	}

	public BigDecimal getDestinationLongitude() {
		return destinationLongitude;
	}

	public void setDestinationLongitude(BigDecimal destinationLongitude) {
		this.destinationLongitude = destinationLongitude;
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}

	public Integer getTravelersCount() {
		return travelersCount;
	}

	public void setTravelersCount(Integer travelersCount) {
		this.travelersCount = travelersCount;
	}

	public TransportType getTransportType() {
		return transportType;
	}

	public void setTransportType(TransportType transportType) {
		this.transportType = transportType;
	}

	public TripStatus getStatus() {
		return status;
	}

	public void setStatus(TripStatus status) {
		this.status = status;
	}

	public LocalDateTime getDepartureAt() {
		return departureAt;
	}

	public void setDepartureAt(LocalDateTime departureAt) {
		this.departureAt = departureAt;
	}

	public LocalDateTime getReturnAt() {
		return returnAt;
	}

	public void setReturnAt(LocalDateTime returnAt) {
		this.returnAt = returnAt;
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
