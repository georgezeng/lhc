package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.My200Yz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class My200Dao extends BaseYzDao<My200Yz> {

	@Override
	protected String getTableName() {
		return "my200_yz";
	}
}
