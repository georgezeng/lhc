package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.FxSw5;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class FxSw5Dao extends BaseYzDao<FxSw5> {

	@Override
	protected String getTableName() {
		return "fx_sw5_yz";
	}
}
