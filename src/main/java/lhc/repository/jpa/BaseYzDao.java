package lhc.repository.jpa;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import lhc.domain.BaseYz;
import lhc.dto.query.PageInfo;
import lhc.dto.query.PageResult;
import lhc.dto.query.QueryInfo;
import lhc.dto.query.SortInfo;
import lhc.enums.SortOrder;
import lhc.util.DatabaseUtil;
import lhc.util.QueryUtil;

@SuppressWarnings("unchecked")
public abstract class BaseYzDao<T extends BaseYz> {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected EntityManager em;

	protected abstract String getTableName();

	protected Class<T> clazz;

	@PostConstruct
	public void init() throws Exception {
		clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public PageResult<T> query(QueryInfo<T> queryInfo) {
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
			T yz = queryInfo.getObject();
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
			Query query = em.createNativeQuery(sql.toString(), clazz);
			QueryUtil.setArgs(args, query);
			if (pageRequest != null) {
				QueryUtil.setPage(pageRequest, query);
			} else {
				queryInfo.setPageInfo(new PageInfo(1, 1));
			}
			List<T> list = query.getResultList();
			if (queryInfo.isToReverse()) {
				Collections.reverse(list);
			}
			return new PageResult<T>(list, count, queryInfo.getPageInfo());
		}
		return new PageResult<T>(new ArrayList<T>(), 0, queryInfo.getPageInfo());
	}

	public List<T> queryUpTo(int year, int phase) {
		List<Object> args = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("select * from " + getTableName()).append("\n");
		sql.append("where (year = ? and phase <= ?)").append("\n");
		args.add(year);
		args.add(phase);
		if (phase < 3) {
			sql.append("or (year = ?)").append("\n");
			args.add(year - 1);
		}
		sql.append("order by year desc, phase desc").append("\n");
		sql.append("limit 0, 3").append("\n");
		Query query = em.createNativeQuery(sql.toString(), clazz);
		QueryUtil.setArgs(args, query);
		return query.getResultList();
	}

	public List<T> queryUpToLatest(Long ltId, Long gtId) {
		List<Object> args = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("select * from " + getTableName()).append("\n");
		sql.append("where id <= ?").append("\n");
		args.add(ltId);
		sql.append("and id >= ?").append("\n");
		args.add(gtId);
		sql.append("order by id desc").append("\n");
		Query query = em.createNativeQuery(sql.toString(), clazz);
		QueryUtil.setArgs(args, query);
		return query.getResultList();
	}

	public T getLastYzWithSamePos(T currentData, String column) {
		List<Object> args = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("select * from " + getTableName()).append("\n");
		sql.append("where id < ?").append("\n");
		args.add(currentData.getId());
		sql.append("and " + column + "=0").append("\n");
		sql.append("order by year desc, phase desc").append("\n");
		sql.append("limit 0, 1").append("\n");
		Query query = em.createNativeQuery(sql.toString(), clazz);
		QueryUtil.setArgs(args, query);
		T result = null;
		try {
			result = (T) query.getSingleResult();
		} catch (NoResultException e) {
		}
		return result;
	}

	public List<T> getLastYzListWithSamePos(T currentData, String column) {
		List<Object> args = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("select * from " + getTableName()).append("\n");
		sql.append("where id < ?").append("\n");
		args.add(currentData.getId());
		sql.append("and " + column + "=0").append("\n");
		sql.append("order by year desc, phase desc").append("\n");
		Query query = em.createNativeQuery(sql.toString(), clazz);
		QueryUtil.setArgs(args, query);
		return query.getResultList();
	}

	public T getLastYzIn(Long id, int presize) {
		List<Object> args = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("select * from " + getTableName()).append("\n");
		sql.append("where id < ?").append("\n");
		args.add(id);
		sql.append("order by year desc, phase desc").append("\n");
		sql.append("limit " + presize + ", 1").append("\n");
		Query query = em.createNativeQuery(sql.toString(), clazz);
		QueryUtil.setArgs(args, query);
		T result = null;
		try {
			result = (T) query.getSingleResult();
		} catch (NoResultException e) {
		}
		return result;
	}
}
