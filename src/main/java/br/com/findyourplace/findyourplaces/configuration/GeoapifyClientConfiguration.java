package br.com.findyourplace.findyourplaces.configuration;

import br.com.findyourplace.findyourplaces.exceptions.GeocodingUnavailableException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

@Configuration
public class GeoapifyClientConfiguration {

    @Bean
    public RestClient geoapifyRestClient(RestClient.Builder clientBuilder,
                                         GeoapifyProperties geoapifyProperties) {

        return clientBuilder
                .baseUrl(geoapifyProperties.baseUrl())
                .defaultStatusHandler(HttpStatusCode::isError, ((request, response) -> {
                    System.out.println("Status retornado pelo Geoapify: " + response.getStatusCode());
                    System.out.println("URL chamada: " + request.getURI());
                    System.out.println("Corpo do erro: " + new String(response.getBody().readAllBytes()));
                    throw new GeocodingUnavailableException();
                }))
                .build();
    }
}
