--liquibase formatted sql

--changeset lhc:6

ALTER TABLE `sw_yz` CHANGE COLUMN `current_yz` `last_yz` int(11) DEFAULT NULL;

