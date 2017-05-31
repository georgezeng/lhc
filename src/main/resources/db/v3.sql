--liquibase formatted sql

--changeset lhc:3

ALTER TABLE `sx_zf_yz` ADD COLUMN `current_pos` int(11) AFTER `last_yz`;

