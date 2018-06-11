--liquibase formatted sql

--changeset lhc:111

CREATE TABLE `ssc_kai_jiang` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`year` int(11) NOT NULL,
	`phase` varchar(50) NOT NULL,
	`date` varchar(20) NOT NULL,
	`nums` varchar(50),
	PRIMARY KEY (`id`),
	INDEX `idx_ssc_kai_jiang_date` (`date`),
	UNIQUE `uidx_ssc_kai_jiang_phase` (`phase`)
);

