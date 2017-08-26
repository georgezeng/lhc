package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.SlqLrYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class SlqLrYzDao extends BaseYzDao<SlqLrYz> {

	@Override
	protected String getTableName() {
		return "slq_lr_yz";
	}

}
