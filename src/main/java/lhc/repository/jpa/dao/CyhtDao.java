package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.CyhtYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class CyhtDao extends BaseYzDao<CyhtYz> {

	@Override
	protected String getTableName() {
		return "cyht_yz";
	}
}
