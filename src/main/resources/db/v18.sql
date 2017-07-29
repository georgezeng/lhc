--liquibase formatted sql

--changeset lhc:18

CREATE TABLE `sx_zf_yz2` (
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
	UNIQUE `uidx_sx_zf_yz2_date` (`date`)
);