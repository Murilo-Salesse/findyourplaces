CREATE TABLE tb_travel_estimates
(
    id                           UUID PRIMARY KEY,

    trip_plan_id                 UUID       NOT NULL,

    distance_one_way_km          DECIMAL(10, 2),
    total_distance_km            DECIMAL(10, 2),
    estimated_duration_minutes   INTEGER,

    fuel_consumption_km_l        DECIMAL(6, 2),
    fuel_price_per_liter         DECIMAL(10, 2),
    estimated_fuel_liters        DECIMAL(10, 2),
    estimated_fuel_cost          DECIMAL(12, 2),

    estimated_toll_cost          DECIMAL(12, 2),
    estimated_ticket_cost        DECIMAL(12, 2),
    estimated_accommodation_cost DECIMAL(12, 2),
    estimated_food_cost          DECIMAL(12, 2),

    emergency_reserve            DECIMAL(12, 2),
    total_estimated_cost         DECIMAL(12, 2),

    currency                     VARCHAR(3) NOT NULL DEFAULT 'BRL',

    created_at                   TIMESTAMP  NOT NULL,
    updated_at                   TIMESTAMP  NOT NULL,

    CONSTRAINT fk_travel_estimates_trip_plans
        FOREIGN KEY (trip_plan_id)
            REFERENCES tb_trip_plans (id)
            ON DELETE CASCADE,

    CONSTRAINT uk_travel_estimates_trip_plan_id
        UNIQUE (trip_plan_id)
);

CREATE INDEX idx_travel_estimates_trip_plan_id
    ON tb_travel_estimates (trip_plan_id);