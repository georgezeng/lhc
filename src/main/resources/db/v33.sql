--liquibase formatted sql

--changeset lhc:33

ALTER TABLE `sx_yz`
DROP COLUMN `max`, 
DROP COLUMN `max_avg`, 
ADD COLUMN `top0` int(11), 
ADD COLUMN `top0avg` decimal(20, 2), 
ADD COLUMN `top1` int(11), 
ADD COLUMN `top1avg` decimal(20, 2), 
ADD COLUMN `top2` int(11), 
ADD COLUMN `top2avg` decimal(20, 2), 
ADD COLUMN `top3` int(11), 
ADD COLUMN `top3avg` decimal(20, 2), 
ADD COLUMN `top4` int(11), 
ADD COLUMN `top4avg` decimal(20, 2), 
ADD COLUMN `min7` int(11), 
ADD COLUMN `min7avg` decimal(20, 2), 
ADD COLUMN `min8` int(11), 
ADD COLUMN `min8avg` decimal(20, 2), 
ADD COLUMN `min9` int(11), 
ADD COLUMN `min9avg` decimal(20, 2), 
ADD COLUMN `min10` int(11), 
ADD COLUMN `min10avg` decimal(20, 2), 
ADD COLUMN `min11` int(11), 
ADD COLUMN `min11avg` decimal(20, 2), 
ADD COLUMN `min12` int(11), 
ADD COLUMN `min12avg` decimal(20, 2), 
ADD COLUMN `min13` int(11), 
ADD COLUMN `min13avg` decimal(20, 2), 
ADD COLUMN `min14` int(11), 
ADD COLUMN `min14avg` decimal(20, 2), 
ADD COLUMN `min15` int(11), 
ADD COLUMN `min15avg` decimal(20, 2), 
ADD COLUMN `min16` int(11), 
ADD COLUMN `min16avg` decimal(20, 2), 
ADD COLUMN `min17` int(11), 
ADD COLUMN `min17avg` decimal(20, 2), 
ADD COLUMN `min18` int(11), 
ADD COLUMN `min18avg` decimal(20, 2), 
ADD COLUMN `min19` int(11), 
ADD COLUMN `min19avg` decimal(20, 2);

ALTER TABLE `sx_zf_yz2`
DROP COLUMN `max`, 
DROP COLUMN `max_avg`,
ADD COLUMN `top0` int(11), 
ADD COLUMN `top0avg` decimal(20, 2), 
ADD COLUMN `top1` int(11), 
ADD COLUMN `top1avg` decimal(20, 2), 
ADD COLUMN `top2` int(11), 
ADD COLUMN `top2avg` decimal(20, 2), 
ADD COLUMN `top3` int(11), 
ADD COLUMN `top3avg` decimal(20, 2), 
ADD COLUMN `top4` int(11), 
ADD COLUMN `top4avg` decimal(20, 2), 
ADD COLUMN `min7` int(11), 
ADD COLUMN `min7avg` decimal(20, 2), 
ADD COLUMN `min8` int(11), 
ADD COLUMN `min8avg` decimal(20, 2), 
ADD COLUMN `min9` int(11), 
ADD COLUMN `min9avg` decimal(20, 2), 
ADD COLUMN `min10` int(11), 
ADD COLUMN `min10avg` decimal(20, 2), 
ADD COLUMN `min11` int(11), 
ADD COLUMN `min11avg` decimal(20, 2), 
ADD COLUMN `min12` int(11), 
ADD COLUMN `min12avg` decimal(20, 2), 
ADD COLUMN `min13` int(11), 
ADD COLUMN `min13avg` decimal(20, 2), 
ADD COLUMN `min14` int(11), 
ADD COLUMN `min14avg` decimal(20, 2), 
ADD COLUMN `min15` int(11), 
ADD COLUMN `min15avg` decimal(20, 2), 
ADD COLUMN `min16` int(11), 
ADD COLUMN `min16avg` decimal(20, 2), 
ADD COLUMN `min17` int(11), 
ADD COLUMN `min17avg` decimal(20, 2), 
ADD COLUMN `min18` int(11), 
ADD COLUMN `min18avg` decimal(20, 2), 
ADD COLUMN `min19` int(11), 
ADD COLUMN `min19avg` decimal(20, 2);

