package br.com.findyourplace.findyourplaces.enums;

public enum ExternalProvider {

    GEOAPIFY("GEOAPIFY");

    private final String description;

    ExternalProvider(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
