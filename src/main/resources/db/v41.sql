--liquibase formatted sql

--changeset lhc:41

ALTER TABLE `pt_yz`
ADD COLUMN `special_num` int(11);
