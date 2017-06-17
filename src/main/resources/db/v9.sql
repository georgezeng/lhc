--liquibase formatted sql

--changeset lhc:9

CREATE TABLE `lh_yz` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`year` int(11) NOT NULL,
	`phase` int(11) NOT NULL,
	`date` varchar(20) NOT NULL,
	`w1` int(11),
	`w2` int(11),
	`w3` int(11),
	`w4` int(11),
	`w5` int(11),
	`w6` int(11),
	`w7` int(11),
	`w8` int(11),
	`w9` int(11),
	`w0` int(11),
	`total` int(11) NOT NULL,
	`delta` int(11),
	`last_yz` int(11),
	PRIMARY KEY (`id`),
	UNIQUE `uidx_lh_yz_date` (`date`)
);

