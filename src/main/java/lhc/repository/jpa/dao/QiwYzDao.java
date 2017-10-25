package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.QiwYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class QiwYzDao extends BaseYzDao<QiwYz> {

	@Override
	protected String getTableName() {
		return "qiw_yz";
	}
}
