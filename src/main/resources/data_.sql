INSERT INTO `connection_type`
(`id`,`RATE`,`TYPE`) VALUES (1,10,'HOME');
INSERT INTO `connection_type`
(`id`,`RATE`,`TYPE`) VALUES (2,20,'COMMERCIAL');

INSERT INTO `bill_status`
(`id`,`STATUS`) VALUES (1,'DUE');

INSERT INTO `bill_status`
(`id`,`STATUS`) VALUES (2,'PAID');

INSERT INTO `payment_status_code`
(`id`,`DESCRIPTION`) VALUES (1,'COMPLETED');

INSERT INTO `payment_status_code`
(`id`,`DESCRIPTION`) VALUES (2,'PARTIAL');

INSERT INTO `payment_type`
(`id`, `DESCRIPTION`) VALUES (1,'BALANCE');

INSERT INTO `payment_type`
(`id`, `DESCRIPTION`) VALUES (2,'ADVANCE');