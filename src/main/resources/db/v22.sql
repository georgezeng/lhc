--liquibase formatted sql

--changeset lhc:22

ALTER TABLE `sx_yz` ADD COLUMN `total_avg` int(11); 
ALTER TABLE `sx_zf_yz2` ADD COLUMN `total_avg` int(11); 

