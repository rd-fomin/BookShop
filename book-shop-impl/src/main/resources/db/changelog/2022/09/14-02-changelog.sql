-- liquibase formatted sql

--changeset rd.fomin:1
create sequence user_id_seq;

alter table "user"
    alter column id set default nextval('public.user_id_seq'::regclass);

alter sequence user_id_seq owned by "user".id;

--changeset rd.fomin:2
create sequence role_id_seq;

alter table role
    alter column id set default nextval('public.role_id_seq'::regclass);

alter sequence role_id_seq owned by role.id;



