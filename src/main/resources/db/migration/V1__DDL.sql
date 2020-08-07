CREATE TABLE
    registrations
    (
        registration_id BIGSERIAL NOT NULL,
        date_of_entry DATE,
        date_received TIMESTAMP(6) WITHOUT TIME ZONE,
        PRIMARY KEY (registration_id)
    );