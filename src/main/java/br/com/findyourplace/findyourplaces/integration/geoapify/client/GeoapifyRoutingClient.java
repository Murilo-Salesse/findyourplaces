package br.com.findyourplace.findyourplaces.integration.geoapify.client;

import br.com.findyourplace.findyourplaces.configuration.GeoapifyProperties;
import br.com.findyourplace.findyourplaces.exceptions.GeocodingUnavailableException;
import br.com.findyourplace.findyourplaces.integration.geoapify.dto.response.GeoapifyResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

@Component
public class GeoapifyRoutingClient {

    private final RestClient restClient;
    private final GeoapifyProperties geoapifyProperties;

    public GeoapifyRoutingClient(RestClient restClient, GeoapifyProperties geoapifyProperties) {
        this.restClient = restClient;
        this.geoapifyProperties = geoapifyProperties;
    }

    public GeoapifyResponse search(String text) {
        try {
            return null;
        } catch (ResourceAccessException ex) {
            throw new GeocodingUnavailableException();
        }
    }
}
