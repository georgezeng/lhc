--liquibase formatted sql

--changeset lhc:108

CREATE TABLE `my_xc_mn_yz` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`year` int(11) NOT NULL,
	`phase` int(11) NOT NULL,
	`date` varchar(20) NOT NULL,
	`special_num` int(11),
	`c1nums` varchar(255),
	`c2nums` varchar(255),
	`c3nums` varchar(255),
	`c4nums` varchar(255),
	`c5nums` varchar(255),
	`c6nums` varchar(255),
	`c7nums` varchar(255),
	`c8nums` varchar(255),
	`c9nums` varchar(255),
	`c10nums` varchar(255),
	PRIMARY KEY (`id`),
	UNIQUE `uidx_my_xc_mn_yz_date` (`date`)
);

CREATE TABLE `my100_xc_mn_yz` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`year` int(11) NOT NULL,
	`phase` int(11) NOT NULL,
	`date` varchar(20) NOT NULL,
	`special_num` int(11),
	`c1nums` varchar(255),
	`c2nums` varchar(255),
	`c3nums` varchar(255),
	`c4nums` varchar(255),
	`c5nums` varchar(255),
	`c6nums` varchar(255),
	`c7nums` varchar(255),
	`c8nums` varchar(255),
	`c9nums` varchar(255),
	`c10nums` varchar(255),
	PRIMARY KEY (`id`),
	UNIQUE `uidx_my100_xc_mn_yz_date` (`date`)
);

CREATE TABLE `sw_xc_mn_yz` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`year` int(11) NOT NULL,
	`phase` int(11) NOT NULL,
	`date` varchar(20) NOT NULL,
	`special_num` int(11),
	`c1nums` varchar(255),
	`c2nums` varchar(255),
	`c3nums` varchar(255),
	`c4nums` varchar(255),
	`c5nums` varchar(255),
	`c6nums` varchar(255),
	`c7nums` varchar(255),
	`c8nums` varchar(255),
	`c9nums` varchar(255),
	`c10nums` varchar(255),
	PRIMARY KEY (`id`),
	UNIQUE `uidx_sw_xc_mn_yz_date` (`date`)
);

CREATE TABLE `scyd_yz` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`year` int(11) NOT NULL,
	`phase` int(11) NOT NULL,
	`date` varchar(20) NOT NULL,
	`special_num` int(11),
	`my_yz` int(11),
	`my100_yz` int(11),
	`sw_yz` int(11),
	`v1_yz` int(11),
	`v2_yz` int(11),
	`v3_yz` int(11),
	`my_nums` varchar(255),
	`my100_nums` varchar(255),
	`sw_nums` varchar(255),
	`v1_nums` varchar(255),
	`v2_nums` varchar(255),
	`v3_nums` varchar(255),
	PRIMARY KEY (`id`),
	UNIQUE `uidx_scyd_yz_date` (`date`)
);

