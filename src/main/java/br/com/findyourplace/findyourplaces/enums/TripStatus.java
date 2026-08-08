package br.com.findyourplace.findyourplaces.enums;

public enum TripStatus {

    DRAFT("Viagem criada, aguardando a geração do roteiro"),
    PLANNING("Roteiro da viagem em processo de geração"),
    PLANNED("Roteiro da viagem gerado com sucesso"),
    CANCELLED("Viagem cancelada");

    private final String description;

    TripStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}