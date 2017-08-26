package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.SlqZfYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class SlqZfYzDao extends BaseYzDao<SlqZfYz> {

	@Override
	protected String getTableName() {
		return "slq_zf_yz";
	}

}
