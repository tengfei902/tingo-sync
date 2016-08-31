CREATE table sync_config
(id number(32,0) PRIMARY KEY,
 property_name VARCHAR(32) not null,
 property_value varchar(256) not null,
 status NUMBER(1));

