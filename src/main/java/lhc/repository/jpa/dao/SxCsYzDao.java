package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.SxCsYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class SxCsYzDao extends BaseYzDao<SxCsYz> {

	@Override
	protected String getTableName() {
		return "sx_cs_yz";
	}

}
