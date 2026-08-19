package br.com.findyourplace.findyourplaces.integration.geoapify.dto.response;

import br.com.findyourplace.findyourplaces.integration.geoapify.dto.request.GeoapifyResult;

import java.util.List;

public record GeoapifyResponse(List<GeoapifyResult> results) {
}
