-- liquibase formatted sql

--changeset rd.fomin:1
CREATE TABLE "user"
(
    id       BIGINT       NOT NULL,
    username VARCHAR(20)  NOT NULL,
    email    VARCHAR(50)  NOT NULL,
    password VARCHAR(128) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

--changeset rd.fomin:2
CREATE TABLE role
(
    id   BIGINT       NOT NULL,
    role VARCHAR(50) NOT NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

--changeset rd.fomin:3
CREATE TABLE user_role
(
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT pk_user_role PRIMARY KEY (role_id, user_id)
);

--changeset rd.fomin:4
ALTER TABLE user_role
    ADD CONSTRAINT fk_user_role_on_role FOREIGN KEY (role_id) REFERENCES role (id);

--changeset rd.fomin:5
ALTER TABLE user_role
    ADD CONSTRAINT fk_user_role_on_user FOREIGN KEY (user_id) REFERENCES "user" (id);