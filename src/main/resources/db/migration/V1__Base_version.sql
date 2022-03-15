CREATE TABLE IF NOT EXISTS price
(
    id       VARCHAR(36)   NOT NULL UNIQUE,
    price    DECIMAL(19, 2) NOT NULL,
    currency VARCHAR(4)     NOT NULL DEFAULT 'EUR',
    discount DECIMAL(19, 2)          DEFAULT '0.00',
    vat      DECIMAL(19, 2)          DEFAULT '19.00',
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS part_specification
(

    id             VARCHAR(36) NOT NULL UNIQUE,
    specifications VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user
(
    id       VARCHAR(36) NOT NULL UNIQUE,
    username VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS part
(
    id                     VARCHAR(36) NOT NULL UNIQUE,
    created                DATETIME     DEFAULT (UTC_TIMESTAMP()),
    updated                DATETIME     DEFAULT (UTC_TIMESTAMP()),
    manufacturer           VARCHAR(255) DEFAULT NULL,
    part_name              VARCHAR(255) NOT NULL,
    part_number            VARCHAR(255) NOT NULL,
    part_count             DECIMAL(19, 2),
    part_specifications_id VARCHAR(255),
    price_id               VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (part_specifications_id) REFERENCES part_specification (id),
    FOREIGN KEY (price_id) REFERENCES price (id)
);

CREATE TABLE IF NOT EXISTS comment
(
    id      VARCHAR(36) NOT NULL UNIQUE,
    part_id VARCHAR(255) NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (part_id) REFERENCES part (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
);


CREATE TABLE IF NOT EXISTS suplayer
(
    id   VARCHAR(36) NOT NULL UNIQUE,
    name VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS part_count
(
    id                  VARCHAR(36) NOT NULL UNIQUE,
    suplayer_part_count DECIMAL(19, 2),
    part_id             VARCHAR(36) DEFAULT NULL,
    suplayer_id         VARCHAR(36) DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (part_id) REFERENCES part (id),
    FOREIGN KEY (suplayer_id) REFERENCES suplayer (id)
);

CREATE TABLE IF NOT EXISTS suplayer_parts
(

    suplayers_id VARCHAR(36) NOT NULL,
    parts_id     VARCHAR(36) NOT NULL,
    FOREIGN KEY (suplayers_id) REFERENCES suplayer (id),
    FOREIGN KEY (parts_id) REFERENCES part (id)
);

CREATE TABLE IF NOT EXISTS car_model
(
    id          VARCHAR(36) NOT NULL,
    constructor VARCHAR(255) NOT NULL,
    engine_type VARCHAR(255) DEFAULT NULL,
    model       VARCHAR(255) DEFAULT NULL,
    year        DATE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS car_model_parts
(
    car_models_id VARCHAR(36) NOT NULL,
    parts_id      VARCHAR(36) NOT NULL,
    FOREIGN KEY (car_models_id) REFERENCES car_model (id),
    FOREIGN KEY (parts_id) REFERENCES part (id)
);



