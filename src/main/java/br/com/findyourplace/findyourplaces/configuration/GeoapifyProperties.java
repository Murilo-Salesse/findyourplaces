package br.com.findyourplace.findyourplaces.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "geoapify")
public record GeoapifyProperties(String baseUrl,
                                 String apiKey) {
}
