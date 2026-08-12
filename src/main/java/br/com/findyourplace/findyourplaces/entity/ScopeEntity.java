package br.com.findyourplace.findyourplaces.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_scopes")
public class ScopeEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(name = "name", length = 100, nullable = false, unique = true)
    private String name;
    
    @Column(name = "description")
    private String description;
    
    @ManyToMany(mappedBy = "scopes")
    private Set<RoleEntity> roles = new HashSet<RoleEntity>();
    
	public ScopeEntity() {
		super();
	}


	public ScopeEntity(UUID id, String name, String description, Set<RoleEntity> roles) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.roles = roles;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}
    
    
}
