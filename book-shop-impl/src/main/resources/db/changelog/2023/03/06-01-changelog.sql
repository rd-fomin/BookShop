-- liquibase formatted sql

--changeset rd.fomin:1
create sequence book_id_seq;

alter table "book"
    alter column id set default nextval('public.book_id_seq'::regclass);

alter sequence book_id_seq owned by book.id;

--changeset rd.fomin:2
create sequence author_id_seq;

alter table author
    alter column id set default nextval('public.author_id_seq'::regclass);

alter sequence author_id_seq owned by author.id;

