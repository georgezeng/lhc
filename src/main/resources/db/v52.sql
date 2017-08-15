--liquibase formatted sql

--changeset lhc:52

ALTER TABLE `mw_yz`
ADD COLUMN `red` int(11),
ADD COLUMN `yellow` int(11),
ADD COLUMN `green` int(11),
ADD COLUMN `color_count` int(11),
ADD COLUMN `color_max` int(11),
ADD COLUMN `color_total` int(11);

ALTER TABLE `sx_yz`
ADD COLUMN `red` int(11),
ADD COLUMN `yellow` int(11),
ADD COLUMN `green` int(11),
ADD COLUMN `color_count` int(11),
ADD COLUMN `color_max` int(11),
ADD COLUMN `color_total` int(11);

