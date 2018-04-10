package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.ScydYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class ScydDao extends BaseYzDao<ScydYz> {

	@Override
	protected String getTableName() {
		return "scyd_yz";
	}
}
