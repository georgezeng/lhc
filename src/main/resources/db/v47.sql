--liquibase formatted sql

--changeset lhc:47

ALTER TABLE `sx_lr_yz`
ADD COLUMN `pos` int(11);

ALTER TABLE `sw_yz`
ADD COLUMN `pos` int(11);

