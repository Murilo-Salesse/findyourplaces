package br.com.findyourplace.findyourplaces.integration.gemini.dto.request;

public record GeminiRequest(String model, String input) {

    public static GeminiRequest of(String model, String prompt) {
        return new GeminiRequest(model, prompt);
    }
}
