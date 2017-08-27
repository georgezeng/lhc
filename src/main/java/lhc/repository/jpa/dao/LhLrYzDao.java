package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.LhLrYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class LhLrYzDao extends BaseYzDao<LhLrYz> {

	@Override
	protected String getTableName() {
		return "lh_lr_yz";
	}

}
