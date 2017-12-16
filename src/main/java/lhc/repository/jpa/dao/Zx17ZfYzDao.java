package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx17ZfYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx17ZfYzDao extends BaseYzDao<Zx17ZfYz> {

	@Override
	protected String getTableName() {
		return "zx17_zf_yz";
	}

}
