--liquibase formatted sql

--changeset lhc:40

CREATE TABLE `pt_yz` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`year` int(11) NOT NULL,
	`phase` int(11) NOT NULL,
	`date` varchar(20) NOT NULL,
	`hm1` int(11),
	`hm2` int(11),
	`hm3` int(11),
	`hm4` int(11),
	`hm5` int(11),
	`hm6` int(11),
	`hm7` int(11),
	`hm8` int(11),
	`hm9` int(11),
	`hm10` int(11),
	`hm11` int(11),
	`hm12` int(11),
	`hm13` int(11),
	`hm14` int(11),
	`hm15` int(11),
	`hm16` int(11),
	`hm17` int(11),
	`hm18` int(11),
	`hm19` int(11),
	`hm20` int(11),
	`hm21` int(11),
	`hm22` int(11),
	`hm23` int(11),
	`hm24` int(11),
	`hm25` int(11),
	`hm26` int(11),
	`hm27` int(11),
	`hm28` int(11),
	`hm29` int(11),
	`hm30` int(11),
	`hm31` int(11),
	`hm32` int(11),
	`hm33` int(11),
	`hm34` int(11),
	`hm35` int(11),
	`hm36` int(11),
	`hm37` int(11),
	`hm38` int(11),
	`hm39` int(11),
	`hm40` int(11),
	`hm41` int(11),
	`hm42` int(11),
	`hm43` int(11),
	`hm44` int(11),
	`hm45` int(11),
	`hm46` int(11),
	`hm47` int(11),
	`hm48` int(11),
	`hm49` int(11),
	PRIMARY KEY (`id`),
	UNIQUE `uidx_pt_yz_date` (`date`)
);

