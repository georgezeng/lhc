package lhc.repository.jpa.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import lhc.domain.KaiJiang;
import lhc.dto.query.PageInfo;
import lhc.dto.query.PageResult;
import lhc.dto.query.QueryInfo;
import lhc.dto.query.SortInfo;
import lhc.enums.SX;
import lhc.enums.SortOrder;
import lhc.util.DatabaseUtil;
import lhc.util.QueryUtil;

@Repository
@SuppressWarnings("unchecked")
public class KaiJiangDao {
	@Autowired
	private EntityManager em;

	public PageResult<KaiJiang> query(QueryInfo<String> queryInfo) {
		PageRequest pageRequest = null;
		if (queryInfo.getPageInfo() != null) {
			pageRequest = queryInfo.getPageInfo().toPageRequest();
		}
		StringBuilder condition = new StringBuilder();
		condition.append("from kai_jiang").append("\n");
		condition.append("where 1=1").append("\n");
		List<Object> args = new ArrayList<Object>();
		if (queryInfo.getObject() != null) {
			String value = queryInfo.getObject();
			if (!StringUtils.isEmpty(value)) {
				condition.append("and (date = ?").append("\n");
				args.add(value);
				try {
					args.add(Integer.valueOf(value));
					condition.append("or year = ?").append("\n");
				} catch (Exception e) {
				}
				try {
					args.add(SX.textOf(value).name());
					condition.append("or special_sx = ?").append("\n");
				} catch (Exception e) {
				}
				condition.append(")").append("\n");
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
			Query query = em.createNativeQuery(sql.toString(), KaiJiang.class);
			QueryUtil.setArgs(args, query);
			if (pageRequest != null) {
				QueryUtil.setPage(pageRequest, query);
			} else {
				queryInfo.setPageInfo(new PageInfo(1, 1));
			}
			List<KaiJiang> list = query.getResultList();
			return new PageResult<KaiJiang>(list, count, queryInfo.getPageInfo());
		}
		return new PageResult<KaiJiang>(new ArrayList<KaiJiang>(), 0, queryInfo.getPageInfo());
	}

	public PageResult<KaiJiang> queryForPM(QueryInfo<KaiJiang> queryInfo) {
		PageRequest pageRequest = null;
		if (queryInfo.getPageInfo() != null) {
			List<SortInfo> sorts = new ArrayList<SortInfo>();
			sorts.add(new SortInfo("d.date", SortOrder.DESC));
			queryInfo.getPageInfo().setSorts(sorts);
			pageRequest = queryInfo.getPageInfo().toPageRequest();
		}
		StringBuilder condition = new StringBuilder();
		condition.append("from kai_jiang d").append("\n");
		List<Object> args = new ArrayList<Object>();
		if (queryInfo.getObject() != null) {
			condition.append(", (select yp.date from kai_jiang yp").append("\n");
			condition.append("where yp.year = ?").append("\n");
			condition.append("and yp.phase = ?) t").append("\n");
			KaiJiang yz = queryInfo.getObject();
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
			Query query = em.createNativeQuery(sql.toString(), KaiJiang.class);
			QueryUtil.setArgs(args, query);
			if (pageRequest != null) {
				QueryUtil.setPage(pageRequest, query);
			} else {
				queryInfo.setPageInfo(new PageInfo(1, 1));
			}
			List<KaiJiang> list = query.getResultList();
			if (queryInfo.isToReverse()) {
				Collections.reverse(list);
			}
			return new PageResult<KaiJiang>(list, count, queryInfo.getPageInfo());
		}
		return new PageResult<KaiJiang>(new ArrayList<KaiJiang>(), 0, queryInfo.getPageInfo());
	}
}
