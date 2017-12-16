package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx14ZfYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx14ZfYzDao extends BaseYzDao<Zx14ZfYz> {

	@Override
	protected String getTableName() {
		return "zx14_zf_yz";
	}

}
