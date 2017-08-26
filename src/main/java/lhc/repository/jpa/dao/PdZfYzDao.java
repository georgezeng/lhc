package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.PdZfYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class PdZfYzDao extends BaseYzDao<PdZfYz> {

	@Override
	protected String getTableName() {
		return "pd_zf_yz";
	}

}
