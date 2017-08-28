package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.ZsLrYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class ZsLrYzDao extends BaseYzDao<ZsLrYz> {

	@Override
	protected String getTableName() {
		return "zs_lr_yz";
	}

}
