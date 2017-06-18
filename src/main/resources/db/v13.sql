--liquibase formatted sql

--changeset lhc:13

CREATE TABLE `ds_yz` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`year` int(11) NOT NULL,
	`phase` int(11) NOT NULL,
	`date` varchar(20) NOT NULL,
	`sx_small` int(11),
	`sx_large` int(11),
	`sx_single` int(11),
	`sx_even` int(11),
	`hm_small` int(11),
	`hm_large` int(11),
	`hm_single` int(11),
	`hm_even` int(11),
	`last_sx_ds_yz` int(11),
	`last_sx_dx_yz` int(11),
	`last_hm_ds_yz` int(11),
	`last_hm_dx_yz` int(11),
	PRIMARY KEY (`id`),
	UNIQUE `uidx_ds_yz_date` (`date`)
);

