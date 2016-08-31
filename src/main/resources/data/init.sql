CREATE table sync_config
(id number(32,0) PRIMARY KEY,
 property_name VARCHAR(32) not null,
 property_value varchar(256) not null,
 status NUMBER(1));

 create table synclink
(id number(32,0) primary key,
origin_id number(32,0) not null,
target_id number(32,0) not null,
table_name varchar(64) not null,
sync_type varchar(32) not null,
status number(10,0) not null);

CREATE SEQUENCE synclink_id minvalue 1 start with 1 increment by 1 cache 20;
CREATE OR REPLACE TRIGGER SYNCLINK_TRIGGER before insert on synclink for each row begin select synclink_id.nextval INTO :new.id from dual; end;
ALTER TRIGGER SYNCLINK_TRIGGER ENABLE;



