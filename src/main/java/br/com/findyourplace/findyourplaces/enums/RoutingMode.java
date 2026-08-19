package br.com.findyourplace.findyourplaces.enums;

public enum RoutingMode {

    DRIVE("drive"),
    MOTORCYCLE("motorcycle"),
    TRANSIT("transit");

    private final String description;

    RoutingMode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
