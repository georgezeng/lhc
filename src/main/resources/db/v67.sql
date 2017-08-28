--liquibase formatted sql

--changeset lhc:67

ALTER TABLE `bs9q_yz` 
DROP COLUMN `w1`, 
DROP COLUMN `w2`, 
DROP COLUMN `w3`, 
DROP COLUMN `w4`, 
DROP COLUMN `w5`, 
DROP COLUMN `w6`, 
DROP COLUMN `w7`, 
DROP COLUMN `w8`, 
DROP COLUMN `w9`, 
ADD COLUMN `red1` int(11), 
ADD COLUMN `red2` int(11),
ADD COLUMN `red3` int(11), 
ADD COLUMN `blue1` int(11), 
ADD COLUMN `blue2` int(11),
ADD COLUMN `blue3` int(11), 
ADD COLUMN `green1` int(11),
ADD COLUMN `green2` int(11), 
ADD COLUMN `green3` int(11);
