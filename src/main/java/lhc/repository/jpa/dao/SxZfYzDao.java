package lhc.repository.jpa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import lhc.domain.SxZfYz;
import lhc.dto.query.PageInfo;
import lhc.dto.query.PageResult;
import lhc.dto.query.QueryInfo;
import lhc.util.DatabaseUtil;
import lhc.util.QueryUtil;

@Repository
@SuppressWarnings("unchecked")
public class SxZfYzDao {
	@Autowired
	private EntityManager em;

	public PageResult<SxZfYz> query(QueryInfo<SxZfYz> queryInfo) {
		PageRequest pageRequest = null;
		if (queryInfo.getPageInfo() != null) {
			pageRequest = queryInfo.getPageInfo().toPageRequest();
		}
		StringBuilder condition = new StringBuilder();
		condition.append("from sx_zf_yz").append("\n");
		condition.append("where 1=1").append("\n");
		List<Object> args = new ArrayList<Object>();
		if (queryInfo.getObject() != null) {
			SxZfYz yz = queryInfo.getObject();
			if (yz != null) {
				if (!"desc".equals(yz.getDate())) {
					condition.append("and year >= ?").append("\n");
					args.add(yz.getYear());
					condition.append("and phase >= ?").append("\n");
					args.add(yz.getPhase());
				} else {
					condition.append("and year <= ?").append("\n");
					args.add(yz.getYear());
					condition.append("and phase <= ?").append("\n");
					args.add(yz.getPhase());
				}
			}
		}
		StringBuilder countSql = new StringBuilder("select count(id)");
		countSql.append("\n").append(condition);
		Query countQuery = em.createNativeQuery(countSql.toString());
		QueryUtil.setArgs(args, countQuery);
		long count = DatabaseUtil.getCount(countQuery.getSingleResult());
		if (count > 0) {
			StringBuilder sql = new StringBuilder("select *");
			sql.append("\n").append(condition);
			if (pageRequest != null) {
				QueryUtil.setOrder(sql, pageRequest);
			}
			Query query = em.createNativeQuery(sql.toString(), SxZfYz.class);
			QueryUtil.setArgs(args, query);
			if (pageRequest != null) {
				QueryUtil.setPage(pageRequest, query);
			} else {
				queryInfo.setPageInfo(new PageInfo(1, 1));
			}
			List<SxZfYz> list = query.getResultList();
			return new PageResult<SxZfYz>(list, count, queryInfo.getPageInfo());
		}
		return new PageResult<SxZfYz>(new ArrayList<SxZfYz>(), 0, queryInfo.getPageInfo());
	}
}
