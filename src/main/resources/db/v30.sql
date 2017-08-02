--liquibase formatted sql

--changeset lhc:30

ALTER TABLE `sx_ds_yz` DROP INDEX `uidx_ds_yz_date`, ADD UNIQUE `uidx_sx_ds_yz_date` USING BTREE (`date`) comment '';