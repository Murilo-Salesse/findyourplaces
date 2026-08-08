package br.com.findyourplace.findyourplaces.exceptions;

import java.util.List;

import br.com.findyourplace.findyourplaces.controller.dto.response.InvalidParamsResponseDTO;

public record ExceptionResponse(String type,
                                String title,
                                String detail,
                                Integer status,
                                List<InvalidParamsResponseDTO> invalidParams) {}
