package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.ZsYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class ZsYzDao extends BaseYzDao<ZsYz> {

	@Override
	protected String getTableName() {
		return "zs_yz";
	}
}
