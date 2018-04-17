--liquibase formatted sql

--changeset lhc:109

CREATE TABLE `zmkm_yz` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`year` int(11) NOT NULL,
	`phase` int(11) NOT NULL,
	`date` varchar(20) NOT NULL,
	`special_num` int(11),
	`c1yz` int(11),
	`c1nums` varchar(255),
	`c2yz` int(11),
	`c2nums` varchar(255),
	`c3yz` int(11),
	`c3nums` varchar(255),
	`c4yz` int(11),
	`c4nums` varchar(255),
	`c5yz` int(11),
	`c5nums` varchar(255),
	`c6yz` int(11),
	`c6nums` varchar(255),
	`c7yz` int(11),
	`c7nums` varchar(255),
	`c8yz` int(11),
	`c8nums` varchar(255),
	`c9yz` int(11),
	`c9nums` varchar(255),
	`c10yz` int(11),
	`c10nums` varchar(255),
	`c11yz` int(11),
	`c11nums` varchar(255),
	`c12yz` int(11),
	`c12nums` varchar(255),
	`c13yz` int(11),
	`c13nums` varchar(255),
	`c14yz` int(11),
	`c14nums` varchar(255),
	`c15yz` int(11),
	`c15nums` varchar(255),
	PRIMARY KEY (`id`),
	UNIQUE `uidx_zmkm_yz_date` (`date`)
);

