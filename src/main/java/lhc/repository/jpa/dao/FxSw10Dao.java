package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.FxSw10;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class FxSw10Dao extends BaseYzDao<FxSw10> {

	@Override
	protected String getTableName() {
		return "fx_sw10_yz";
	}
}
