package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.ZsZfYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class ZsZfYzDao extends BaseYzDao<ZsZfYz> {

	@Override
	protected String getTableName() {
		return "zs_zf_yz";
	}

}
