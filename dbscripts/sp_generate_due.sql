-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE PROCEDURE `generate_due`()
BEGIN
	DECLARE no_more BOOLEAN;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET no_more = TRUE;
	DROP TEMPORARY TABLE IF EXISTS DUE_REPORT;
	CREATE TEMPORARY TABLE DUE_REPORT
	SELECT customer_id,net_bill_amount, MAX(due_date)
		FROM water_bill WHERE bill_status='DUE'
		GROUP BY customer_id;
END