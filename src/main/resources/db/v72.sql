--liquibase formatted sql

--changeset lhc:72

ALTER TABLE `qiw_zf_yz`
DROP COLUMN `currentPos`,
ADD COLUMN `current_pos` int(11);
