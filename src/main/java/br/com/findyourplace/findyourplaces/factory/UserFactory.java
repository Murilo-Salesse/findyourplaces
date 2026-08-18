package br.com.findyourplace.findyourplaces.factory;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.findyourplace.findyourplaces.entity.UserEntity;
import br.com.findyourplace.findyourplaces.exceptions.EntityNotFoundException;
import br.com.findyourplace.findyourplaces.repository.RoleRepository;

@Component
public class UserFactory {

    // - Factory: sabe como construir um objeto válido.

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserFactory(RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity create(String name, String email, String password, String phone, String roleName) {
        var role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Role não encontrada",
                        "A role " + roleName + " não foi encontrada."));

        var user = new UserEntity();
        user.setName(name);
        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(password));
        user.setPhone(phone);
        user.getRoles().add(role);

        return user;
    }
}
