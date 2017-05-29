--liquibase formatted sql

--changeset lhc:2

truncate table `sx_yz`;
ALTER TABLE `sx_yz` ADD COLUMN `date` varchar(20) NOT NULL AFTER `phase`;
ALTER TABLE `sx_yz` ADD COLUMN `current_sx` varchar(10) AFTER `last_yz`;
ALTER TABLE `sx_yz` DROP INDEX `uidx_sx_yz_year_phase`, ADD UNIQUE `uidx_sx_yz_date` (`date`);

CREATE TABLE `sx_zf_yz` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`year` int(11) NOT NULL,
	`phase` int(11) NOT NULL,
	`date` varchar(20) NOT NULL,
	`zf0` int(11),
	`zf1` int(11),
	`zf2` int(11),
	`zf3` int(11),
	`zf4` int(11),
	`zf5` int(11),
	`zf6` int(11),
	`zf7` int(11),
	`zf8` int(11),
	`zf9` int(11),
	`zf10` int(11),
	`zf11` int(11),
	`total` int(11) NOT NULL,
	`delta` int(11),
	`last_yz` int(11),
	PRIMARY KEY (`id`),
	UNIQUE `uidx_sx_zf_yz_date` (`date`)
);