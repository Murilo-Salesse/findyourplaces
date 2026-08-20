package br.com.findyourplace.findyourplaces.exceptions;

import org.springframework.http.HttpStatus;

public class RoutingUnavailableException extends FindYourPlaceException {

    public RoutingUnavailableException() {
        super(
                "Serviço de cálculo de rotas indisponível",
                "Não foi possível calcular a rota para a viagem neste momento.",
                HttpStatus.SERVICE_UNAVAILABLE
        );
    }
}