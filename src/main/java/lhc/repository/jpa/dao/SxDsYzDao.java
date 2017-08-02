package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.SxDsYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class SxDsYzDao extends BaseYzDao<SxDsYz> {

	@Override
	protected String getTableName() {
		return "sx_ds_yz";
	}
}
