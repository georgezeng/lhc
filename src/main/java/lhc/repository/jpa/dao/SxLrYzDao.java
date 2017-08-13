package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.SxLrYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class SxLrYzDao extends BaseYzDao<SxLrYz> {

	@Override
	protected String getTableName() {
		return "sx_lr_yz";
	}

}
