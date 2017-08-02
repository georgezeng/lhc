package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.DsYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class DsYzDao extends BaseYzDao<DsYz> {

	@Override
	protected String getTableName() {
		return "ds_yz";
	}

}
