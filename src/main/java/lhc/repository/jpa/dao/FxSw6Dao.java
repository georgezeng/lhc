package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.FxSw6;
import lhc.repository.jpa.BaseFxSwDao;

@Repository
public class FxSw6Dao extends BaseFxSwDao<FxSw6> {

	@Override
	protected String getTableName() {
		return "fx_sw6_yz";
	}
}
