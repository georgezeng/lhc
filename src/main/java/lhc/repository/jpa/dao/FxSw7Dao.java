package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.FxSw7;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class FxSw7Dao extends BaseYzDao<FxSw7> {

	@Override
	protected String getTableName() {
		return "fx_sw7_yz";
	}
}
