package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.SwYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class SwYzDao extends BaseYzDao<SwYz> {

	@Override
	protected String getTableName() {
		return "sw_yz";
	}
}
