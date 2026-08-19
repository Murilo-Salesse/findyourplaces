package br.com.findyourplace.findyourplaces.integration.geoapify.dto.request;

import java.math.BigDecimal;

public record GeoapifyResult(BigDecimal lat,
                             BigDecimal lon,
                             String formatted,
                             String city,
                             String state,
                             String country) {
}
