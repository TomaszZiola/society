CREATE SEQUENCE public.profile_id_seq INCREMENT 1 START WITH 1 MINVALUE 1;

CREATE TABLE profile
(
    id          BIGINT DEFAULT nextval('profile_id_seq'),
    name        VARCHAR NOT NULL,
    second_name VARCHAR NOT NULL,
    PRIMARY KEY (id)
);
