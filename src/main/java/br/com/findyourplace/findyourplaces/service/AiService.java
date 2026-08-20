package br.com.findyourplace.findyourplaces.service;

import br.com.findyourplace.findyourplaces.integration.gemini.client.GeminiClient;
import org.springframework.stereotype.Service;

@Service
public class AiService {

    private final GeminiClient geminiClient;

    public AiService(GeminiClient geminiClient) {
        this.geminiClient = geminiClient;
    }

    public String generateRecommendation(String prompt) {

        var response = geminiClient.generateContent(prompt);

        if (response == null) {
            return "Não foi possível gerar a recomendação no momento.";
        }

        return response.extractText();
    }
}
