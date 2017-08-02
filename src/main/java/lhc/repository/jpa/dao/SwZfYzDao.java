package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.SwZfYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class SwZfYzDao extends BaseYzDao<SwZfYz> {

	@Override
	protected String getTableName() {
		return "sw_zf_yz";
	}
}
