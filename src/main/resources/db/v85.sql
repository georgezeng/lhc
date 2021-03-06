--liquibase formatted sql

--changeset lhc:85

CREATE TABLE `zx2_zf_yz` (
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
	UNIQUE `uidx_zx2_zf_yz_date` (`date`)
);

ALTER TABLE `zx2_zf_yz`
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

CREATE TABLE `zx3_zf_yz` (
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
	UNIQUE `uidx_zx3_zf_yz_date` (`date`)
);

ALTER TABLE `zx3_zf_yz`
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

CREATE TABLE `zx4_zf_yz` (
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
	UNIQUE `uidx_zx4_zf_yz_date` (`date`)
);

ALTER TABLE `zx4_zf_yz`
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

CREATE TABLE `zx5_zf_yz` (
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
	UNIQUE `uidx_zx5_zf_yz_date` (`date`)
);

ALTER TABLE `zx5_zf_yz`
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

CREATE TABLE `zx6_zf_yz` (
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
	UNIQUE `uidx_zx6_zf_yz_date` (`date`)
);

ALTER TABLE `zx6_zf_yz`
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

CREATE TABLE `zx7_zf_yz` (
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
	`delta` int(11),
	`last_yz` int(11),
	`total` int(11) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE `uidx_zx7_zf_yz_date` (`date`)
);

ALTER TABLE `zx7_zf_yz`
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

CREATE TABLE `zx8_zf_yz` (
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
	`delta` int(11),
	`last_yz` int(11),
	`total` int(11) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE `uidx_zx8_zf_yz_date` (`date`)
);

ALTER TABLE `zx8_zf_yz`
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

CREATE TABLE `zx9_zf_yz` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`year` int(11) NOT NULL,
	`phase` int(11) NOT NULL,
	`date` varchar(20) NOT NULL,
	`zf0` int(11),
	`zf1` int(11),
	`zf2` int(11),
	`zf3` int(11),
	`zf4` int(11),
	`delta` int(11),
	`last_yz` int(11),
	`total` int(11) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE `uidx_zx9_zf_yz_date` (`date`)
);

ALTER TABLE `zx9_zf_yz`
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

CREATE TABLE `zx10_zf_yz` (
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
	`delta` int(11),
	`last_yz` int(11),
	`total` int(11) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE `uidx_zx10_zf_yz_date` (`date`)
);

ALTER TABLE `zx10_zf_yz`
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

CREATE TABLE `zx11_zf_yz` (
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
	UNIQUE `uidx_zx11_zf_yz_date` (`date`)
);

ALTER TABLE `zx11_zf_yz`
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

CREATE TABLE `zx12_zf_yz` (
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
	UNIQUE `uidx_zx12_zf_yz_date` (`date`)
);

ALTER TABLE `zx12_zf_yz`
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

CREATE TABLE `zx13_zf_yz` (
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
	UNIQUE `uidx_zx13_zf_yz_date` (`date`)
);

ALTER TABLE `zx13_zf_yz`
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

CREATE TABLE `zx14_zf_yz` (
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
	UNIQUE `uidx_zx14_zf_yz_date` (`date`)
);

ALTER TABLE `zx14_zf_yz`
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

CREATE TABLE `zx15_zf_yz` (
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
	UNIQUE `uidx_zx15_zf_yz_date` (`date`)
);

ALTER TABLE `zx15_zf_yz`
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

CREATE TABLE `zx16_zf_yz` (
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
	UNIQUE `uidx_zx16_zf_yz_date` (`date`)
);

ALTER TABLE `zx16_zf_yz`
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

CREATE TABLE `zx17_zf_yz` (
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
	`zf12` int(11),
	`zf13` int(11),
	`zf14` int(11),
	`zf15` int(11),
	`delta` int(11),
	`last_yz` int(11),
	`total` int(11) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE `uidx_zx17_zf_yz_date` (`date`)
);

ALTER TABLE `zx17_zf_yz`
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

CREATE TABLE `zx18_zf_yz` (
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
	`zf12` int(11),
	`zf13` int(11),
	`zf14` int(11),
	`zf15` int(11),
	`delta` int(11),
	`last_yz` int(11),
	`total` int(11) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE `uidx_zx18_zf_yz_date` (`date`)
);

ALTER TABLE `zx18_zf_yz`
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

