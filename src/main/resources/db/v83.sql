--liquibase formatted sql

--changeset lhc:83

ALTER TABLE `zx17_yz`
ADD COLUMN `w13` int(11), 
ADD COLUMN `w14` int(11), 
ADD COLUMN `w15` int(11), 
ADD COLUMN `w16` int(11); 

ALTER TABLE `zx18_yz`
ADD COLUMN `w13` int(11), 
ADD COLUMN `w14` int(11), 
ADD COLUMN `w15` int(11), 
ADD COLUMN `w16` int(11); 

