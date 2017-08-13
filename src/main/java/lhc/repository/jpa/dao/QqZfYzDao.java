package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.QqZfYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class QqZfYzDao extends BaseYzDao<QqZfYz> {

	@Override
	protected String getTableName() {
		return "qq_zf_yz";
	}

}
