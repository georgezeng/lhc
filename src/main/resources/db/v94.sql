--liquibase formatted sql

--changeset lhc:94

CREATE TABLE `fx_sw_a` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`year` int(11) NOT NULL,
	`phase` int(11) NOT NULL,
	`date` varchar(20) NOT NULL,
	`special_num` int(11),
	`a1nums_for_all` varchar(255),
	`a2nums_for_all` varchar(255),
	`a3nums_for_all` varchar(255),
	`a3p_nums_for_all` varchar(255),
	`ar_nums_for_all` varchar(255),
	`ara2a3a3pnums_for_all` varchar(255),
	`a1nums_for_all_yz` varchar(255),
	`a2nums_for_all_yz` varchar(255),
	`a3nums_for_all_yz` varchar(255),
	`a3p_nums_for_all_yz` varchar(255),
	`ar_nums_for_all_yz` varchar(255),
	`ara2a3a3pnums_for_all_yz` varchar(255),
	`a1nums_for_all_zf` varchar(255),
	`a2nums_for_all_zf` varchar(255),
	`a3nums_for_all_zf` varchar(255),
	`a3p_nums_for_all_zf` varchar(255),
	`ar_nums_for_all_zf` varchar(255),
	`ara2a3a3pnums_for_all_zf` varchar(255),
	`a1nums_for_nonwq` varchar(255),
	`a2nums_for_nonwq` varchar(255),
	`a3nums_for_nonwq` varchar(255),
	`a3p_nums_for_nonwq` varchar(255),
	`ar_nums_for_nonwq` varchar(255),
	`ara2a3a3pnums_for_nonwq` varchar(255),
	PRIMARY KEY (`id`),
	UNIQUE `uidx_fx_sw_a_date` (`date`)
);
