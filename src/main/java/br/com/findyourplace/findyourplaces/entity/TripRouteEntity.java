package br.com.findyourplace.findyourplaces.entity;

import br.com.findyourplace.findyourplaces.enums.ExternalProvider;
import br.com.findyourplace.findyourplaces.enums.RouteDirection;
import br.com.findyourplace.findyourplaces.enums.RouteType;
import br.com.findyourplace.findyourplaces.enums.RoutingMode;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;
import com.fasterxml.jackson.databind.JsonNode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "tb_trip_routes")
public class TripRouteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "trip_plan_id", nullable = false)
    private TripPlanEntity tripPlan;

    @Enumerated(EnumType.STRING)
    @Column(name = "direction", nullable = false)
    private RouteDirection direction;

    @Enumerated(EnumType.STRING)
    @Column(name = "external_provider", nullable = false)
    private ExternalProvider externalProvider;

    @Enumerated(EnumType.STRING)
    @Column(name = "transport_mode", nullable = false)
    private RoutingMode transportMode;

    @Enumerated(EnumType.STRING)
    @Column(name = "route_type", nullable = false)
    private RouteType routeType = RouteType.BALANCED;

    @Column(name = "distance_meters", nullable = false)
    private BigDecimal distanceMeters;

    @Column(name = "duration_seconds", nullable = false)
    private Integer durationSeconds;

    @Column(name = "has_tolls", nullable = false)
    private Boolean hasTolls = false;

    @Column(name = "has_ferry", nullable = false)
    private Boolean hasFerry = false;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "geometry", columnDefinition = "jsonb", nullable = false)
    private Map<String, Object> geometry;

    @Column(name = "calculated_at", nullable = false)
    private LocalDateTime calculatedAt;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public TripRouteEntity() {
    }

    public TripRouteEntity(UUID id, TripPlanEntity tripPlan, RouteDirection direction, ExternalProvider externalProvider, RoutingMode transportMode, RouteType routeType, BigDecimal distanceMeters, Integer durationSeconds, Boolean hasTolls, Boolean hasFerry, Map<String, Object> geometry, LocalDateTime calculatedAt, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.tripPlan = tripPlan;
        this.direction = direction;
        this.externalProvider = externalProvider;
        this.transportMode = transportMode;
        this.routeType = routeType;
        this.distanceMeters = distanceMeters;
        this.durationSeconds = durationSeconds;
        this.hasTolls = hasTolls;
        this.hasFerry = hasFerry;
        this.geometry = geometry;
        this.calculatedAt = calculatedAt;
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

    public RouteDirection getDirection() {
        return direction;
    }

    public void setDirection(RouteDirection direction) {
        this.direction = direction;
    }

    public ExternalProvider getExternalProvider() {
        return externalProvider;
    }

    public void setExternalProvider(ExternalProvider externalProvider) {
        this.externalProvider = externalProvider;
    }

    public RoutingMode getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(RoutingMode transportMode) {
        this.transportMode = transportMode;
    }

    public RouteType getRouteType() {
        return routeType;
    }

    public void setRouteType(RouteType routeType) {
        this.routeType = routeType;
    }

    public BigDecimal getDistanceMeters() {
        return distanceMeters;
    }

    public void setDistanceMeters(BigDecimal distanceMeters) {
        this.distanceMeters = distanceMeters;
    }

    public Integer getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(Integer durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public Boolean getHasTolls() {
        return hasTolls;
    }

    public void setHasTolls(Boolean hasTolls) {
        this.hasTolls = hasTolls;
    }

    public Boolean getHasFerry() {
        return hasFerry;
    }

    public void setHasFerry(Boolean hasFerry) {
        this.hasFerry = hasFerry;
    }

    public Map<String, Object> getGeometry() {
        return geometry;
    }

    public void setGeometry(Map<String, Object> geometry) {
        this.geometry = geometry;
    }

    public LocalDateTime getCalculatedAt() {
        return calculatedAt;
    }

    public void setCalculatedAt(LocalDateTime calculatedAt) {
        this.calculatedAt = calculatedAt;
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
