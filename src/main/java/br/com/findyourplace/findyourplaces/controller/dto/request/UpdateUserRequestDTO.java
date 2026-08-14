package br.com.findyourplace.findyourplaces.controller.dto.request;

import org.hibernate.validator.constraints.Length;

public record UpdateUserRequestDTO(@Length(min = 3, max = 20) String name,
		   						   String phone) {}
