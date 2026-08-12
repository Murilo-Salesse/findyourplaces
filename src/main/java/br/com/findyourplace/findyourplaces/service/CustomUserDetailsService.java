package br.com.findyourplace.findyourplaces.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.findyourplace.findyourplaces.entity.RoleEntity;
import br.com.findyourplace.findyourplaces.entity.UserEntity;
import br.com.findyourplace.findyourplaces.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	private final UserRepository userRepository;
	
	public CustomUserDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		 UserEntity user = this.userRepository.findByEmail(username)
				 .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
		 
		 String[] roles = user.getRoles()
			        .stream()
			        .map(RoleEntity::getName)
			        .toArray(String[]::new);
	    

		    return User.builder()
		            .username(user.getEmail())
		            .password(user.getPasswordHash())
		            .disabled(!user.isActive())
		            .roles(roles)
		            .build();
	}

}
