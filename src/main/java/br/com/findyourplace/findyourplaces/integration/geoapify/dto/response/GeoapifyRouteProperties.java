package br.com.findyourplace.findyourplaces.integration.geoapify.dto.response;

import java.math.BigDecimal;

public record GeoapifyRouteProperties(BigDecimal distance,
                                      BigDecimal time,
                                      Boolean toll,
                                      Boolean ferry) {
}
