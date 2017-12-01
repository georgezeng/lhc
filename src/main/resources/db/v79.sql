--liquibase formatted sql

--changeset lhc:79

update `sx_yz` set zh_count=0, non_zh_count=0;
