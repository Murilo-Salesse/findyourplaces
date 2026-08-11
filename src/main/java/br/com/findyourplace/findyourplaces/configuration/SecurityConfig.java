package br.com.findyourplace.findyourplaces.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final JwtConfig jwtConfig;
	
	public SecurityConfig(JwtConfig jwtConfig) {
	    this.jwtConfig = jwtConfig;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	    http
	        .csrf(AbstractHttpConfigurer::disable)

	        .sessionManagement(session ->
	            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        )

	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers(
	                "/api/v1/auth/register",
	                "/api/v1/auth/token"
	            ).permitAll()
	            .anyRequest().authenticated()
	        )
	        
	    	.oauth2ResourceServer((oauth2) -> oauth2
					.jwt(Customizer.withDefaults())
				);
	    
	    return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(
			UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);

		return new ProviderManager(authenticationProvider);
	}
		
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	public JwtEncoder jwtEncoder() {

	    JWK jwk = new RSAKey.Builder(jwtConfig.getPublicKey())
	            .privateKey(jwtConfig.getPrivateKey())
	            .build();

	    JWKSource<SecurityContext> jwks =
	            new ImmutableJWKSet<>(new JWKSet(jwk));

	    return new NimbusJwtEncoder(jwks);
	}
	
	@Bean
	public JwtDecoder jwtDecoder() {
	    return NimbusJwtDecoder
	            .withPublicKey(jwtConfig.getPublicKey())
	            .build();
	}
	
	
	//@Bean
	//public UserDetailsService userDetailsService() {
//		@SuppressWarnings("deprecation")
//		UserDetails userDetails = User.withDefaultPasswordEncoder()
//			.username("user.com")
//			.password("12345678")
//			.roles("USER")
//			.build();
	//
//		return new InMemoryUserDetailsManager(userDetails);
	//}
	
}


