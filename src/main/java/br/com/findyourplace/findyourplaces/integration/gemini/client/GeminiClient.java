package br.com.findyourplace.findyourplaces.integration.gemini.client;

import br.com.findyourplace.findyourplaces.configuration.GeminiProperties;
import br.com.findyourplace.findyourplaces.integration.gemini.dto.request.GeminiRequest;
import br.com.findyourplace.findyourplaces.integration.gemini.dto.response.GeminiResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class GeminiClient {

    private final RestClient restClient;
    private final GeminiProperties properties;

    public GeminiClient(RestClient restClient, GeminiProperties properties) {
        this.restClient = restClient;
        this.properties = properties;
    }

    public GeminiResponse generateContent(String prompt) {

        String uri = properties.baseUrl() + "/v1beta/interactions";

        return restClient.post()
                .uri(uri)
                .header("x-goog-api-key", properties.apiKey())
                .contentType(MediaType.APPLICATION_JSON)
                .body(GeminiRequest.of(properties.model(), prompt))
                .retrieve()
                .body(GeminiResponse.class);
    }
}
