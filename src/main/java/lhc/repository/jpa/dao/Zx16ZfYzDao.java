package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx16ZfYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx16ZfYzDao extends BaseYzDao<Zx16ZfYz> {

	@Override
	protected String getTableName() {
		return "zx16_zf_yz";
	}

}
