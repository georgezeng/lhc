package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.FxSw4;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class FxSw4Dao extends BaseYzDao<FxSw4> {

	@Override
	protected String getTableName() {
		return "fx_sw4_yz";
	}
}
