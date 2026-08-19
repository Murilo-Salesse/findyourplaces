package br.com.findyourplace.findyourplaces.enums;

public enum TripPlanStatus {

    PENDING("Planejamento aguardando processamento."),
    PROCESSING("Planejamento sendo gerado."),
    COMPLETED("Planejamento concluído."),
    FAILED("Geração falhou");

    private final String description;

    TripPlanStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
