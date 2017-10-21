package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.DsLrYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class DsLrYzDao extends BaseYzDao<DsLrYz> {

	@Override
	protected String getTableName() {
		return "ds_lr_yz";
	}

}
