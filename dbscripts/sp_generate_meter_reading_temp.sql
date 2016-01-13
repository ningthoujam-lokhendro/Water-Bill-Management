-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE PROCEDURE `generate_meter_reading_temp`()
BEGIN
	DECLARE no_more BOOLEAN;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET no_more = TRUE;
	DROP TEMPORARY TABLE IF EXISTS METER_READING_CUR_MONTH;
	CREATE TEMPORARY TABLE METER_READING_CUR_MONTH
		SELECT id,unit,patta_number from meter_reading WHERE (reading_date between DATE_FORMAT(CURDATE(), '%Y-%m-01') AND LAST_DAY(CURDATE()) )
		GROUP by meter_id;
END