--liquibase formatted sql

--changeset lhc:15

CREATE TABLE `tm_fd_yz` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`year` int(11) NOT NULL,
	`phase` int(11) NOT NULL,
	`date` varchar(20) NOT NULL,
	`fd1` int(11),
	`fd2` int(11),
	`fd3` int(11),
	`fd4` int(11),
	`fd5` int(11),
	`fd6` int(11),
	`fd7` int(11),
	`last_yz` int(11),
	`max_yz` int(11),
	`total` int(11),
	`delta` int(11),
	PRIMARY KEY (`id`),
	UNIQUE `uidx_tm_fd_yz_date` (`date`)
);

