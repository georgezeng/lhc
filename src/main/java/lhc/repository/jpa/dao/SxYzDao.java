package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.SxYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class SxYzDao extends BaseYzDao<SxYz> {

	@Override
	protected String getTableName() {
		return "sx_yz";
	}
}
