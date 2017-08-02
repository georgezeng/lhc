--liquibase formatted sql

--changeset lhc:32

ALTER TABLE `ds_zf_yz`
ADD COLUMN `current_pos` int(11);