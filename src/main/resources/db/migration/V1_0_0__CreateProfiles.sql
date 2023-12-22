CREATE SEQUENCE profile_id_seq START 1;

CREATE TABLE profile
(
    id          BIGINT DEFAULT nextval('profile_id_seq'),
    name        VARCHAR NOT NULL,
    second_name VARCHAR NOT NULL,
    PRIMARY KEY (id)
);
