--liquibase formatted sql

--changeset lhc:86

CREATE TABLE `fx_sw1_yz` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`year` int(11) NOT NULL,
	`phase` int(11) NOT NULL,
	`date` varchar(20) NOT NULL,
	`sx` int(11),
	`sxDW` varchar(20),
	`sx_nums` varchar(255),
	`sxzf` int(11),
	`sxzfDW` varchar(20),
	`sxzf_nums` varchar(255),
	`ds` int(11),
	`dsDW` varchar(20),
	`ds_nums` varchar(255),
	`dszf` int(11),
	`dszfDW` varchar(20),
	`dszf_nums` varchar(255),
	`sw` int(11),
	`swDW` varchar(20),
	`sw_nums` varchar(255),
	`swzf` int(11),
	`swzfDW` varchar(20),
	`swzf_nums` varchar(255),
	`mw` int(11),
	`mwDW` varchar(20),
	`mw_nums` varchar(255),
	`mwzf` int(11),
	`mwzfDW` varchar(20),
	`mwzf_nums` varchar(255),
	`lh` int(11),
	`lhDW` varchar(20),
	`lh_nums` varchar(255),
	`lhzf` int(11),
	`lhzfDW` varchar(20),
	`lhzf_nums` varchar(255),
	`bs` int(11),
	`bsDW` varchar(20),
	`bs_nums` varchar(255),
	`bszf` int(11),
	`bszfDW` varchar(20),
	`bszf_nums` varchar(255),
	`zs` int(11),
	`zsDW` varchar(20),
	`zs_nums` varchar(255),
	`zszf` int(11),
	`zszfDW` varchar(20),
	`zszf_nums` varchar(255),
	`wx` int(11),
	`wxDW` varchar(20),
	`wx_nums` varchar(255),
	`wxzf` int(11),
	`wxzfDW` varchar(20),
	`wxzf_nums` varchar(255),
	`wxds` int(11),
	`wxdsDW` varchar(20),
	`wxds_nums` varchar(255),
	`wxdszf` int(11),
	`wxdszfDW` varchar(20),
	`wxdszf_nums` varchar(255),
	`pd` int(11),
	`pdDW` varchar(20),
	`pd_nums` varchar(255),
	`pdzf` int(11),
	`pdzfDW` varchar(20),
	`pdzf_nums` varchar(255),
	`fd` int(11),
	`fdDW` varchar(20),
	`fd_nums` varchar(255),
	`fdzf` int(11),
	`fdzfDW` varchar(20),
	`fdzf_nums` varchar(255),
	`qq` int(11),
	`qqDW` varchar(20),
	`qq_nums` varchar(255),
	`qqzf` int(11),
	`qqzfDW` varchar(20),
	`qqzf_nums` varchar(255),
	`qiw` int(11),
	`qiwDW` varchar(20),
	`qiw_nums` varchar(255),
	`qiwzf` int(11),
	`qiwzfDW` varchar(20),
	`qiwzf_nums` varchar(255),
	`twelve` int(11),
	`twelveDW` varchar(20),
	`twelve_nums` varchar(255),
	`twelvezf` int(11),
	`twelvezfDW` varchar(20),
	`twelvezf_nums` varchar(255),
	`slq` int(11),
	`slqDW` varchar(20),
	`slq_nums` varchar(255),
	`slqzf` int(11),
	`slqzfDW` varchar(20),
	`slqzf_nums` varchar(255),
	`zx1` int(11),
	`zx1DW` varchar(20),
	`zx1nums` varchar(255),
	`zx1zf` int(11),
	`zx1zfDW` varchar(20),
	`zx1zf_nums` varchar(255),
	`zx2` int(11),
	`zx2DW` varchar(20),
	`zx2nums` varchar(255),
	`zx2zf` int(11),
	`zx2zfDW` varchar(20),
	`zx2zf_nums` varchar(255),
	`zx3` int(11),
	`zx3DW` varchar(20),
	`zx3nums` varchar(255),
	`zx3zf` int(11),
	`zx3zfDW` varchar(20),
	`zx3zf_nums` varchar(255),
	`zx4` int(11),
	`zx4DW` varchar(20),
	`zx4nums` varchar(255),
	`zx4zf` int(11),
	`zx4zfDW` varchar(20),
	`zx4zf_nums` varchar(255),
	`zx5` int(11),
	`zx5DW` varchar(20),
	`zx5nums` varchar(255),
	`zx5zf` int(11),
	`zx5zfDW` varchar(20),
	`zx5zf_nums` varchar(255),
	`zx6` int(11),
	`zx6DW` varchar(20),
	`zx6nums` varchar(255),
	`zx6zf` int(11),
	`zx6zfDW` varchar(20),
	`zx6zf_nums` varchar(255),
	`zx7` int(11),
	`zx7DW` varchar(20),
	`zx7nums` varchar(255),
	`zx7zf` int(11),
	`zx7zfDW` varchar(20),
	`zx7zf_nums` varchar(255),
	`zx8` int(11),
	`zx8DW` varchar(20),
	`zx8nums` varchar(255),
	`zx8zf` int(11),
	`zx8zfDW` varchar(20),
	`zx8zf_nums` varchar(255),
	`zx9` int(11),
	`zx9DW` varchar(20),
	`zx9nums` varchar(255),
	`zx9zf` int(11),
	`zx9zfDW` varchar(20),
	`zx9zf_nums` varchar(255),
	`zx10` int(11),
	`zx10DW` varchar(20),
	`zx10nums` varchar(255),
	`zx10zf` int(11),
	`zx10zfDW` varchar(20),
	`zx10zf_nums` varchar(255),
	`zx11` int(11),
	`zx11DW` varchar(20),
	`zx11nums` varchar(255),
	`zx11zf` int(11),
	`zx11zfDW` varchar(20),
	`zx11zf_nums` varchar(255),
	`zx12` int(11),
	`zx12DW` varchar(20),
	`zx12nums` varchar(255),
	`zx12zf` int(11),
	`zx12zfDW` varchar(20),
	`zx12zf_nums` varchar(255),
	`zx13` int(11),
	`zx13DW` varchar(20),
	`zx13nums` varchar(255),
	`zx13zf` int(11),
	`zx13zfDW` varchar(20),
	`zx13zf_nums` varchar(255),
	`zx14` int(11),
	`zx14DW` varchar(20),
	`zx14nums` varchar(255),
	`zx14zf` int(11),
	`zx14zfDW` varchar(20),
	`zx14zf_nums` varchar(255),
	`zx15` int(11),
	`zx15DW` varchar(20),
	`zx15nums` varchar(255),
	`zx15zf` int(11),
	`zx15zfDW` varchar(20),
	`zx15zf_nums` varchar(255),
	`zx16` int(11),
	`zx16DW` varchar(20),
	`zx16nums` varchar(255),
	`zx16zf` int(11),
	`zx16zfDW` varchar(20),
	`zx16zf_nums` varchar(255),
	`zx17` int(11),
	`zx17DW` varchar(20),
	`zx17nums` varchar(255),
	`zx17zf` int(11),
	`zx17zfDW` varchar(20),
	`zx17zf_nums` varchar(255),
	`zx18` int(11),
	`zx18DW` varchar(20),
	`zx18nums` varchar(255),
	`zx18zf` int(11),
	`zx18zfDW` varchar(20),
	`zx18zf_nums` varchar(255),
	PRIMARY KEY (`id`),
	UNIQUE `uidx_fx_sw1_yz_date` (`date`)
);
