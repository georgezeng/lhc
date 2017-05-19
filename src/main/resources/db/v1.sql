--liquibase formatted sql

--changeset lhc:1

CREATE TABLE `kai_jiang` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`date` varchar(20) NOT NULL,
	`year` int(11) NOT NULL,
	`phase` int(11) NOT NULL,
	`num1` int(11) NOT NULL,
	`num1sx` varchar(10) NOT NULL,
	`num2` int(11) NOT NULL,
	`num2sx` varchar(10) NOT NULL,
	`num3` int(11) NOT NULL,
	`num3sx` varchar(10) NOT NULL,
	`num4` int(11) NOT NULL,
	`num4sx` varchar(10) NOT NULL,
	`num5` int(11) NOT NULL,
	`num5sx` varchar(10) NOT NULL,
	`num6` int(11) NOT NULL,
	`num6sx` varchar(10) NOT NULL,
	`special_num` int(11) NOT NULL,
	`special_sx` varchar(10) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE `uidx_kai_jiang_date` (`date`),
	INDEX `idx_kai_jiang_year` (`year`)
);

CREATE TABLE `sx_yz` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`year` int(11) NOT NULL,
	`phase` int(11) NOT NULL,
	`Shu` int(11),
	`Niu` int(11),
	`Hu` int(11),
	`Tu` int(11),
	`lonng` int(11),
	`She` int(11),
	`Ma` int(11),
	`Yang` int(11),
	`Hou` int(11),
	`Ji` int(11),
	`Gou` int(11),
	`Zhu` int(11),
	`total` int(11) NOT NULL,
	`delta` int(11),
	`last_yz` int(11),
	PRIMARY KEY (`id`),
	UNIQUE `uidx_sx_yz_year_phase` (`year`, `phase`)
);