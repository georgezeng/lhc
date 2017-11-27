--liquibase formatted sql

--changeset lhc:75

ALTER TABLE `sx_ds_yz`
DROP COLUMN `last_sx_dx_dsYz`,
ADD COLUMN `last_sx_dx_ds_yz` int(11);
