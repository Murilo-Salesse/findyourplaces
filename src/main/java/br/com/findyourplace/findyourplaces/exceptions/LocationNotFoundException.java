package br.com.findyourplace.findyourplaces.exceptions;

public class LocationNotFoundException extends EntityNotFoundException {

    public LocationNotFoundException(String city, String state) {
        super(
                "Localização não encontrada",
                "Não foi possível encontrar a localização especificada para %s, %s".formatted(city, state)
        );
    }
}
