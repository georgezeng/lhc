--liquibase formatted sql

--changeset lhc:5

CREATE TABLE `sw_yz` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`year` int(11) NOT NULL,
	`phase` int(11) NOT NULL,
	`date` varchar(20) NOT NULL,
	`w1` int(11),
	`w2` int(11),
	`w3` int(11),
	`w4` int(11),
	`w0` int(11),
	`total` int(11) NOT NULL,
	`current_yz` int(11),
	PRIMARY KEY (`id`),
	UNIQUE `uidx_sw_yz_date` (`date`)
);

