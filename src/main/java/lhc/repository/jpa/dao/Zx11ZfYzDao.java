package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx11ZfYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx11ZfYzDao extends BaseYzDao<Zx11ZfYz> {

	@Override
	protected String getTableName() {
		return "zx11_zf_yz";
	}

}
