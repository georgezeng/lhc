package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.FxSw1;
import lhc.repository.jpa.BaseFxSwDao;

@Repository
public class FxSw1Dao extends BaseFxSwDao<FxSw1> {

	@Override
	protected String getTableName() {
		return "fx_sw1_yz";
	}
}
