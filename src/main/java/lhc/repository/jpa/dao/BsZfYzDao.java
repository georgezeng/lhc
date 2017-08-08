package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.BsZfYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class BsZfYzDao extends BaseYzDao<BsZfYz> {

	@Override
	protected String getTableName() {
		return "bs_zf_yz";
	}

}
