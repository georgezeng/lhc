package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.FxSwA;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class FxSwADao extends BaseYzDao<FxSwA> {

	@Override
	protected String getTableName() {
		return "fx_sw_a";
	}
}
