package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.FxSw4;
import lhc.repository.jpa.BaseFxSwDao;

@Repository
public class FxSw4Dao extends BaseFxSwDao<FxSw4> {

	@Override
	protected String getTableName() {
		return "fx_sw4_yz";
	}
}
