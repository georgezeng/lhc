package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.FxSw2;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class FxSw2Dao extends BaseYzDao<FxSw2> {

	@Override
	protected String getTableName() {
		return "fx_sw2_yz";
	}
}
