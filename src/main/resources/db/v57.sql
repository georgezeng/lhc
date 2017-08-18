--liquibase formatted sql

--changeset lhc:57

ALTER TABLE `tm_fd_yz`
ADD COLUMN `tm` int(11),
ADD COLUMN `prev_delta` int(11);

