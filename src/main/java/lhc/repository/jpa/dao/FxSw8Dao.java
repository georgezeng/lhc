package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.FxSw8;
import lhc.repository.jpa.BaseFxSwDao;

@Repository
public class FxSw8Dao extends BaseFxSwDao<FxSw8> {

	@Override
	protected String getTableName() {
		return "fx_sw8_yz";
	}
}
