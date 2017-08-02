package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.QwYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class QwYzDao extends BaseYzDao<QwYz> {

	@Override
	protected String getTableName() {
		return "qw_yz";
	}
}
