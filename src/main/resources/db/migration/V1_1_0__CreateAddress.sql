CREATE TABLE address
(
    country     VARCHAR NOT NULL,
    city        VARCHAR NOT NULL,
    street      VARCHAR NOT NULL,
    postal_code VARCHAR NOT NULL,
    profile_id  BIGINT,
    PRIMARY KEY (profile_id),
    FOREIGN KEY (profile_id) REFERENCES profile (id)
);
