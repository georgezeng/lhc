package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.FxSw12;
import lhc.repository.jpa.BaseFxSwDao;

@Repository
public class FxSw12Dao extends BaseFxSwDao<FxSw12> {

	@Override
	protected String getTableName() {
		return "fx_sw12_yz";
	}
}
