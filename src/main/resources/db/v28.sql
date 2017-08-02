--liquibase formatted sql

--changeset lhc:28

CREATE TABLE `ds_yz` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`year` int(11) NOT NULL,
	`phase` int(11) NOT NULL,
	`date` varchar(20) NOT NULL,
	`ds0Odd` int(11),
	`ds0Even` int(11),
	`ds1Odd` int(11),
	`ds1Even` int(11),
	`ds2Odd` int(11),
	`ds2Even` int(11),
	`ds3Odd` int(11),
	`ds3Even` int(11),
	`ds4Odd` int(11),
	`ds4Even` int(11),
	`delta` int(11),
	`last_yz` int(11),
	`total` int(11) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE `uidx_ds_yz_date` (`date`)
);

ALTER TABLE `ds_yz`
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