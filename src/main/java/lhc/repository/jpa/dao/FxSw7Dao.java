package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.FxSw7;
import lhc.repository.jpa.BaseFxSwDao;

@Repository
public class FxSw7Dao extends BaseFxSwDao<FxSw7> {

	@Override
	protected String getTableName() {
		return "fx_sw7_yz";
	}
}
