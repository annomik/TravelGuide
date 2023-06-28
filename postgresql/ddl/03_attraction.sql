CREATE SCHEMA IF NOT EXISTS guide
    AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS guide.attraction
(
    uuid uuid NOT NULL,
    address character varying(255) COLLATE pg_catalog."default",
    dt_create timestamp(3) without time zone,
    dt_update timestamp(3) without time zone,
    name character varying(255) COLLATE pg_catalog."default",
    town_uuid uuid,
    CONSTRAINT attraction_pkey PRIMARY KEY (uuid),
    CONSTRAINT constraintowm FOREIGN KEY (town_uuid)
        REFERENCES guide.town (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
