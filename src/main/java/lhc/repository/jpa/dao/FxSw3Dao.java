package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.FxSw3;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class FxSw3Dao extends BaseYzDao<FxSw3> {

	@Override
	protected String getTableName() {
		return "fx_sw3_yz";
	}
}
