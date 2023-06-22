CREATE SCHEMA IF NOT EXISTS guide
    AUTHORIZATION root;

CREATE TABLE IF NOT EXISTS guide.town
(
    uuid uuid NOT NULL,
    country_name character varying(255) COLLATE pg_catalog."default",
    dt_create timestamp(3) without time zone,
    dt_update timestamp(3) without time zone,
    number_of_population integer,
    town_name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT town_pkey PRIMARY KEY (uuid)
)
