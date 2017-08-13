--liquibase formatted sql

--changeset lhc:42

ALTER TABLE `pt_yz`
ADD COLUMN `add1` int(11),
ADD COLUMN `min1` int(11),
ADD COLUMN `jg0` int(11),
ADD COLUMN `jg1` int(11),
ADD COLUMN `jg2` int(11),
ADD COLUMN `jg3` int(11),
ADD COLUMN `jg4` int(11),
ADD COLUMN `jg5` int(11),
ADD COLUMN `jg6` int(11),
ADD COLUMN `jg6plus` int(11);
