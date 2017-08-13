--liquibase formatted sql

--changeset lhc:44

ALTER TABLE `sx_cs_yz`
ADD COLUMN `large` int(11),
ADD COLUMN `small` int(11);
