package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.LhDsYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class LhDsYzDao extends BaseYzDao<LhDsYz> {

	@Override
	protected String getTableName() {
		return "lh_ds_yz";
	}
}
