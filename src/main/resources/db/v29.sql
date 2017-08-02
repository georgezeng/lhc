--liquibase formatted sql

--changeset lhc:29

ALTER TABLE `sw_zf_yz` DROP INDEX `uidx_zs_yz_date`, ADD UNIQUE `uidx_sw_zf_yz_date` USING BTREE (`date`) comment '';