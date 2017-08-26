--liquibase formatted sql

--changeset lhc:60

ALTER TABLE `zs_yz`
ADD COLUMN `fd9` int(11);

ALTER TABLE `zs_zf_yz`
ADD COLUMN `zf8` int(11);

