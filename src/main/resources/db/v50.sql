--liquibase formatted sql

--changeset lhc:50

ALTER TABLE `qq_yz`
ADD COLUMN `pos` int(11);

