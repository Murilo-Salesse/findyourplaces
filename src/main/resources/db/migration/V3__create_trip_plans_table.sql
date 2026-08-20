CREATE TABLE tb_trip_plans
(
    id                   UUID PRIMARY KEY,
    trip_id              UUID        NOT NULL,
    status               varchar(30) NOT NULL DEFAULT 'PENDING',
    summary              TEXT,
    ai_recommendation    TEXT,
    estimated_total_cost DECIMAL(12, 2),
    remaining_budget     DECIMAL(12, 2),
    generated_at         TIMESTAMP,
    created_at           TIMESTAMP   NOT NULL,
    updated_at           TIMESTAMP   NOT NULL,

    CONSTRAINT fk_trip_plans
        FOREIGN KEY (trip_id)
            REFERENCES tb_trips (id)
);

CREATE INDEX idx_trip_plans_trip_id
    ON tb_trip_plans (trip_id);

CREATE INDEX idx_trip_plans_status
    ON tb_trip_plans (status);