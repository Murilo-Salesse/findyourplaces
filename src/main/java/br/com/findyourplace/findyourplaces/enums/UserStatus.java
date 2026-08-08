package br.com.findyourplace.findyourplaces.enums;

public enum UserStatus {

    ACTIVE("Usuário Ativo"),
	INACTIVE("Usuário Inativo");

	  private final String description;

	  UserStatus(String description) {
	        this.description = description;
	    }

	    public String getDescription() {
	        return description;
	    }
}
