package br.com.findyourplace.findyourplaces.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_roles")
public class RoleEntity {

	
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;
    
    @Column(name = "description")
    private String description;
    
    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users = new HashSet<>();
    
	@ManyToMany
	@JoinTable(
	  name = "tb_roles_scopes", 
	  joinColumns = @JoinColumn(name = "role_id"), 
	  inverseJoinColumns = @JoinColumn(name = "scope_id"))
	private Set<ScopeEntity> scopes = new HashSet<ScopeEntity>();

	public RoleEntity() {
		super();
	}

	public RoleEntity(UUID id, String name, String description, Set<UserEntity> users, Set<ScopeEntity> scopes) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.users = users;
		this.scopes = scopes;
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

	public Set<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(Set<UserEntity> users) {
		this.users = users;
	}

	public Set<ScopeEntity> getScopes() {
		return scopes;
	}

	public void setScopes(Set<ScopeEntity> scopes) {
		this.scopes = scopes;
	}


	
	
}
