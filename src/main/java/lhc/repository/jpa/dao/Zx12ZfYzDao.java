package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx12ZfYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx12ZfYzDao extends BaseYzDao<Zx12ZfYz> {

	@Override
	protected String getTableName() {
		return "zx12_zf_yz";
	}

}
