--liquibase formatted sql

--changeset lhc:23

ALTER TABLE `bs_yz` 
DROP COLUMN `red`, 
DROP COLUMN `green`, 
DROP COLUMN `blue`, 
ADD COLUMN `red_odd` int(11), 
ADD COLUMN `red_even` int(11), 
ADD COLUMN `green_odd` int(11), 
ADD COLUMN `green_even` int(11), 
ADD COLUMN `blue_odd` int(11), 
ADD COLUMN `blue_even` int(11), 
ADD COLUMN `total_avg` decimal(20, 2), 
ADD COLUMN `max` int(11), 
ADD COLUMN `max_avg` decimal(20, 2), 
ADD COLUMN `min0` int(11), 
ADD COLUMN `min0avg` decimal(20,2),
ADD COLUMN `min1` int(11), 
ADD COLUMN `min1avg` decimal(20,2),
ADD COLUMN `min2` int(11), 
ADD COLUMN `min2avg` decimal(20,2),
ADD COLUMN `min3` int(11), 
ADD COLUMN `min3avg` decimal(20,2),
ADD COLUMN `min4` int(11), 
ADD COLUMN `min4avg` decimal(20,2),
ADD COLUMN `min5` int(11), 
ADD COLUMN `min5avg` decimal(20,2),
ADD COLUMN `min6` int(11), 
ADD COLUMN `min6avg` decimal(20,2);

