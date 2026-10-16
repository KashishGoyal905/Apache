-- Application domain role and schema (used by the Spring Boot backend).
-- CREATEDB lets Hibernate validate/update its own schema objects.
CREATE ROLE app WITH LOGIN PASSWORD 'app' CREATEDB;

CREATE SCHEMA IF NOT EXISTS app AUTHORIZATION app;

ALTER ROLE app SET search_path TO app;

-- Keep the shared public schema locked down.
REVOKE ALL ON SCHEMA public FROM PUBLIC;
GRANT USAGE ON SCHEMA public TO app;
GRANT ALL ON SCHEMA app TO app;
