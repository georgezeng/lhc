--liquibase formatted sql

--changeset lhc:11

CREATE TABLE `bs_yz` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`year` int(11) NOT NULL,
	`phase` int(11) NOT NULL,
	`date` varchar(20) NOT NULL,
	`red` int(11),
	`green` int(11),
	`blue` int(11),
	`total` int(11) NOT NULL,
	`delta` int(11),
	`last_yz` int(11),
	PRIMARY KEY (`id`),
	UNIQUE `uidx_bs_yz_date` (`date`)
);

