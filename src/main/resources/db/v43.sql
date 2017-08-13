--liquibase formatted sql

--changeset lhc:43

CREATE TABLE `sx_cs_yz` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`year` int(11) NOT NULL,
	`phase` int(11) NOT NULL,
	`date` varchar(20) NOT NULL,
	`shu` int(11),
	`niu` int(11),
	`hu` int(11),
	`tu` int(11),
	`lonng` int(11),
	`She` int(11),
	`Ma` int(11),
	`Yang` int(11),
	`Hou` int(11),
	`Ji` int(11),
	`Gou` int(11),
	`Zhu` int(11),
	`avg` decimal(20, 2),
	`current_sx` varchar(20),
	`pairs` int(11),
	PRIMARY KEY (`id`),
	UNIQUE `uidx_sx_cs_yz_date` (`date`)
);