ALTER TABLE `bs_yz`
DROP COLUMN `max`, 
DROP COLUMN `max_avg`, 
ADD COLUMN `top0` int(11), 
ADD COLUMN `top0avg` decimal(20, 2), 
ADD COLUMN `top1` int(11), 
ADD COLUMN `top1avg` decimal(20, 2), 
ADD COLUMN `top2` int(11), 
ADD COLUMN `top2avg` decimal(20, 2), 
ADD COLUMN `top3` int(11), 
ADD COLUMN `top3avg` decimal(20, 2), 
ADD COLUMN `top4` int(11), 
ADD COLUMN `top4avg` decimal(20, 2), 
ADD COLUMN `min7` int(11), 
ADD COLUMN `min7avg` decimal(20, 2), 
ADD COLUMN `min8` int(11), 
ADD COLUMN `min8avg` decimal(20, 2), 
ADD COLUMN `min9` int(11), 
ADD COLUMN `min9avg` decimal(20, 2), 
ADD COLUMN `min10` int(11), 
ADD COLUMN `min10avg` decimal(20, 2), 
ADD COLUMN `min11` int(11), 
ADD COLUMN `min11avg` decimal(20, 2), 
ADD COLUMN `min12` int(11), 
ADD COLUMN `min12avg` decimal(20, 2), 
ADD COLUMN `min13` int(11), 
ADD COLUMN `min13avg` decimal(20, 2), 
ADD COLUMN `min14` int(11), 
ADD COLUMN `min14avg` decimal(20, 2), 
ADD COLUMN `min15` int(11), 
ADD COLUMN `min15avg` decimal(20, 2), 
ADD COLUMN `min16` int(11), 
ADD COLUMN `min16avg` decimal(20, 2), 
ADD COLUMN `min17` int(11), 
ADD COLUMN `min17avg` decimal(20, 2), 
ADD COLUMN `min18` int(11), 
ADD COLUMN `min18avg` decimal(20, 2), 
ADD COLUMN `min19` int(11), 
ADD COLUMN `min19avg` decimal(20, 2);

ALTER TABLE `ds_yz`
DROP COLUMN `max`, 
DROP COLUMN `max_avg`, 
ADD COLUMN `top0` int(11), 
ADD COLUMN `top0avg` decimal(20, 2), 
ADD COLUMN `top1` int(11), 
ADD COLUMN `top1avg` decimal(20, 2), 
ADD COLUMN `top2` int(11), 
ADD COLUMN `top2avg` decimal(20, 2), 
ADD COLUMN `top3` int(11), 
ADD COLUMN `top3avg` decimal(20, 2), 
ADD COLUMN `top4` int(11), 
ADD COLUMN `top4avg` decimal(20, 2), 
ADD COLUMN `min7` int(11), 
ADD COLUMN `min7avg` decimal(20, 2), 
ADD COLUMN `min8` int(11), 
ADD COLUMN `min8avg` decimal(20, 2), 
ADD COLUMN `min9` int(11), 
ADD COLUMN `min9avg` decimal(20, 2), 
ADD COLUMN `min10` int(11), 
ADD COLUMN `min10avg` decimal(20, 2), 
ADD COLUMN `min11` int(11), 
ADD COLUMN `min11avg` decimal(20, 2), 
ADD COLUMN `min12` int(11), 
ADD COLUMN `min12avg` decimal(20, 2), 
ADD COLUMN `min13` int(11), 
ADD COLUMN `min13avg` decimal(20, 2), 
ADD COLUMN `min14` int(11), 
ADD COLUMN `min14avg` decimal(20, 2), 
ADD COLUMN `min15` int(11), 
ADD COLUMN `min15avg` decimal(20, 2), 
ADD COLUMN `min16` int(11), 
ADD COLUMN `min16avg` decimal(20, 2), 
ADD COLUMN `min17` int(11), 
ADD COLUMN `min17avg` decimal(20, 2), 
ADD COLUMN `min18` int(11), 
ADD COLUMN `min18avg` decimal(20, 2), 
ADD COLUMN `min19` int(11), 
ADD COLUMN `min19avg` decimal(20, 2);

