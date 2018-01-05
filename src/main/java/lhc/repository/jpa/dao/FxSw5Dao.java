package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.FxSw5;
import lhc.repository.jpa.BaseFxSwDao;

@Repository
public class FxSw5Dao extends BaseFxSwDao<FxSw5> {

	@Override
	protected String getTableName() {
		return "fx_sw5_yz";
	}
}
