CREATE TABLE tb_users (
    id UUID PRIMARY KEY,

    name VARCHAR(150) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    phone VARCHAR(30),

    status VARCHAR(30) NOT NULL DEFAULT 'ACTIVE',

    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT uk_users_email UNIQUE (email)
);


CREATE TABLE tb_vehicles (
    id UUID PRIMARY KEY,

    user_id UUID NOT NULL,

    nickname VARCHAR(100),
    brand VARCHAR(100) NOT NULL,
    model VARCHAR(100) NOT NULL,
    year INTEGER,

    fuel_type VARCHAR(30) NOT NULL,

    city_consumption_km_l DECIMAL(6,2),
    highway_consumption_km_l DECIMAL(6,2) NOT NULL,
    tank_capacity_liters DECIMAL(6,2),

    active BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_vehicles_users
        FOREIGN KEY (user_id)
        REFERENCES tb_users(id),

    CONSTRAINT ck_vehicles_highway_consumption
        CHECK (highway_consumption_km_l > 0),

    CONSTRAINT ck_vehicles_city_consumption
        CHECK (
            city_consumption_km_l IS NULL
            OR city_consumption_km_l > 0
        ),

    CONSTRAINT ck_vehicles_tank_capacity
        CHECK (
            tank_capacity_liters IS NULL
            OR tank_capacity_liters > 0
        )
);


CREATE INDEX idx_vehicles_user_id
    ON tb_vehicles(user_id);


CREATE TABLE tb_trips (
    id UUID PRIMARY KEY,

    user_id UUID NOT NULL,
    vehicle_id UUID,

    title VARCHAR(150),

    origin_city VARCHAR(150) NOT NULL,
    origin_state VARCHAR(100) NOT NULL,
    origin_latitude DECIMAL(10,7),
    origin_longitude DECIMAL(10,7),

    destination_city VARCHAR(150) NOT NULL,
    destination_state VARCHAR(100) NOT NULL,
    destination_latitude DECIMAL(10,7),
    destination_longitude DECIMAL(10,7),

    budget DECIMAL(12,2) NOT NULL,
    travelers_count INTEGER NOT NULL DEFAULT 1,

    transport_type VARCHAR(30) NOT NULL,
    status VARCHAR(30) NOT NULL DEFAULT 'DRAFT',

    departure_at TIMESTAMP NOT NULL,
    return_at TIMESTAMP NOT NULL,

    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_trips_users
        FOREIGN KEY (user_id)
        REFERENCES tb_users(id),

    CONSTRAINT fk_trips_vehicles
        FOREIGN KEY (vehicle_id)
        REFERENCES tb_vehicles(id),

    CONSTRAINT ck_trips_budget
        CHECK (budget > 0),

    CONSTRAINT ck_trips_travelers_count
        CHECK (travelers_count >= 1),

    CONSTRAINT ck_trips_period
        CHECK (return_at > departure_at)
);


CREATE INDEX idx_trips_user_id
    ON tb_trips(user_id);

CREATE INDEX idx_trips_vehicle_id
    ON tb_trips(vehicle_id);

CREATE INDEX idx_trips_status
    ON tb_trips(status);