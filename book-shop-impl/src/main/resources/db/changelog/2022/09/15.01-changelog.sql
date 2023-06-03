-- liquibase formatted sql

--changeset rd.fomin:1
alter table "user"
    add unique (username);

alter table "user"
    add unique (email);