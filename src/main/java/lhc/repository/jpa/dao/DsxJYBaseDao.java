package lhc.repository.jpa.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import lhc.dto.DsxJYCondition;
import lhc.dto.DsxJYViewBean;
import lhc.dto.query.PageInfo;
import lhc.dto.query.PageResult;
import lhc.dto.query.QueryInfo;
import lhc.dto.query.SortInfo;
import lhc.enums.SortOrder;
import lhc.util.DatabaseUtil;
import lhc.util.QueryUtil;

@SuppressWarnings("unchecked")
public abstract class DsxJYBaseDao {

	@Autowired
	protected EntityManager em;

	protected abstract String getTableName();

	public PageResult<DsxJYViewBean> query(QueryInfo<DsxJYCondition> queryInfo) {
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
			if (!queryInfo.getObject().isQc()) {
				sql.append("d.c1nums_xc as c1n,").append("\n");
				sql.append("d.c2nums_xc as c2n,").append("\n");
				sql.append("d.c3nums_xc as c3n,").append("\n");
				sql.append("d.c4nums_xc as c4n,").append("\n");
				sql.append("d.c5nums_xc as c5n,").append("\n");
				sql.append("d.c6nums_xc as c6n,").append("\n");
				sql.append("d.c7nums_xc as c7n,").append("\n");
				sql.append("d.c8nums_xc as c8n,").append("\n");
				sql.append("d.c9nums_xc as c9n,").append("\n");
				sql.append("d.c10nums_xc as c10n,").append("\n");
				sql.append("d.c11nums_xc as c11n").append("\n");
			} else if (queryInfo.getObject().isReverse()) {
				sql.append("d.c1nums_reverse as c1n,").append("\n");
				sql.append("d.c2nums_reverse as c2n,").append("\n");
				sql.append("d.c3nums_reverse as c3n,").append("\n");
				sql.append("d.c4nums_reverse as c4n,").append("\n");
				sql.append("d.c5nums_reverse as c5n,").append("\n");
				sql.append("d.c6nums_reverse as c6n,").append("\n");
				sql.append("d.c7nums_reverse as c7n,").append("\n");
				sql.append("d.c8nums_reverse as c8n,").append("\n");
				sql.append("d.c9nums_reverse as c9n,").append("\n");
				sql.append("d.c10nums_reverse as c10n,").append("\n");
				sql.append("d.c11nums_reverse as c11n").append("\n");
			} else {
				sql.append("d.c1nums as c1n,").append("\n");
				sql.append("d.c2nums as c2n,").append("\n");
				sql.append("d.c3nums as c3n,").append("\n");
				sql.append("d.c4nums as c4n,").append("\n");
				sql.append("d.c5nums as c5n,").append("\n");
				sql.append("d.c6nums as c6n,").append("\n");
				sql.append("d.c7nums as c7n,").append("\n");
				sql.append("d.c8nums as c8n,").append("\n");
				sql.append("d.c9nums as c9n,").append("\n");
				sql.append("d.c10nums as c10n,").append("\n");
				sql.append("d.c11nums as c11n").append("\n");
			}
			sql.append(condition);
			if (pageRequest != null) {
				QueryUtil.setOrder(sql, pageRequest);
			}
			Query query = em.createNativeQuery(sql.toString(), "DsxJY");
			QueryUtil.setArgs(args, query);
			if (pageRequest != null) {
				QueryUtil.setPage(pageRequest, query);
			} else {
				queryInfo.setPageInfo(new PageInfo(1, 1));
			}
			List<DsxJYViewBean> list = query.getResultList();
			if (queryInfo.isToReverse()) {
				Collections.reverse(list);
			}
			return new PageResult<DsxJYViewBean>(list, count, queryInfo.getPageInfo());
		}
		return new PageResult<DsxJYViewBean>(new ArrayList<DsxJYViewBean>(), 0, queryInfo.getPageInfo());
	}
}
