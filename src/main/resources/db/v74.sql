--liquibase formatted sql

--changeset lhc:74

ALTER TABLE `sx_ds_yz`
DROP COLUMN `last_hm_ds_yz`,
DROP COLUMN `last_hm_dx_yz`,
DROP COLUMN `hm_small`,
DROP COLUMN `hm_large`,
DROP COLUMN `hm_single`,
DROP COLUMN `hm_even`,
ADD COLUMN `sx_small_odd` int(11),
ADD COLUMN `sx_small_even` int(11),
ADD COLUMN `sx_large_odd` int(11),
ADD COLUMN `sx_large_even` int(11),
ADD COLUMN `last_sx_dx_dsYz` int(11);
