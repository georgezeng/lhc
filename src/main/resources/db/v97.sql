--liquibase formatted sql

--changeset lhc:97

CREATE TABLE `dmg_min_jy_jqb` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`year` int(11) NOT NULL,
	`phase` int(11) NOT NULL,
	`date` varchar(20) NOT NULL,
	`special_num` int(11),
	`ae_nums` varchar(255),
	`bf_nums` varchar(255),
	`cg_nums` varchar(255),
	`dh_nums` varchar(255),
	`ae_nums_reverse` varchar(255),
	`bf_nums_reverse` varchar(255),
	`cg_nums_reverse` varchar(255),
	`dh_nums_reverse` varchar(255),
	PRIMARY KEY (`id`),
	UNIQUE `uidx_dmg_min_jy_jqb_date` (`date`)
);

CREATE TABLE `dmg_min_jy_jdb` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`year` int(11) NOT NULL,
	`phase` int(11) NOT NULL,
	`date` varchar(20) NOT NULL,
	`special_num` int(11),
	`ae_nums` varchar(255),
	`bf_nums` varchar(255),
	`cg_nums` varchar(255),
	`dh_nums` varchar(255),
	`ae_nums_reverse` varchar(255),
	`bf_nums_reverse` varchar(255),
	`cg_nums_reverse` varchar(255),
	`dh_nums_reverse` varchar(255),
	PRIMARY KEY (`id`),
	UNIQUE `uidx_dmg_min_jy_jdb_date` (`date`)
);

CREATE TABLE `dmg_min_jy_jhb` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`year` int(11) NOT NULL,
	`phase` int(11) NOT NULL,
	`date` varchar(20) NOT NULL,
	`special_num` int(11),
	`ae_nums` varchar(255),
	`bf_nums` varchar(255),
	`cg_nums` varchar(255),
	`dh_nums` varchar(255),
	`ae_nums_reverse` varchar(255),
	`bf_nums_reverse` varchar(255),
	`cg_nums_reverse` varchar(255),
	`dh_nums_reverse` varchar(255),
	PRIMARY KEY (`id`),
	UNIQUE `uidx_dmg_min_jy_jhb_date` (`date`)
);

CREATE TABLE `dmg_max_jy_jqb` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`year` int(11) NOT NULL,
	`phase` int(11) NOT NULL,
	`date` varchar(20) NOT NULL,
	`special_num` int(11),
	`ae_nums` varchar(255),
	`bf_nums` varchar(255),
	`cg_nums` varchar(255),
	`dh_nums` varchar(255),
	`ae_nums_reverse` varchar(255),
	`bf_nums_reverse` varchar(255),
	`cg_nums_reverse` varchar(255),
	`dh_nums_reverse` varchar(255),
	PRIMARY KEY (`id`),
	UNIQUE `uidx_dmg_max_jy_jqb_date` (`date`)
);

CREATE TABLE `dmg_max_jy_jdb` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`year` int(11) NOT NULL,
	`phase` int(11) NOT NULL,
	`date` varchar(20) NOT NULL,
	`special_num` int(11),
	`ae_nums` varchar(255),
	`bf_nums` varchar(255),
	`cg_nums` varchar(255),
	`dh_nums` varchar(255),
	`ae_nums_reverse` varchar(255),
	`bf_nums_reverse` varchar(255),
	`cg_nums_reverse` varchar(255),
	`dh_nums_reverse` varchar(255),
	PRIMARY KEY (`id`),
	UNIQUE `uidx_dmg_max_jy_jdb_date` (`date`)
);

CREATE TABLE `dmg_max_jy_jhb` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`year` int(11) NOT NULL,
	`phase` int(11) NOT NULL,
	`date` varchar(20) NOT NULL,
	`special_num` int(11),
	`ae_nums` varchar(255),
	`bf_nums` varchar(255),
	`cg_nums` varchar(255),
	`dh_nums` varchar(255),
	`ae_nums_reverse` varchar(255),
	`bf_nums_reverse` varchar(255),
	`cg_nums_reverse` varchar(255),
	`dh_nums_reverse` varchar(255),
	PRIMARY KEY (`id`),
	UNIQUE `uidx_dmg_max_jy_jhb_date` (`date`)
);
