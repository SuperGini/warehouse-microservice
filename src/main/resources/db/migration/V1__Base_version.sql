
CREATE TABLE IF NOT EXISTS price (
    id       BINARY(255) NOT NULL UNIQUE,
    price    DECIMAL(19,2) NOT NULL,
    currency VARCHAR(4)  NOT NULL DEFAULT 'EUR',
    discount DECIMAL(19, 2)       DEFAULT '0.00',
    vat      DECIMAL(19, 2)       DEFAULT '19.00',
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS part_specification (

    id             BINARY(255) NOT NULL UNIQUE,
    specifications VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user (
    id       BINARY(255)  NOT NULL UNIQUE,
    username VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS part (
    id                     BINARY(255)  NOT NULL UNIQUE,
    constructor            VARCHAR(255) DEFAULT NULL,
    created                DATETIME     DEFAULT (UTC_TIMESTAMP()),
    updated                DATETIME     DEFAULT (UTC_TIMESTAMP()),
    manufacturer           VARCHAR(255) DEFAULT NULL,
    part_name              VARCHAR(255) NOT NULL,
    part_number            VARCHAR(255) NOT NULL,
    part_specifications_id BINARY(255)  NOT NULL,
    price_id               BINARY(255)  NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (part_specifications_id) REFERENCES part_specification (id),
    FOREIGN KEY (price_id) REFERENCES price (id)
);

CREATE TABLE IF NOT EXISTS comment (
    id      BINARY(255) NOT NULL UNIQUE,
    part_id BINARY(255) NOT NULL,
    user_id BINARY(255) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (part_id) REFERENCES part (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
);



CREATE TABLE IF NOT EXISTS suplayer (
    id   BINARY(255) NOT NULL UNIQUE,
    name VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS suplayer_parts (

    suplayers_id BINARY(255) NOT NULL,
    parts_id     BINARY(255) NOT NULL,
    FOREIGN KEY (suplayers_id) REFERENCES suplayer (id),
    FOREIGN KEY (parts_id) REFERENCES part (id)
);

CREATE TABLE IF NOT EXISTS car_model (
    id          BINARY(255)  NOT NULL,
    constructor VARCHAR(255) NOT NULL,
    engine_type VARCHAR(255) DEFAULT NULL,
    model       VARCHAR(255) DEFAULT NULL,
    year        DATE         NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS car_model_parts (
    car_models_id BINARY(255) NOT NULL,
    parts_id      BINARY(255) NOT NULL,
    FOREIGN KEY (car_models_id) REFERENCES car_model (id),
    FOREIGN KEY (parts_id) REFERENCES part (id)
);



