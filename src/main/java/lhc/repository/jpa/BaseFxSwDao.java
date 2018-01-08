package lhc.repository.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import lhc.domain.FxSw;
import lhc.util.QueryUtil;

@Transactional
public abstract class BaseFxSwDao<T extends FxSw> extends BaseYzDao<T> {
	@Async
	public Future<Exception> countReds(int startForwardFromYear, int startForwardFromPhase, int perSize) {
		Exception t = null;
		try {
			List<Object> args = new ArrayList<Object>();
			StringBuilder sql = new StringBuilder();
			sql.append("update " + getTableName() + " set").append("\n");

			sql.append("red_counts_for_sx=(").append("\n");
			sql.append("select count(case when t.sx=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, sx from " + getTableName()).append("\n");
			sql.append("where sx=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_sxzf=(").append("\n");
			sql.append("select count(case when t.sxzf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, sxzf from " + getTableName()).append("\n");
			sql.append("where sxzf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_ds=(").append("\n");
			sql.append("select count(case when t.ds=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, ds from " + getTableName()).append("\n");
			sql.append("where ds=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_dszf=(").append("\n");
			sql.append("select count(case when t.dszf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, dszf from " + getTableName()).append("\n");
			sql.append("where dszf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_sw=(").append("\n");
			sql.append("select count(case when t.sw=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, sw from " + getTableName()).append("\n");
			sql.append("where sw=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_swzf=(").append("\n");
			sql.append("select count(case when t.swzf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, swzf from " + getTableName()).append("\n");
			sql.append("where swzf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_mw=(").append("\n");
			sql.append("select count(case when t.mw=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, mw from " + getTableName()).append("\n");
			sql.append("where mw=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_mwzf=(").append("\n");
			sql.append("select count(case when t.mwzf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, mwzf from " + getTableName()).append("\n");
			sql.append("where mwzf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_lh=(").append("\n");
			sql.append("select count(case when t.lh=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, lh from " + getTableName()).append("\n");
			sql.append("where lh=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_lhzf=(").append("\n");
			sql.append("select count(case when t.lhzf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, lhzf from " + getTableName()).append("\n");
			sql.append("where lhzf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_bs=(").append("\n");
			sql.append("select count(case when t.bs=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, bs from " + getTableName()).append("\n");
			sql.append("where bs=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_bszf=(").append("\n");
			sql.append("select count(case when t.bszf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, bszf from " + getTableName()).append("\n");
			sql.append("where bszf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zs=(").append("\n");
			sql.append("select count(case when t.zs=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zs from " + getTableName()).append("\n");
			sql.append("where zs=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zszf=(").append("\n");
			sql.append("select count(case when t.zszf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zszf from " + getTableName()).append("\n");
			sql.append("where zszf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_wx=(").append("\n");
			sql.append("select count(case when t.wx=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, wx from " + getTableName()).append("\n");
			sql.append("where wx=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_wxzf=(").append("\n");
			sql.append("select count(case when t.wxzf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, wxzf from " + getTableName()).append("\n");
			sql.append("where wxzf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_wxds=(").append("\n");
			sql.append("select count(case when t.wxds=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, wxds from " + getTableName()).append("\n");
			sql.append("where wxds=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_wxdszf=(").append("\n");
			sql.append("select count(case when t.wxdszf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, wxdszf from " + getTableName()).append("\n");
			sql.append("where wxdszf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_pd=(").append("\n");
			sql.append("select count(case when t.pd=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, pd from " + getTableName()).append("\n");
			sql.append("where pd=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_pdzf=(").append("\n");
			sql.append("select count(case when t.pdzf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, pdzf from " + getTableName()).append("\n");
			sql.append("where pdzf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_fd=(").append("\n");
			sql.append("select count(case when t.fd=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, fd from " + getTableName()).append("\n");
			sql.append("where fd=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_fdzf=(").append("\n");
			sql.append("select count(case when t.fdzf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, fdzf from " + getTableName()).append("\n");
			sql.append("where fdzf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_qq=(").append("\n");
			sql.append("select count(case when t.qq=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, qq from " + getTableName()).append("\n");
			sql.append("where qq=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_qqzf=(").append("\n");
			sql.append("select count(case when t.qqzf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, qqzf from " + getTableName()).append("\n");
			sql.append("where qqzf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_qiw=(").append("\n");
			sql.append("select count(case when t.qiw=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, qiw from " + getTableName()).append("\n");
			sql.append("where qiw=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_qiwzf=(").append("\n");
			sql.append("select count(case when t.qiwzf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, qiwzf from " + getTableName()).append("\n");
			sql.append("where qiwzf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_twelve=(").append("\n");
			sql.append("select count(case when t.twelve=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, twelve from " + getTableName()).append("\n");
			sql.append("where twelve=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_twelvezf=(").append("\n");
			sql.append("select count(case when t.twelvezf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, twelvezf from " + getTableName()).append("\n");
			sql.append("where twelvezf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_slq=(").append("\n");
			sql.append("select count(case when t.slq=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, slq from " + getTableName()).append("\n");
			sql.append("where slq=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_slqzf=(").append("\n");
			sql.append("select count(case when t.slqzf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, slqzf from " + getTableName()).append("\n");
			sql.append("where slqzf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx1=(").append("\n");
			sql.append("select count(case when t.zx1=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx1 from " + getTableName()).append("\n");
			sql.append("where zx1=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx1zf=(").append("\n");
			sql.append("select count(case when t.zx1zf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx1zf from " + getTableName()).append("\n");
			sql.append("where zx1zf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx2=(").append("\n");
			sql.append("select count(case when t.zx2=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx2 from " + getTableName()).append("\n");
			sql.append("where zx2=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx2zf=(").append("\n");
			sql.append("select count(case when t.zx2zf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx2zf from " + getTableName()).append("\n");
			sql.append("where zx2zf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx3=(").append("\n");
			sql.append("select count(case when t.zx3=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx3 from " + getTableName()).append("\n");
			sql.append("where zx3=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx3zf=(").append("\n");
			sql.append("select count(case when t.zx3zf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx3zf from " + getTableName()).append("\n");
			sql.append("where zx3zf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx4=(").append("\n");
			sql.append("select count(case when t.zx4=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx4 from " + getTableName()).append("\n");
			sql.append("where zx4=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx4zf=(").append("\n");
			sql.append("select count(case when t.zx4zf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx4zf from " + getTableName()).append("\n");
			sql.append("where zx4zf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx5=(").append("\n");
			sql.append("select count(case when t.zx5=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx5 from " + getTableName()).append("\n");
			sql.append("where zx5=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx5zf=(").append("\n");
			sql.append("select count(case when t.zx5zf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx5zf from " + getTableName()).append("\n");
			sql.append("where zx5zf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx6=(").append("\n");
			sql.append("select count(case when t.zx6=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx6 from " + getTableName()).append("\n");
			sql.append("where zx6=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx6zf=(").append("\n");
			sql.append("select count(case when t.zx6zf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx6zf from " + getTableName()).append("\n");
			sql.append("where zx6zf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx7=(").append("\n");
			sql.append("select count(case when t.zx7=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx7 from " + getTableName()).append("\n");
			sql.append("where zx7=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx7zf=(").append("\n");
			sql.append("select count(case when t.zx7zf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx7zf from " + getTableName()).append("\n");
			sql.append("where zx7zf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx8=(").append("\n");
			sql.append("select count(case when t.zx8=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx8 from " + getTableName()).append("\n");
			sql.append("where zx8=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx8zf=(").append("\n");
			sql.append("select count(case when t.zx8zf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx8zf from " + getTableName()).append("\n");
			sql.append("where zx8zf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx9=(").append("\n");
			sql.append("select count(case when t.zx9=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx9 from " + getTableName()).append("\n");
			sql.append("where zx9=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx9zf=(").append("\n");
			sql.append("select count(case when t.zx9zf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx9zf from " + getTableName()).append("\n");
			sql.append("where zx9zf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx10=(").append("\n");
			sql.append("select count(case when t.zx10=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx10 from " + getTableName()).append("\n");
			sql.append("where zx10=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx10zf=(").append("\n");
			sql.append("select count(case when t.zx10zf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx10zf from " + getTableName()).append("\n");
			sql.append("where zx10zf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx11=(").append("\n");
			sql.append("select count(case when t.zx11=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx11 from " + getTableName()).append("\n");
			sql.append("where zx11=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx11zf=(").append("\n");
			sql.append("select count(case when t.zx11zf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx11zf from " + getTableName()).append("\n");
			sql.append("where zx11zf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx12=(").append("\n");
			sql.append("select count(case when t.zx12=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx12 from " + getTableName()).append("\n");
			sql.append("where zx12=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx12zf=(").append("\n");
			sql.append("select count(case when t.zx12zf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx12zf from " + getTableName()).append("\n");
			sql.append("where zx12zf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx13=(").append("\n");
			sql.append("select count(case when t.zx13=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx13 from " + getTableName()).append("\n");
			sql.append("where zx13=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx13zf=(").append("\n");
			sql.append("select count(case when t.zx13zf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx13zf from " + getTableName()).append("\n");
			sql.append("where zx13zf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx14=(").append("\n");
			sql.append("select count(case when t.zx14=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx14 from " + getTableName()).append("\n");
			sql.append("where zx14=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx14zf=(").append("\n");
			sql.append("select count(case when t.zx14zf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx14zf from " + getTableName()).append("\n");
			sql.append("where zx14zf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx15=(").append("\n");
			sql.append("select count(case when t.zx15=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx15 from " + getTableName()).append("\n");
			sql.append("where zx15=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx15zf=(").append("\n");
			sql.append("select count(case when t.zx15zf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx15zf from " + getTableName()).append("\n");
			sql.append("where zx15zf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx16=(").append("\n");
			sql.append("select count(case when t.zx16=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx16 from " + getTableName()).append("\n");
			sql.append("where zx16=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx16zf=(").append("\n");
			sql.append("select count(case when t.zx16zf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx16zf from " + getTableName()).append("\n");
			sql.append("where zx16zf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx17=(").append("\n");
			sql.append("select count(case when t.zx17=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx17 from " + getTableName()).append("\n");
			sql.append("where zx17=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx17zf=(").append("\n");
			sql.append("select count(case when t.zx17zf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx17zf from " + getTableName()).append("\n");
			sql.append("where zx17zf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx18=(").append("\n");
			sql.append("select count(case when t.zx18=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx18 from " + getTableName()).append("\n");
			sql.append("where zx18=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append("),").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("red_counts_for_zx18zf=(").append("\n");
			sql.append("select count(case when t.zx18zf=0 then 1 end) as num").append("\n");
			sql.append("from (").append("\n");
			sql.append("select id, zx18zf from " + getTableName()).append("\n");
			sql.append("where zx18zf=0").append("\n");
			sql.append("and date<=(select date from " + getTableName() + " where year=? and phase=?)").append("\n");
			sql.append("order by date desc limit 0, " + perSize).append("\n");
			sql.append(") as t").append("\n");
			sql.append(")").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			sql.append("where year=? and phase=?").append("\n");
			args.add(startForwardFromYear);
			args.add(startForwardFromPhase);

			Query query = em.createNativeQuery(sql.toString());
			QueryUtil.setArgs(args, query);
			query.executeUpdate();
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);

	}
}
