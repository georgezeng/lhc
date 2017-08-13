--liquibase formatted sql

--changeset lhc:49

ALTER TABLE `qq_zf_yz`
DROP COLUMN `currentPos`,
ADD COLUMN `current_pos` int(11);

