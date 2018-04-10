package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.My250Yz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class My250Dao extends BaseYzDao<My250Yz> {

	@Override
	protected String getTableName() {
		return "my250_yz";
	}
}
