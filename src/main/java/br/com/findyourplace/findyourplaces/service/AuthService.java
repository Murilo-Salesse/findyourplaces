package br.com.findyourplace.findyourplaces.service;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import br.com.findyourplace.findyourplaces.configuration.JwtConfig;
import br.com.findyourplace.findyourplaces.controller.dto.response.LoginResponseDTO;
import br.com.findyourplace.findyourplaces.entity.RoleEntity;
import br.com.findyourplace.findyourplaces.repository.UserRepository;

@Service
public class AuthService {

	private final UserRepository userRepository;
	private final JwtEncoder jwtEncoder;
	private final JwtConfig jwtConfig;

	public AuthService(JwtEncoder jwtEncoder, JwtConfig jwtConfig, UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
		this.jwtEncoder = jwtEncoder;
		this.jwtConfig = jwtConfig;
	}

	public LoginResponseDTO generateToken(Authentication authentication) {

		Instant now = Instant.now();
		long expiresIn = jwtConfig.getExpiresIn();

		var user = this.userRepository.findByEmail(authentication.getName())
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

		var roles = user.getRoles().stream().map(RoleEntity::getName).collect(Collectors.toList());

		var scopes = user.getRoles().stream().flatMap(role -> role.getScopes().stream()).map(scope -> scope.getName())
				.distinct().toList();

		JwtClaimsSet claims = JwtClaimsSet.builder().issuer(jwtConfig.getIssuer()).subject(user.getId().toString())
				.claim("email", user.getEmail()).claim("roles", roles).claim("scope", String.join(" ", scopes))
				.issuedAt(now).expiresAt(now.plusSeconds(expiresIn)).build();

		String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

		return new LoginResponseDTO(token, expiresIn);
	}
}
