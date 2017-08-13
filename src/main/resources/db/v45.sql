--liquibase formatted sql

--changeset lhc:44

ALTER TABLE `sx_cs_yz`
ADD COLUMN `total` int(11);
