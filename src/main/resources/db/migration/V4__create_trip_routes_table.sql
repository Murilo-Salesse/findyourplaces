CREATE TABLE tb_trip_routes
(
    id                UUID PRIMARY KEY,
    trip_plan_id      UUID           NOT NULL,
    direction         varchar(20)    NOT NULL,
    external_provider varchar(50)    NOT NULL,
    transport_mode    varchar(30)    NOT NULL,
    route_type        varchar(30)    NOT NULL DEFAULT 'BALANCED',
    distance_meters   DECIMAL(12, 2) NOT NULL,
    duration_seconds  INTEGER        NOT NULL,
    has_tolls         BOOLEAN        NOT NULL DEFAULT FALSE,
    has_ferry         BOOLEAN        NOT NULL DEFAULT FALSE,
    geometry          JSONB          NOT NULL,
    calculated_at     TIMESTAMP      NOT NULL,
    created_at        TIMESTAMP      NOT NULL,
    updated_at        TIMESTAMP      NOT NULL,

    CONSTRAINT fk_trip_plan
        FOREIGN KEY (trip_plan_id)
            REFERENCES tb_trip_plans (id)
);

CREATE INDEX idx_trip_routes_trip_plan_id
    ON tb_trip_routes (trip_plan_id);

CREATE UNIQUE INDEX uk_trip_routes_plan_direction
    ON tb_trip_routes (trip_plan_id, direction);