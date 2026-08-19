package br.com.findyourplace.findyourplaces.enums;

public enum RouteDirection {

    OUTBOUND("Rota de ida."),
    RETURN("Rota de volta.");

    private final String description;

    RouteDirection(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
