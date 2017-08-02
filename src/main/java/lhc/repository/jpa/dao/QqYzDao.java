package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.QqYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class QqYzDao extends BaseYzDao<QqYz> {

	@Override
	protected String getTableName() {
		return "qq_yz";
	}
}
