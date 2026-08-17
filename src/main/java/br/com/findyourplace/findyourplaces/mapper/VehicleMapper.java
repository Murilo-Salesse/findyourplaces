package br.com.findyourplace.findyourplaces.mapper;

import org.springframework.stereotype.Component;

import br.com.findyourplace.findyourplaces.controller.dto.request.CreateVehicleRequestDTO;
import br.com.findyourplace.findyourplaces.controller.dto.response.CreateVehiclesResponseDTO;
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

    public CreateVehiclesResponseDTO toResponse(VehicleEntity vehicle) {
        return new CreateVehiclesResponseDTO(
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
    
    
	public ListVehiclesResponseDTO toListInfosVehiclesReponse(VehicleEntity vehicle) {
		
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
	
	public ListVehiclesResponseDTO toListById(VehicleEntity vehicle) {
		
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
}
