create table uc_user_basics (user_id bigint generated by default as identity (start with 1), created timestamp, enabled boolean, latest_modified timestamp, usercode varchar(255), username varchar(255), primary key (user_id));
create table uc_user_contact_info (user_contact_id bigint generated by default as identity (start with 1), contact_type varchar(255), created timestamp, enabled boolean not null, latest_modified timestamp, payload varchar(255), user_id bigint, primary key (user_contact_id));
create table uc_user_credential (user_credential_id bigint generated by default as identity (start with 1), credential_type varchar(255), created timestamp, enabled boolean not null, latest_modified timestamp, content varchar(255), user_id bigint, primary key (user_contact_id));