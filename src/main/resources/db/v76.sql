--liquibase formatted sql

--changeset lhc:76

CREATE TABLE `lh_ds_yz` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`year` int(11) NOT NULL,
	`phase` int(11) NOT NULL,
	`date` varchar(20) NOT NULL,
	`small` int(11),
	`large` int(11),
	`odd` int(11),
	`even` int(11),
	`small_odd` int(11),
	`small_even` int(11),
	`large_odd` int(11),
	`large_even` int(11),
	`last_ds_yz` int(11),
	`last_dx_yz` int(11),
	`last_dx_ds_yz` int(11),
	PRIMARY KEY (`id`),
	UNIQUE `uidx_lh_ds_yz_date` (`date`)
);

