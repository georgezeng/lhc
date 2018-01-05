package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.FxSw2;
import lhc.repository.jpa.BaseFxSwDao;

@Repository
public class FxSw2Dao extends BaseFxSwDao<FxSw2> {

	@Override
	protected String getTableName() {
		return "fx_sw2_yz";
	}
}
