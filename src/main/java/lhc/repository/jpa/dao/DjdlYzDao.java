package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.DjdlYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class DjdlYzDao extends BaseYzDao<DjdlYz> {

	@Override
	protected String getTableName() {
		return "djdl_yz";
	}
}
