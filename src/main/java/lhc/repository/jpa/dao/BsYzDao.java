package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.BsYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class BsYzDao extends BaseYzDao<BsYz> {

	@Override
	protected String getTableName() {
		return "bs_yz";
	}

}
