package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.SqYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class SqYzDao extends BaseYzDao<SqYz> {

	@Override
	protected String getTableName() {
		return "sq_yz";
	}
}
