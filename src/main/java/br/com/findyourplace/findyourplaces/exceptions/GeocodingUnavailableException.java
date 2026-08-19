package br.com.findyourplace.findyourplaces.exceptions;

import org.springframework.http.HttpStatus;

public class GeocodingUnavailableException extends FindYourPlaceException {

    public GeocodingUnavailableException() {
        super(
                "Serviço de geocodificação indisponível",
                "Não foi possível consultar as coordenadas neste momento.",
                HttpStatus.SERVICE_UNAVAILABLE
        );
    }
}
