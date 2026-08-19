package br.com.findyourplace.findyourplaces.service;

import br.com.findyourplace.findyourplaces.exceptions.LocationNotFoundException;
import br.com.findyourplace.findyourplaces.integration.geoapify.client.GeoapifyClient;
import br.com.findyourplace.findyourplaces.service.model.Coordinates;
import org.springframework.stereotype.Service;

@Service
public class GeocodingService {

    private final GeoapifyClient geoapifyClient;

    public GeocodingService(GeoapifyClient geoapifyClient) {
        this.geoapifyClient = geoapifyClient;
    }

    public Coordinates geocode(String city, String state) {

        String searchText = String.format("%s, %s, BRASIL", city, state);
        var response = geoapifyClient.search(searchText);

        if (response == null || response.results() == null || response.results().isEmpty()) {
            throw new LocationNotFoundException(city, state);
        }

        var firstResult = response.results().getFirst();

        if (firstResult.lat() == null || firstResult.lon() == null) {
            throw new LocationNotFoundException(city, state);
        }

        return new Coordinates(firstResult.lat(), firstResult.lon());
    }
}
