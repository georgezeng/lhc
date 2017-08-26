package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.SlqYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class SlqYzDao extends BaseYzDao<SlqYz> {

	@Override
	protected String getTableName() {
		return "slq_yz";
	}
}
