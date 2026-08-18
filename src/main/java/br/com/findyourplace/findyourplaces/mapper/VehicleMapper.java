package br.com.findyourplace.findyourplaces.mapper;

import br.com.findyourplace.findyourplaces.controller.dto.response.UpdateVehicleResponseDTO;
import org.springframework.stereotype.Component;

import br.com.findyourplace.findyourplaces.controller.dto.request.CreateVehicleRequestDTO;
import br.com.findyourplace.findyourplaces.controller.dto.request.UpdateVehicleRequestDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.CreateVehicleResponseDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.ListVehiclesResponseDTO;
import br.com.findyourplace.findyourplaces.entity.UserEntity;
import br.com.findyourplace.findyourplaces.entity.VehicleEntity;

@Component
public class VehicleMapper {

    public VehicleEntity toEntity(CreateVehicleRequestDTO dto, UserEntity user) {
        var vehicle = new VehicleEntity();

        vehicle.setUser(user);
        vehicle.setNickname(dto.nickname());
        vehicle.setBrand(dto.brand());
        vehicle.setModel(dto.model());
        vehicle.setYear(dto.year());
        vehicle.setFuelType(dto.fuelType());
        vehicle.setCityConsumptionKmL(dto.cityConsumptionKmL());
        vehicle.setHighwayConsumptionKmL(dto.highwayConsumptionKmL());
        vehicle.setTankCapacityLiters(dto.tankCapacityLiters());

        return vehicle;
    }

	public CreateVehicleResponseDTO toResponse(VehicleEntity vehicle) {
		return new CreateVehicleResponseDTO(
                vehicle.getId(),
                vehicle.getNickname(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getYear(),
                vehicle.getFuelType(),
                vehicle.getCityConsumptionKmL(),
                vehicle.getHighwayConsumptionKmL(),
                vehicle.getTankCapacityLiters(),
                vehicle.getActive(),
                vehicle.getCreatedAt()
        );
    }


	public ListVehiclesResponseDTO toListResponse(VehicleEntity vehicle) {
		
		return new ListVehiclesResponseDTO(vehicle.getId(),
										   vehicle.getNickname(),
										   vehicle.getBrand(),
										   vehicle.getModel(),
										   vehicle.getYear(),
										   vehicle.getFuelType(),
										   vehicle.getCityConsumptionKmL(),
										   vehicle.getHighwayConsumptionKmL(),
										   vehicle.getTankCapacityLiters(),
										   vehicle.getActive());
	}

	public void updateEntity(UpdateVehicleRequestDTO dto, VehicleEntity vehicle) {
		if (dto.nickname() != null) vehicle.setNickname(dto.nickname());
		if (dto.brand() != null) vehicle.setBrand(dto.brand());
		if (dto.model() != null) vehicle.setModel(dto.model());
		if (dto.year() != null) vehicle.setYear(dto.year());
		if (dto.fuelType() != null) vehicle.setFuelType(dto.fuelType());
		if (dto.cityConsumptionKmL() != null) vehicle.setCityConsumptionKmL(dto.cityConsumptionKmL());
		if (dto.highwayConsumptionKmL() != null) vehicle.setHighwayConsumptionKmL(dto.highwayConsumptionKmL());
		if (dto.tankCapacityLiters() != null) vehicle.setTankCapacityLiters(dto.tankCapacityLiters());
	}

	public UpdateVehicleResponseDTO toUpdateResponse(VehicleEntity vehicle) {

		return new UpdateVehicleResponseDTO(
				vehicle.getId(),
				vehicle.getNickname(),
				vehicle.getBrand(),
				vehicle.getModel(),
				vehicle.getYear(),
				vehicle.getFuelType(),
				vehicle.getCityConsumptionKmL(),
				vehicle.getHighwayConsumptionKmL(),
				vehicle.getTankCapacityLiters(),
				vehicle.getActive(),
				vehicle.getUpdatedAt()
		);
	}
}
