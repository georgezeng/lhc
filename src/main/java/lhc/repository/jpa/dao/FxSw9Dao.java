package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.FxSw9;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class FxSw9Dao extends BaseYzDao<FxSw9> {

	@Override
	protected String getTableName() {
		return "fx_sw9_yz";
	}
}
