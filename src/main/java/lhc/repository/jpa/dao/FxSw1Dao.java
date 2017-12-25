package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.FxSw1;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class FxSw1Dao extends BaseYzDao<FxSw1> {

	@Override
	protected String getTableName() {
		return "fx_sw1_yz";
	}
}