ALTER TABLE `ds_zf_yz`
DROP COLUMN `max`, 
DROP COLUMN `max_avg`, 
ADD COLUMN `top0` int(11), 
ADD COLUMN `top0avg` decimal(20, 2), 
ADD COLUMN `top1` int(11), 
ADD COLUMN `top1avg` decimal(20, 2), 
ADD COLUMN `top2` int(11), 
ADD COLUMN `top2avg` decimal(20, 2), 
ADD COLUMN `top3` int(11), 
ADD COLUMN `top3avg` decimal(20, 2), 
ADD COLUMN `top4` int(11), 
ADD COLUMN `top4avg` decimal(20, 2), 
ADD COLUMN `min7` int(11), 
ADD COLUMN `min7avg` decimal(20, 2), 
ADD COLUMN `min8` int(11), 
ADD COLUMN `min8avg` decimal(20, 2), 
ADD COLUMN `min9` int(11), 
ADD COLUMN `min9avg` decimal(20, 2), 
ADD COLUMN `min10` int(11), 
ADD COLUMN `min10avg` decimal(20, 2), 
ADD COLUMN `min11` int(11), 
ADD COLUMN `min11avg` decimal(20, 2), 
ADD COLUMN `min12` int(11), 
ADD COLUMN `min12avg` decimal(20, 2), 
ADD COLUMN `min13` int(11), 
ADD COLUMN `min13avg` decimal(20, 2), 
ADD COLUMN `min14` int(11), 
ADD COLUMN `min14avg` decimal(20, 2), 
ADD COLUMN `min15` int(11), 
ADD COLUMN `min15avg` decimal(20, 2), 
ADD COLUMN `min16` int(11), 
ADD COLUMN `min16avg` decimal(20, 2), 
ADD COLUMN `min17` int(11), 
ADD COLUMN `min17avg` decimal(20, 2), 
ADD COLUMN `min18` int(11), 
ADD COLUMN `min18avg` decimal(20, 2), 
ADD COLUMN `min19` int(11), 
ADD COLUMN `min19avg` decimal(20, 2);

ALTER TABLE `sw_yz`
DROP COLUMN `max`, 
DROP COLUMN `max_avg`, 
ADD COLUMN `top0` int(11), 
ADD COLUMN `top0avg` decimal(20, 2), 
ADD COLUMN `top1` int(11), 
ADD COLUMN `top1avg` decimal(20, 2), 
ADD COLUMN `top2` int(11), 
ADD COLUMN `top2avg` decimal(20, 2), 
ADD COLUMN `top3` int(11), 
ADD COLUMN `top3avg` decimal(20, 2), 
ADD COLUMN `top4` int(11), 
ADD COLUMN `top4avg` decimal(20, 2), 
ADD COLUMN `min7` int(11), 
ADD COLUMN `min7avg` decimal(20, 2), 
ADD COLUMN `min8` int(11), 
ADD COLUMN `min8avg` decimal(20, 2), 
ADD COLUMN `min9` int(11), 
ADD COLUMN `min9avg` decimal(20, 2), 
ADD COLUMN `min10` int(11), 
ADD COLUMN `min10avg` decimal(20, 2), 
ADD COLUMN `min11` int(11), 
ADD COLUMN `min11avg` decimal(20, 2), 
ADD COLUMN `min12` int(11), 
ADD COLUMN `min12avg` decimal(20, 2), 
ADD COLUMN `min13` int(11), 
ADD COLUMN `min13avg` decimal(20, 2), 
ADD COLUMN `min14` int(11), 
ADD COLUMN `min14avg` decimal(20, 2), 
ADD COLUMN `min15` int(11), 
ADD COLUMN `min15avg` decimal(20, 2), 
ADD COLUMN `min16` int(11), 
ADD COLUMN `min16avg` decimal(20, 2), 
ADD COLUMN `min17` int(11), 
ADD COLUMN `min17avg` decimal(20, 2), 
ADD COLUMN `min18` int(11), 
ADD COLUMN `min18avg` decimal(20, 2), 
ADD COLUMN `min19` int(11), 
ADD COLUMN `min19avg` decimal(20, 2);

