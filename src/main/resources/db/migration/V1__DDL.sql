CREATE TABLE
    registrations
    (
        registration_id BIGSERIAL NOT NULL,
        registration_uuid UUID NOT NULL,
        date_of_entry DATE NOT NULL ,
        is_transit BOOL NOT NULL,
        date_received TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
        PRIMARY KEY (registration_id)
    );

CREATE TABLE countries (
 country_id BIGSERIAL NOT NULL,
 code TEXT NOT NULL,
 name TEXT NOT NULL,
 isolation_required BOOL NOT NULL,
 PRIMARY KEY(country_id)
);

CREATE TABLE carrier_types (
 type_id BIGSERIAL NOT NULL,
 code TEXT NOT NULL,
 name TEXT NOT NULL,
 PRIMARY KEY(type_id)
);

CREATE TABLE
    addresses
    (
        address_id BIGSERIAL NOT NULL,
        registration_id BIGINT NOT NULL REFERENCES registrations("registration_id") ON DELETE CASCADE,
        country_id BIGINT NOT NULL REFERENCES countries(country_id) ON DELETE CASCADE,
        zip TEXT,
        province TEXT,
        city TEXT,
        district TEXT,
        village TEXT,
        street TEXT,
        house TEXT,
        flat TEXT,
        PRIMARY KEY (address_id)
    );

CREATE TABLE transport_details (
 transport_details_id BIGSERIAL NOT NULL,
 registration_id BIGINT NOT NULL REFERENCES registrations(registration_id),
 carrier_type_id BIGINT NOT NULL REFERENCES carrier_types(type_id),
 flight_number TEXT,
 flight_date DATE,
 PRIMARY KEY(transport_details_id)
);

INSERT INTO countries(code, name, isolation_required) VALUES ('LV', 'Latvia', false);
INSERT INTO carrier_types(code, name) VALUES ('PLANE', 'Plane');