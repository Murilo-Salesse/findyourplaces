package br.com.findyourplace.findyourplaces.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.findyourplace.findyourplaces.enums.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_users")
public class UserEntity {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "password_hash", nullable = false)
	private String passwordHash;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING) 
	private UserStatus status = UserStatus.ACTIVE;
	
	@Column(name = "created_at", nullable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at", nullable = false)
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	public UserEntity() {
		super();
	}

	public UserEntity(UUID id, String name, String email, String passwordHash, String phone, UserStatus status,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.passwordHash = passwordHash;
		this.phone = phone;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}
