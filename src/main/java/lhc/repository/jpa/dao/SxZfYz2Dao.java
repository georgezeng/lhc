package lhc.repository.jpa.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import lhc.domain.SxZfYz2;
import lhc.dto.query.PageInfo;
import lhc.dto.query.PageResult;
import lhc.dto.query.QueryInfo;
import lhc.dto.query.SortInfo;
import lhc.enums.SortOrder;
import lhc.util.DatabaseUtil;
import lhc.util.QueryUtil;

@Repository
@SuppressWarnings("unchecked")
public class SxZfYz2Dao {
	@Autowired
	private EntityManager em;

	public PageResult<SxZfYz2> query(QueryInfo<SxZfYz2> queryInfo) {
		if (queryInfo.getPageInfo() != null && !queryInfo.getPageInfo().isToSort()) {
			List<SortInfo> sorts = new ArrayList<SortInfo>();
			sorts.add(new SortInfo("d.date", SortOrder.DESC));
			queryInfo.getPageInfo().setSorts(sorts);
		}
		PageRequest pageRequest = queryInfo.getPageInfo().toPageRequest();
		StringBuilder condition = new StringBuilder();
		condition.append("from sx_zf_yz2 d").append("\n");
		List<Object> args = new ArrayList<Object>();
		if (queryInfo.getObject() != null) {
			condition.append(", (select yp.date from sx_zf_yz2 yp").append("\n");
			condition.append("where yp.year = ?").append("\n");
			condition.append("and yp.phase = ?) t").append("\n");
			SxZfYz2 yz = queryInfo.getObject();
			args.add(yz.getYear());
			args.add(yz.getPhase());
		}
		condition.append("where 1=1").append("\n");
		if (queryInfo.getObject() != null) {
			condition.append("and d.date<=t.date").append("\n");
		}
		StringBuilder countSql = new StringBuilder("select count(d.id)");
		countSql.append("\n").append(condition);
		Query countQuery = em.createNativeQuery(countSql.toString());
		QueryUtil.setArgs(args, countQuery);
		long count = DatabaseUtil.getCount(countQuery.getSingleResult());
		if (count > 0) {
			StringBuilder sql = new StringBuilder("select d.*");
			sql.append("\n").append(condition);
			if (pageRequest != null) {
				QueryUtil.setOrder(sql, pageRequest);
			}
			Query query = em.createNativeQuery(sql.toString(), SxZfYz2.class);
			QueryUtil.setArgs(args, query);
			if (pageRequest != null) {
				QueryUtil.setPage(pageRequest, query);
			} else {
				queryInfo.setPageInfo(new PageInfo(1, 1));
			}
			List<SxZfYz2> list = query.getResultList();
			if (queryInfo.isToReverse()) {
				Collections.reverse(list);
			}
			return new PageResult<SxZfYz2>(list, count, queryInfo.getPageInfo());
		}
		return new PageResult<SxZfYz2>(new ArrayList<SxZfYz2>(), 0, queryInfo.getPageInfo());
	}
}
