package br.com.findyourplace.findyourplaces.service;

import br.com.findyourplace.findyourplaces.entity.TripEntity;
import br.com.findyourplace.findyourplaces.service.model.CalculatedRoute;
import br.com.findyourplace.findyourplaces.service.model.TravelEstimate;
import org.springframework.stereotype.Service;

@Service
public class TripPlanContextService {

    public String build(TripEntity trip, CalculatedRoute route, TravelEstimate estimate) {

        String vehicleInfo = "Não informado / Transporte público";

        if (trip.getVehicle() != null) {
            vehicleInfo = "%s %s (Consumo rodoviário: %s km/l)"
                    .formatted(trip.getVehicle().getBrand(),
                            trip.getVehicle().getModel(),
                            trip.getVehicle().getHighwayConsumptionKmL());
        }

        return """
                Você é um consultor especialista em viagens e roteiros turísticos.
                Analise os dados estruturados da viagem abaixo e forneça um planejamento completo, estruturado e inspirador.
                DADOS DA VIAGEM:
                - Origem: %s - %s
                - Destino: %s - %s
                - Quantidade de viajantes: %d
                - Orçamento Total: R$ %s
                - Meio de Transporte: %s
                - Veículo: %s
                - Data de Ida: %s
                - Data de Retorno: %s
                DADOS DA ROTA:
                - Distância estimada: %s km
                - Tempo de estrada estimado: %d minutos
                - Possui pedágios: %s
                - Possui balsa: %s
                ESTIMATIVAS FINANCEIRAS:
                - Custo estimado de combustível: R$ %s
                - Custo total estimado inicial: R$ %s
                - Orçamento restante para o destino (hospedagem, alimentação e lazer): R$ %s
                INSTRUÇÕES DE RESPOSTA:
                Por favor, formate sua resposta em Markdown contendo:
                1. **Resumo Executivo**: Uma visão geral do planejamento da viagem.
                2. **Dicas de Trajeto**: Cuidados na estrada, paradas recomendadas para descanso/abastecimento considerando a distância.
                3. **Roteiro e Sugestões no Destino**: Atrações imperdíveis e gastronomia respeitando o orçamento restante de R$ %s.
                4. **Dicas Financeiras**: Como otimizar os gastos no destino.
                """.formatted(
                trip.getOriginCity(), trip.getOriginState(),
                trip.getDestinationCity(), trip.getDestinationState(),
                trip.getTravelersCount(),
                trip.getBudget(),
                trip.getTransportType().getDescription(),
                vehicleInfo,
                trip.getDepartureAt(),
                trip.getReturnAt(),
                estimate.distanceKm(),
                estimate.durationMinutes(),
                estimate.hasToll() ? "Sim" : "Não",
                estimate.hasFerry() ? "Sim" : "Não",
                estimate.estimatedFuelCost(),
                estimate.estimatedTotalCost(),
                estimate.remainingBudget(),
                estimate.remainingBudget()
        );
    }
}