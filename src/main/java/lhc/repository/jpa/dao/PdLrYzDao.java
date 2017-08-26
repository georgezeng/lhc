package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.PdLrYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class PdLrYzDao extends BaseYzDao<PdLrYz> {

	@Override
	protected String getTableName() {
		return "pd_lr_yz";
	}

}
