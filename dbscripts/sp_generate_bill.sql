-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- amount_due = Rental + unit * type + previous_due

-- Rental = from connection_type
-- unit = from meter_reading
--        - get meterId from meter using customerId
--        - get unit from meter_reading using meterId.
-- type = from customer
-- previous_due = water_bill -> due -> status.
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE PROCEDURE `generate_bill` ()
BEGIN
DECLARE no_more_rows BOOLEAN;
DECLARE no_more BOOLEAN;
DECLARE patta,conn_type varchar(255);
DECLARE cus_id INT;
DECLARE home_rate, home_rental, commercial_rate, commercial_rental INT;
DECLARE local_rental,local_rate INT DEFAULT 0;
DECLARE no_unit,reading_id INT;
DECLARE to_period,from_period,due_date DATE;
DECLARE amount_due,net_due,prev_due INT DEFAULT 0;

DECLARE customer_cursor CURSOR FOR SELECT id,patta_number,connection_type FROM customer;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET no_more_rows = TRUE;

SELECT rate,rental INTO home_rate,home_rental FROM connection_type where type='HOME';
SELECT rate,rental INTO commercial_rate,commercial_rental FROM connection_type where type='COMMERCIAL';

SET to_period = LAST_DAY(CURDATE());
SET due_date = ADDDATE(to_period,INTERVAL 15 DAY);
SET from_period = DATE_FORMAT(CURDATE(), '%Y-%m-01');

call generate_due();
call generate_meter_reading_temp();

OPEN customer_cursor;

billing_loop : LOOP
	
	FETCH customer_cursor INTO cus_id, patta, conn_type;
	
	IF no_more_rows THEN
		CLOSE customer_cursor;
		LEAVE billing_loop;
	END IF;
	
	IF conn_type = 'HOME' THEN
		SET local_rate=home_rate;
		SET local_rental=home_rental;
	ELSEIF conn_type = 'COMMERCIAL' THEN
		SET local_rental=commercial_rental;
		SET local_rate=commercial_rate;
	END IF;
	
	BEGIN
		DECLARE CONTINUE HANDLER FOR NOT FOUND SET no_more = TRUE;
		SELECT IFNULL(unit,0), id into no_unit,reading_id FROM METER_READING_CUR_MONTH where patta_number = patta;
		SELECT IFNULL(net_bill_amount,0) INTO prev_due FROM DUE_REPORT WHERE customer_id = cus_id;
	END;
	
	SET amount_due = local_rental + no_unit*local_rate;
	SET net_due = amount_due + prev_due;

	INSERT INTO water_bill(`amount_due`,`due_date`,`net_bill_amount`,`period_from`,`period_to`,`previous_due`,
							`customer_id`,`meter_reading`,`bill_status`)
	VALUES(amount_due,due_date,net_due,from_period,to_period,prev_due,cus_id,reading_id,'DUE');

	END LOOP billing_loop;
SELECT "DONE";
END 
$$