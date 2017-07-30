--liquibase formatted sql

--changeset lhc:24

CREATE TABLE `zs_yz` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`year` int(11) NOT NULL,
	`phase` int(11) NOT NULL,
	`date` varchar(20) NOT NULL,
	`fd1` int(11),
	`fd2` int(11),
	`fd3` int(11),
	`fd4` int(11),
	`fd5` int(11),
	`fd6` int(11),
	`fd7` int(11),
	`fd8` int(11),
	`delta` int(11),
	`last_yz` int(11),
	`total` int(11) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE `uidx_zs_yz_date` (`date`)
);

ALTER TABLE `zs_yz`
ADD COLUMN `total_avg` decimal(20, 2), 
ADD COLUMN `max` int(11), 
ADD COLUMN `max_avg` decimal(20, 2), 
ADD COLUMN `min0` int(11), 
ADD COLUMN `min0avg` decimal(20,2),
ADD COLUMN `min1` int(11), 
ADD COLUMN `min1avg` decimal(20,2),
ADD COLUMN `min2` int(11), 
ADD COLUMN `min2avg` decimal(20,2),
ADD COLUMN `min3` int(11), 
ADD COLUMN `min3avg` decimal(20,2),
ADD COLUMN `min4` int(11), 
ADD COLUMN `min4avg` decimal(20,2),
ADD COLUMN `min5` int(11), 
ADD COLUMN `min5avg` decimal(20,2),
ADD COLUMN `min6` int(11), 
ADD COLUMN `min6avg` decimal(20,2);