package br.com.findyourplace.findyourplaces.service.model;

import java.math.BigDecimal;

public record Coordinates(BigDecimal latitude,
                          BigDecimal longitude) {
}
