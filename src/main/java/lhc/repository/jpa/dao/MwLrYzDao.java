package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.MwLrYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class MwLrYzDao extends BaseYzDao<MwLrYz> {

	@Override
	protected String getTableName() {
		return "mw_lr_yz";
	}

}
