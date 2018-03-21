package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.My2Yz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class My2Dao extends BaseYzDao<My2Yz> {

	@Override
	protected String getTableName() {
		return "my2_yz";
	}
}
