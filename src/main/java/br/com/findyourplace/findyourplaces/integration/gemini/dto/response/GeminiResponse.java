package br.com.findyourplace.findyourplaces.integration.gemini.dto.response;


import java.util.List;


public record GeminiResponse(String id, String status, List<Step> steps) {

    public String extractText() {
        if (steps == null || steps.isEmpty()) {
            return "";
        }

        return steps.stream()
                .filter(step -> "model_output".equals(step.type()))
                .filter(step -> step.content() != null && !step.content().isEmpty())
                .flatMap(step -> step.content().stream())
                .map(Content::text)
                .findFirst()
                .orElse("");
    }

    public record Step(String type, List<Content> content) {
    }

    public record Content(String text, String type) {
    }
}
