--liquibase formatted sql

--changeset lhc:84

CREATE TABLE `zx1_zf_yz` (
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
	`delta` int(11),
	`last_yz` int(11),
	`total` int(11) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE `uidx_zx1_zf_yz_date` (`date`)
);

ALTER TABLE `zx1_zf_yz`
ADD COLUMN `total_avg` decimal(20, 2), 
ADD COLUMN `top0` int(11), 
ADD COLUMN `top0avg` decimal(20, 2), 
ADD COLUMN `top1` int(11), 
ADD COLUMN `top1avg` decimal(20, 2), 
ADD COLUMN `top2` int(11), 
ADD COLUMN `top2avg` decimal(20, 2), 
ADD COLUMN `top3` int(11), 
ADD COLUMN `top3avg` decimal(20, 2), 
ADD COLUMN `top4` int(11), 
ADD COLUMN `top4avg` decimal(20, 2), 
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
ADD COLUMN `min6avg` decimal(20,2),
ADD COLUMN `min7` int(11), 
ADD COLUMN `min7avg` decimal(20, 2), 
ADD COLUMN `min8` int(11), 
ADD COLUMN `min8avg` decimal(20, 2), 
ADD COLUMN `min9` int(11), 
ADD COLUMN `min9avg` decimal(20, 2), 
ADD COLUMN `min10` int(11), 
ADD COLUMN `min10avg` decimal(20, 2), 
ADD COLUMN `min11` int(11), 
ADD COLUMN `min11avg` decimal(20, 2), 
ADD COLUMN `min12` int(11), 
ADD COLUMN `min12avg` decimal(20, 2), 
ADD COLUMN `min13` int(11), 
ADD COLUMN `min13avg` decimal(20, 2), 
ADD COLUMN `min14` int(11), 
ADD COLUMN `min14avg` decimal(20, 2), 
ADD COLUMN `min15` int(11), 
ADD COLUMN `min15avg` decimal(20, 2), 
ADD COLUMN `min16` int(11), 
ADD COLUMN `min16avg` decimal(20, 2), 
ADD COLUMN `min17` int(11), 
ADD COLUMN `min17avg` decimal(20, 2), 
ADD COLUMN `min18` int(11), 
ADD COLUMN `min18avg` decimal(20, 2), 
ADD COLUMN `min19` int(11), 
ADD COLUMN `min19avg` decimal(20, 2),
ADD COLUMN `current_pos` int(11);

