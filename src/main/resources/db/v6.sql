--liquibase formatted sql

--changeset lhc:6

ALTER TABLE `sw_yz` ADD COLUMN `delta` int(11) AFTER `current_yz`;

