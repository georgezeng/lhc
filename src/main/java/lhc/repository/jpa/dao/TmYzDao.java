package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.TmYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class TmYzDao extends BaseYzDao<TmYz> {

	@Override
	protected String getTableName() {
		return "tm_yz";
	}
}
