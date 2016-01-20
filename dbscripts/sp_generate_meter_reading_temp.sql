-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- Create a temporary table that holds the meter reading for the month
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE PROCEDURE `generate_meter_reading_temp`(IN fromDate DATE, IN toDate DATE)
BEGIN
	DECLARE no_more BOOLEAN;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET no_more = TRUE;
	DROP TEMPORARY TABLE IF EXISTS METER_READING_CUR_MONTH;
	CREATE TEMPORARY TABLE METER_READING_CUR_MONTH
		SELECT id,unit,patta_number from meter_reading WHERE (reading_date between fromDate AND toDate)
		GROUP by meter_id;
END