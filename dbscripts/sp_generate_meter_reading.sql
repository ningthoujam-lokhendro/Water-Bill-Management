-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE PROCEDURE `generate_meter_reading`(IN p_unit INT)
BEGIN
	DECLARE done BOOLEAN;
	DECLARE meterId varchar(255);
	DECLARE pattaNumber varchar(255);
	DECLARE meter_cursor CURSOR FOR SELECT meter_id,patta_number FROM meter;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
	OPEN meter_cursor;

read_loop: LOOP
	
	FETCH meter_cursor INTO meterId,pattaNumber;
	
	IF done THEN
		CLOSE meter_cursor;
		LEAVE read_loop;
	END IF;

	INSERT INTO meter_reading(reading_date,unit,patta_number,meter_id)
			VALUES (curdate(),p_unit,pattaNumber,meterId);
	
	END loop read_loop;
END
$$