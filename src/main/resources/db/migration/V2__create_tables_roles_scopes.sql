CREATE TABLE tb_roles (
    id UUID PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255)
);

CREATE TABLE tb_scopes (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(255)
);

CREATE TABLE tb_users_roles (
    user_id UUID NOT NULL,
    role_id UUID NOT NULL,

    PRIMARY KEY (user_id, role_id),

    CONSTRAINT fk_users_roles_user
        FOREIGN KEY (user_id)
        REFERENCES tb_users(id),

    CONSTRAINT fk_users_roles_role
        FOREIGN KEY (role_id)
        REFERENCES tb_roles(id)
);

CREATE TABLE tb_roles_scopes (
    role_id UUID NOT NULL,
    scope_id UUID NOT NULL,

    PRIMARY KEY (role_id, scope_id),

    CONSTRAINT fk_roles_scopes_role
        FOREIGN KEY (role_id)
        REFERENCES tb_roles(id),

    CONSTRAINT fk_roles_scopes_scope
        FOREIGN KEY (scope_id)
        REFERENCES tb_scopes(id)
);