--liquibase formatted sql

--changeset lhc:35

ALTER TABLE `mw_yz`
ADD COLUMN `min0` int(11), 
ADD COLUMN `min0avg` decimal(20, 2), 
ADD COLUMN `min1` int(11), 
ADD COLUMN `min1avg` decimal(20, 2), 
ADD COLUMN `min2` int(11), 
ADD COLUMN `min2avg` decimal(20, 2), 
ADD COLUMN `min3` int(11), 
ADD COLUMN `min3avg` decimal(20, 2), 
ADD COLUMN `min4` int(11), 
ADD COLUMN `min4avg` decimal(20, 2), 
ADD COLUMN `min5` int(11), 
ADD COLUMN `min5avg` decimal(20, 2), 
ADD COLUMN `min6` int(11), 
ADD COLUMN `min6avg` decimal(20, 2);

ALTER TABLE `mw_zf_yz`
ADD COLUMN `min0` int(11), 
ADD COLUMN `min0avg` decimal(20, 2), 
ADD COLUMN `min1` int(11), 
ADD COLUMN `min1avg` decimal(20, 2), 
ADD COLUMN `min2` int(11), 
ADD COLUMN `min2avg` decimal(20, 2), 
ADD COLUMN `min3` int(11), 
ADD COLUMN `min3avg` decimal(20, 2), 
ADD COLUMN `min4` int(11), 
ADD COLUMN `min4avg` decimal(20, 2), 
ADD COLUMN `min5` int(11), 
ADD COLUMN `min5avg` decimal(20, 2), 
ADD COLUMN `min6` int(11), 
ADD COLUMN `min6avg` decimal(20, 2); 
