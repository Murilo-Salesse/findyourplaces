package br.com.findyourplace.findyourplaces.controller.dto.response;

public record ResponseAPIDefault<T>(String message,
									T data) {}
