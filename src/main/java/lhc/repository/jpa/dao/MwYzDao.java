package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.MwYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class MwYzDao extends BaseYzDao<MwYz> {

	@Override
	protected String getTableName() {
		return "mw_yz";
	}
}
