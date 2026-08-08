package br.com.findyourplace.findyourplaces.controller.dto;

public record ResponseAPIDefault<T>(String message,
									T data) {}
