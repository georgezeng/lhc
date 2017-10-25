package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.QiwZfYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class QiwZfYzDao extends BaseYzDao<QiwZfYz> {

	@Override
	protected String getTableName() {
		return "qiw_zf_yz";
	}

}
