package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.My100Yz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class My100Dao extends BaseYzDao<My100Yz> {

	@Override
	protected String getTableName() {
		return "my100_yz";
	}
}
