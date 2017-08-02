package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.DsZfYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class DsZfYzDao extends BaseYzDao<DsZfYz> {

	@Override
	protected String getTableName() {
		return "ds_zf_yz";
	}

}
