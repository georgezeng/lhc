--liquibase formatted sql

--changeset lhc:78

ALTER TABLE `sx_yz`
ADD COLUMN `zh_count` int(11),
ADD COLUMN `non_zh_count` int(11);