ALTER TABLE `sw_zf_yz`
DROP COLUMN `max`, 
DROP COLUMN `max_avg`, 
ADD COLUMN `top0` int(11), 
ADD COLUMN `top0avg` decimal(20, 2), 
ADD COLUMN `top1` int(11), 
ADD COLUMN `top1avg` decimal(20, 2), 
ADD COLUMN `top2` int(11), 
ADD COLUMN `top2avg` decimal(20, 2), 
ADD COLUMN `top3` int(11), 
ADD COLUMN `top3avg` decimal(20, 2), 
ADD COLUMN `top4` int(11), 
ADD COLUMN `top4avg` decimal(20, 2), 
ADD COLUMN `min7` int(11), 
ADD COLUMN `min7avg` decimal(20, 2), 
ADD COLUMN `min8` int(11), 
ADD COLUMN `min8avg` decimal(20, 2), 
ADD COLUMN `min9` int(11), 
ADD COLUMN `min9avg` decimal(20, 2), 
ADD COLUMN `min10` int(11), 
ADD COLUMN `min10avg` decimal(20, 2), 
ADD COLUMN `min11` int(11), 
ADD COLUMN `min11avg` decimal(20, 2), 
ADD COLUMN `min12` int(11), 
ADD COLUMN `min12avg` decimal(20, 2), 
ADD COLUMN `min13` int(11), 
ADD COLUMN `min13avg` decimal(20, 2), 
ADD COLUMN `min14` int(11), 
ADD COLUMN `min14avg` decimal(20, 2), 
ADD COLUMN `min15` int(11), 
ADD COLUMN `min15avg` decimal(20, 2), 
ADD COLUMN `min16` int(11), 
ADD COLUMN `min16avg` decimal(20, 2), 
ADD COLUMN `min17` int(11), 
ADD COLUMN `min17avg` decimal(20, 2), 
ADD COLUMN `min18` int(11), 
ADD COLUMN `min18avg` decimal(20, 2), 
ADD COLUMN `min19` int(11), 
ADD COLUMN `min19avg` decimal(20, 2);

ALTER TABLE `zs_yz`
DROP COLUMN `max`, 
DROP COLUMN `max_avg`, 
ADD COLUMN `top0` int(11), 
ADD COLUMN `top0avg` decimal(20, 2), 
ADD COLUMN `top1` int(11), 
ADD COLUMN `top1avg` decimal(20, 2), 
ADD COLUMN `top2` int(11), 
ADD COLUMN `top2avg` decimal(20, 2), 
ADD COLUMN `top3` int(11), 
ADD COLUMN `top3avg` decimal(20, 2), 
ADD COLUMN `top4` int(11), 
ADD COLUMN `top4avg` decimal(20, 2), 
ADD COLUMN `min7` int(11), 
ADD COLUMN `min7avg` decimal(20, 2), 
ADD COLUMN `min8` int(11), 
ADD COLUMN `min8avg` decimal(20, 2), 
ADD COLUMN `min9` int(11), 
ADD COLUMN `min9avg` decimal(20, 2), 
ADD COLUMN `min10` int(11), 
ADD COLUMN `min10avg` decimal(20, 2), 
ADD COLUMN `min11` int(11), 
ADD COLUMN `min11avg` decimal(20, 2), 
ADD COLUMN `min12` int(11), 
ADD COLUMN `min12avg` decimal(20, 2), 
ADD COLUMN `min13` int(11), 
ADD COLUMN `min13avg` decimal(20, 2), 
ADD COLUMN `min14` int(11), 
ADD COLUMN `min14avg` decimal(20, 2), 
ADD COLUMN `min15` int(11), 
ADD COLUMN `min15avg` decimal(20, 2), 
ADD COLUMN `min16` int(11), 
ADD COLUMN `min16avg` decimal(20, 2), 
ADD COLUMN `min17` int(11), 
ADD COLUMN `min17avg` decimal(20, 2), 
ADD COLUMN `min18` int(11), 
ADD COLUMN `min18avg` decimal(20, 2), 
ADD COLUMN `min19` int(11), 
ADD COLUMN `min19avg` decimal(20, 2);