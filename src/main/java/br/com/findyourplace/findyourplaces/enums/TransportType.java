package br.com.findyourplace.findyourplaces.enums;

public enum TransportType {

    CAR("Carro"),
    MOTORCYCLE("Moto"),
    BUS("Ônibus"),
    OTHER("Outro");
    
    private final String description;

    TransportType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
