package br.com.findyourplace.findyourplaces.enums;

public enum RouteType {

    BALANCED("Equilíbrio entre tempo, custo e distância."),
    SHORT("Prioriza menor distância."),
    LESS_MANEUVERS("Reduz a quantidade de manobras.");

    private final String description;

    RouteType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
