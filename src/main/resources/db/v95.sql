--liquibase formatted sql

--changeset lhc:95

ALTER TABLE `fx_sw_a` 
ADD COLUMN `a1nums_for_jh` varchar(255),
ADD COLUMN `a2nums_for_jh` varchar(255),
ADD COLUMN `a3nums_for_jh` varchar(255),
ADD COLUMN `a3p_nums_for_jh` varchar(255),
ADD COLUMN `ar_nums_for_jh` varchar(255),
ADD COLUMN `ara2a3a3pnums_for_jh` varchar(255);

