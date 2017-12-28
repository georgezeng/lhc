package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.FxSw11;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class FxSw11Dao extends BaseYzDao<FxSw11> {

	@Override
	protected String getTableName() {
		return "fx_sw11_yz";
	}
}
