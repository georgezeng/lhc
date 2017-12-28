package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.FxSw12;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class FxSw12Dao extends BaseYzDao<FxSw12> {

	@Override
	protected String getTableName() {
		return "fx_sw12_yz";
	}
}
