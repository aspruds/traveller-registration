CREATE TABLE
    registrations
    (
        registration_id BIGSERIAL NOT NULL,
        registration_uuid UUID NOT NULL,
        date_of_entry DATE,
        is_transit bool NOT NULL,
        date_received TIMESTAMP(6) WITHOUT TIME ZONE,
        PRIMARY KEY (registration_id)
    );