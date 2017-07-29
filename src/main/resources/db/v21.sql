--liquibase formatted sql

--changeset lhc:21

ALTER TABLE `sx_zf_yz2` ADD COLUMN `max` int(11), 
ADD COLUMN `max_avg` decimal(20,2) 

