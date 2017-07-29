--liquibase formatted sql

--changeset lhc:16

ALTER TABLE `sx_yz` ADD COLUMN `max` int(11), 
ADD COLUMN `max_avg` decimal(20,2), 
ADD COLUMN `min` int(11), 
ADD COLUMN `min_avg` decimal(20,2);

