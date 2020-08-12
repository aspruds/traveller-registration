CREATE TABLE registrations
(
    registration_id BIGSERIAL NOT NULL,
    registration_uuid UUID NOT NULL,
    date_of_entry DATE NOT NULL,
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

CREATE TABLE sex_types (
    type_id BIGSERIAL NOT NULL,
    code TEXT NOT NULL,
    name TEXT NOT NULL,
    PRIMARY KEY(type_id)
);

CREATE TABLE identity_document_types (
    type_id BIGSERIAL NOT NULL,
    code TEXT NOT NULL,
    name TEXT NOT NULL,
    PRIMARY KEY(type_id)
);

CREATE TABLE addresses
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

CREATE TABLE travellers (
    traveller_id BIGSERIAL NOT NULL,
    registration_id BIGINT NOT NULL REFERENCES registrations(registration_id),
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    national_id_number TEXT,
    date_of_birth DATE,
    age INT NOT NULL,
    seat TEXT,
    foreigner_needed_for_business BOOLEAN NOT NULL,
    sex_type_id BIGINT NOT NULL REFERENCES sex_types(type_id),
    PRIMARY KEY(traveller_id)
);

CREATE TABLE recent_countries (
    recent_country_id BIGSERIAL NOT NULL,
    traveller_id BIGINT NOT NULL REFERENCES travellers(traveller_id),
    date_of_exit DATE NOT NULL,
    country_id BIGINT NOT NULL REFERENCES countries(country_id),
    PRIMARY KEY(recent_country_id)
);

CREATE TABLE contact_information (
    contact_information_id BIGSERIAL NOT NULL,
    traveller_id BIGINT NOT NULL REFERENCES travellers(traveller_id),
    email TEXT NOT NULL,
    phone_country_code TEXT NOT NULL,
    phone TEXT NOT NULL,
    PRIMARY KEY(contact_information_id)
);

CREATE TABLE identity_documents (
    identity_document_id BIGSERIAL NOT NULL,
    traveller_id BIGINT NOT NULL REFERENCES travellers(traveller_id),
    identity_document_type_id BIGINT NOT NULL REFERENCES identity_document_types(type_id),
    issuing_country_id BIGINT NOT NULL REFERENCES countries(country_id),
    document_number TEXT NOT NULL,
    PRIMARY KEY(identity_document_id)
);

INSERT INTO countries(code, name, isolation_required) VALUES ('LV', 'Latvia', false);
INSERT INTO carrier_types(code, name) VALUES ('PLANE', 'Plane');

INSERT INTO sex_types(code, name) VALUES ('MALE', 'Male');
INSERT INTO sex_types(code, name) VALUES ('FEMALE', 'Female');
INSERT INTO sex_types(code, name) VALUES ('OTHER', 'Other');

INSERT INTO identity_document_types(code, name) VALUES ('PASSPORT', 'Passport');
INSERT INTO identity_document_types(code, name) VALUES ('ID_CARD', 'ID Card');
INSERT INTO identity_document_types(code, name) VALUES ('OTHER', 'Other');