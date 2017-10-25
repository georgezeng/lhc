package lhc.repository.jpa.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import lhc.dto.J0Yz;
import lhc.dto.query.PageInfo;
import lhc.dto.query.PageResult;
import lhc.dto.query.QueryInfo;
import lhc.dto.query.SortInfo;
import lhc.enums.SortOrder;
import lhc.util.DatabaseUtil;
import lhc.util.QueryUtil;

@Repository
@SuppressWarnings("unchecked")
public class CommonDao {
	@Autowired
	private EntityManager em;

	public PageResult<J0Yz> getTop0List(QueryInfo<J0Yz> queryInfo) {
		PageRequest pageRequest = null;
		if (queryInfo.getPageInfo() != null) {
			if (!queryInfo.getPageInfo().isToSort()) {
				List<SortInfo> sorts = new ArrayList<SortInfo>();
				sorts.add(new SortInfo("sx.date", SortOrder.DESC));
				queryInfo.getPageInfo().setSorts(sorts);
			}
			pageRequest = queryInfo.getPageInfo().toPageRequest();
		}
		List<Object> args = new ArrayList<Object>();
		StringBuilder condition = new StringBuilder();
		condition.append("from").append("\n");
		if (queryInfo.getObject() != null) {
			condition.append("(select yp.date from sx_yz yp").append("\n");
			condition.append("where yp.year = ?").append("\n");
			condition.append("and yp.phase = ?) t,").append("\n");
			J0Yz yz = queryInfo.getObject();
			args.add(yz.getYear());
			args.add(yz.getPhase());
		}
		condition.append("sx_yz sx").append("\n");
		condition.append("left join sx_zf_yz2 sxzf on sx.date=sxzf.date").append("\n");
		condition.append("left join ds_yz ds on sx.date=ds.date").append("\n");
		condition.append("left join ds_zf_yz dszf on sx.date=dszf.date").append("\n");
		condition.append("left join sw_yz sw on sx.date=sw.date").append("\n");
		condition.append("left join sw_zf_yz swzf on sx.date=swzf.date").append("\n");
		condition.append("left join mw_yz mw on sx.date=mw.date").append("\n");
		condition.append("left join mw_zf_yz mwzf on sx.date=mwzf.date").append("\n");
		condition.append("left join lh_yz lh on sx.date=lh.date").append("\n");
		condition.append("left join lh_zf_yz lhzf on sx.date=lhzf.date").append("\n");
		condition.append("left join bs9q_yz bs on sx.date=bs.date").append("\n");
		condition.append("left join bs9q_zf_yz bszf on sx.date=bszf.date").append("\n");
		condition.append("left join zs_yz zs on sx.date=zs.date").append("\n");
		condition.append("left join zs_zf_yz zszf on sx.date=zszf.date").append("\n");
		condition.append("left join wx_yz wx on sx.date=wx.date").append("\n");
		condition.append("left join wx_zf_yz wxzf on sx.date=wxzf.date").append("\n");
		condition.append("left join wxds_yz wxds on sx.date=wxds.date").append("\n");
		condition.append("left join wxds_zf_yz wxdszf on sx.date=wxdszf.date").append("\n");
		condition.append("left join pd_yz pd on sx.date=pd.date").append("\n");
		condition.append("left join pd_zf_yz pdzf on sx.date=pdzf.date").append("\n");
		condition.append("left join tm12fd_yz tm12fd on sx.date=tm12fd.date").append("\n");
		condition.append("left join tm12fd_zf_yz tm12fdzf on sx.date=tm12fdzf.date").append("\n");
		condition.append("left join qq_yz qq on sx.date=qq.date").append("\n");
		condition.append("left join qq_zf_yz qqzf on sx.date=qqzf.date").append("\n");
		condition.append("left join qiw_yz qiw on sx.date=qiw.date").append("\n");
		condition.append("left join qiw_zf_yz qiwzf on sx.date=qiwzf.date").append("\n");
		condition.append("left join twelve_yz twelve on sx.date=twelve.date").append("\n");
		condition.append("left join twelve_zf_yz twelvezf on sx.date=twelvezf.date").append("\n");
		condition.append("left join slq_yz slq on sx.date=slq.date").append("\n");
		condition.append("left join slq_zf_yz slqzf on sx.date=slqzf.date").append("\n");
		condition.append("where 1=1").append("\n");
		if (queryInfo.getObject() != null) {
			condition.append("and sx.date<=t.date").append("\n");
		}
		StringBuilder countSql = new StringBuilder("select count(sx.date)");
		countSql.append("\n").append(condition);
		Query countQuery = em.createNativeQuery(countSql.toString());
		QueryUtil.setArgs(args, countQuery);
		long count = DatabaseUtil.getCount(countQuery.getSingleResult());
		if (count > 0) {
			StringBuilder sql = new StringBuilder("select sx.date, sx.year, sx.phase,").append("\n");
			sql.append("sx.min0 as sxj0, sxzf.min0 as sxzfj0,").append("\n");
			sql.append("ds.min0 as dsj0, dszf.min0 as dszfj0,").append("\n");
			sql.append("sw.min0 as swj0, swzf.min0 as swzfj0,").append("\n");
			sql.append("mw.min0 as mwj0, mwzf.min0 as mwzfj0,").append("\n");
			sql.append("lh.min0 as lhj0, lhzf.min0 as lhzfj0,").append("\n");
			sql.append("bs.min0 as bsj0, bszf.min0 as bszfj0,").append("\n");
			sql.append("zs.min0 as zsj0, zszf.min0 as zszfj0,").append("\n");
			sql.append("wx.min0 as wxj0, wxzf.min0 as wxzfj0,").append("\n");
			sql.append("wxds.min0 as wxdsj0, wxdszf.min0 as wxdszfj0,").append("\n");
			sql.append("pd.min0 as pdj0, pdzf.min0 as pdzfj0,").append("\n");
			sql.append("tm12fd.min0 as fdj0, tm12fdzf.min0 as fdzfj0,").append("\n");
			sql.append("qq.min0 as qqj0, qqzf.min0 as qqzfj0,").append("\n");
			sql.append("qiw.min0 as qiwj0, qiwzf.min0 as qiwzfj0,").append("\n");
			sql.append("twelve.min0 as twelvej0, twelvezf.min0 as twelvezfj0,").append("\n");
			sql.append("slq.min0 as slqj0, slqzf.min0 as slqzfj0").append("\n");
			sql.append(condition).append("\n");
			if (pageRequest != null) {
				QueryUtil.setOrder(sql, pageRequest);
			}
			Query query = em.createNativeQuery(sql.toString(), "J0Yz");
			QueryUtil.setArgs(args, query);
			if (pageRequest != null) {
				QueryUtil.setPage(pageRequest, query);
			} else {
				queryInfo.setPageInfo(new PageInfo(1, 1));
			}
			List<J0Yz> list = query.getResultList();
			if (queryInfo.isToReverse()) {
				Collections.reverse(list);
			}
			return new PageResult<J0Yz>(list, count, queryInfo.getPageInfo());
		}
		return new PageResult<J0Yz>(new ArrayList<J0Yz>(), 0, queryInfo.getPageInfo());
	}
}
