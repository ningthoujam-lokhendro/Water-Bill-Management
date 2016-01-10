create table bill_status (id bigint not null auto_increment, status varchar(255) not null, primary key (id)) ENGINE=InnoDB
create table connection_type (id integer not null auto_increment, rate integer not null, type varchar(10) not null, primary key (id)) ENGINE=InnoDB
create table customer (id bigint not null auto_increment, address varchar(255) not null, creation_date date not null, customer_id varchar(10), first_name varchar(20) not null, phone_landline varchar(15), last_name varchar(20) not null, phone_mobile varchar(15), patta_number varchar(255), ward_number integer not null, connection_type varchar(10) not null, primary key (id)) ENGINE=InnoDB
create table meter (id bigint not null auto_increment, meter_id varchar(255), customer_id bigint not null, primary key (id)) ENGINE=InnoDB
create table meter_reading (id bigint not null auto_increment, reading_date date not null, value integer not null, meter_id varchar(255) not null, primary key (id)) ENGINE=InnoDB
create table payment (id bigint not null auto_increment, date_of_payment datetime not null, paid_amount integer not null, ref_payement_status_code bigint not null, ref_payment_type bigint not null, ref_water_bill bigint not null, primary key (id)) ENGINE=InnoDB
create table payment_status_code (id bigint not null auto_increment, description varchar(255) not null, primary key (id)) ENGINE=InnoDB
create table payment_type (id bigint not null auto_increment, description varchar(255), primary key (id)) ENGINE=InnoDB
create table water_bill (id bigint not null auto_increment, amount_due integer not null, due_date date not null, period_from date not null, period_to date not null, ref_meter_reading bigint not null, ref_bill_status bigint not null, primary key (id)) ENGINE=InnoDB
alter table connection_type add constraint TYPE_IDX unique (type)
alter table customer add constraint PATTA_NUMBER_IDX unique (patta_number)
alter table customer add constraint CUSTOMER_ID_IDX unique (customer_id)
alter table meter add constraint METER_ID_IDX unique (meter_id)
alter table customer add constraint FK_CONNECTION_TYPE foreign key (connection_type) references connection_type (type)
alter table meter add constraint FK_CUSTOMER_ID foreign key (customer_id) references customer (id)
alter table meter_reading add constraint FK_METER_ID foreign key (meter_id) references meter (meter_id)
alter table payment add constraint FK_PAYEMENT_STATUS_CODE foreign key (ref_payement_status_code) references payment_status_code (id)
alter table payment add constraint FK_PAYMENT_TYPE foreign key (ref_payment_type) references payment_type (id)
alter table payment add constraint FK_WATER_BILL foreign key (ref_water_bill) references water_bill (id)
alter table water_bill add constraint FK_METER_READING foreign key (ref_meter_reading) references meter_reading (id)
alter table water_bill add constraint FK_BILL_STATUS foreign key (ref_bill_status) references bill_status (id)

-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE PROCEDURE `gen_meter_reading`()
BEGIN
DECLARE done INT DEFAULT 0;
DECLARE meter_ids varchar(255);
DECLARE meter_cursor CURSOR FOR SELECT meter_id FROM meter;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
OPEN meter_cursor;

read_loop: LOOP
IF done = 1 THEN leave read_loop; END IF;
FETCH meter_cursor INTO meter_ids;
INSERT INTO meter_reading(reading_date,value,ref_meter)
VALUES (curdate(),10,meter_ids);
END loop;
close meter_cursor; 
END