package lhc.repository.jpa.dao;

import java.util.ArrayList;
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
import lhc.enums.SX;
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
}
