package br.com.findyourplace.findyourplaces.integration.geoapify.client;

import br.com.findyourplace.findyourplaces.configuration.GeoapifyProperties;
import br.com.findyourplace.findyourplaces.exceptions.GeocodingUnavailableException;
import br.com.findyourplace.findyourplaces.integration.geoapify.dto.response.GeoapifyResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

@Component
public class GeoapifyClient {

    private final RestClient restClient;
    private final GeoapifyProperties geoapifyProperties;

    public GeoapifyClient(RestClient restClient, GeoapifyProperties geoapifyProperties) {
        this.restClient = restClient;
        this.geoapifyProperties = geoapifyProperties;
    }

    public GeoapifyResponse search(String text) {
        try {
            return restClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/v1/geocode/search")
                            .queryParam("text", text)
                            .queryParam("format", "json")
                            .queryParam("apiKey", geoapifyProperties.apiKey())
                            .build())
                    .retrieve()
                    .body(GeoapifyResponse.class);
        } catch (ResourceAccessException ex) {
            ex.printStackTrace(); // 👈 Isso vai imprimir o erro exato e a causa raiz no console da IDE
            throw new GeocodingUnavailableException();
        }
    }
}
