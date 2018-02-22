package lhc.repository.jpa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import lhc.dto.DmgJYViewBean;
import lhc.dto.DsxJYCondition;
import lhc.dto.query.PageInfo;
import lhc.dto.query.PageResult;
import lhc.dto.query.QueryInfo;
import lhc.dto.query.SortInfo;
import lhc.enums.SortOrder;
import lhc.util.DatabaseUtil;
import lhc.util.QueryUtil;

@SuppressWarnings("unchecked")
public abstract class BaseDmgJYDao {

	@Autowired
	protected EntityManager em;

	protected abstract String getTableName();

	public PageResult<DmgJYViewBean> query(QueryInfo<DsxJYCondition> queryInfo) {
		PageRequest pageRequest = null;
		if (queryInfo.getPageInfo() != null) {
			if (!queryInfo.getPageInfo().isToSort()) {
				List<SortInfo> sorts = new ArrayList<SortInfo>();
				sorts.add(new SortInfo("d.date", SortOrder.DESC));
				queryInfo.getPageInfo().setSorts(sorts);
			}
			pageRequest = queryInfo.getPageInfo().toPageRequest();
		}
		StringBuilder condition = new StringBuilder();
		condition.append("from " + getTableName() + " d").append("\n");
		List<Object> args = new ArrayList<Object>();
		if (queryInfo.getObject() != null) {
			condition.append(", (select yp.date from " + getTableName() + " yp").append("\n");
			condition.append("where yp.year = ?").append("\n");
			condition.append("and yp.phase = ?) t").append("\n");
			DsxJYCondition yz = queryInfo.getObject();
			args.add(yz.getYear());
			args.add(yz.getPhase());
		}
		condition.append("where year > 0").append("\n");
		if (queryInfo.getObject() != null) {
			condition.append("and d.date<=t.date").append("\n");
		}
		StringBuilder countSql = new StringBuilder("select count(d.id)");
		countSql.append("\n").append(condition);
		Query countQuery = em.createNativeQuery(countSql.toString());
		QueryUtil.setArgs(args, countQuery);
		long count = DatabaseUtil.getCount(countQuery.getSingleResult());
		if (count > 0) {
			StringBuilder sql = new StringBuilder("select d.year, d.phase, d.date, d.special_num as specialNum, ");
			if (queryInfo.getObject().isReverse()) {
				sql.append("d.ae_nums_reverse as aen,").append("\n");
				sql.append("d.bf_nums_reverse as bfn,").append("\n");
				sql.append("d.cg_nums_reverse as cgn,").append("\n");
				sql.append("d.dh_nums_reverse as dhn").append("\n");
			} else {
				sql.append("d.ae_nums as aen,").append("\n");
				sql.append("d.bf_nums as bfn,").append("\n");
				sql.append("d.cg_nums as cgn,").append("\n");
				sql.append("d.dh_nums as dhn").append("\n");
			}
			sql.append(condition);
			if (pageRequest != null) {
				QueryUtil.setOrder(sql, pageRequest);
			}
			Query query = em.createNativeQuery(sql.toString(), "DmgJY");
			QueryUtil.setArgs(args, query);
			if (pageRequest != null) {
				QueryUtil.setPage(pageRequest, query);
			} else {
				queryInfo.setPageInfo(new PageInfo(1, 1));
			}
			List<DmgJYViewBean> list = query.getResultList();
			if (queryInfo.isToReverse()) {
				Collections.reverse(list);
			}
			return new PageResult<DmgJYViewBean>(list, count, queryInfo.getPageInfo());
		}
		return new PageResult<DmgJYViewBean>(new ArrayList<DmgJYViewBean>(), 0, queryInfo.getPageInfo());
	}

	public DmgJYViewBean findLatestOne(QueryInfo<DsxJYCondition> queryInfo) {
		StringBuilder sql = new StringBuilder("select d.year, d.phase, d.date, d.special_num as specialNum, ");
		if (queryInfo.getObject().isReverse()) {
			sql.append("d.ae_nums_reverse as aen,").append("\n");
			sql.append("d.bf_nums_reverse as bfn,").append("\n");
			sql.append("d.cg_nums_reverse as cgn,").append("\n");
			sql.append("d.dh_nums_reverse as dhn").append("\n");
		} else {
			sql.append("d.ae_nums as aen,").append("\n");
			sql.append("d.bf_nums as bfn,").append("\n");
			sql.append("d.cg_nums as cgn,").append("\n");
			sql.append("d.dh_nums as dhn").append("\n");
		}
		sql.append("from " + getTableName() + " d").append("\n");
		sql.append("where year=0 and phase=0").append("\n");
		Query query = em.createNativeQuery(sql.toString(), "DmgJY");
		List<DmgJYViewBean> list = query.getResultList();
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
}
