--liquibase formatted sql

--changeset lhc:19

ALTER TABLE `sx_zf_yz2` ADD COLUMN `current_pos` int(11);

