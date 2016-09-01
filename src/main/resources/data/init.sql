CREATE table sync_config
 (property_value varchar(256) not null);

 insert into sync_config values('a72995a1705a1a5e2f9c3eed633c93a1');

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

alter table hrmdepartment add fid varchar(256);
ALTER TABLE hrmdepartment ADD CONSTRAINT DEBT_FID_UNIQUE UNIQUE(fid);
ALTER TABLE hrmresource add fid varchar(256);
ALTER TABLE hrmresource ADD CONSTRAINT HRM_FID_UNIQUE UNIQUE(fid);






