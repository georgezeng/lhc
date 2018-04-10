package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.My150Yz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class My150Dao extends BaseYzDao<My150Yz> {

	@Override
	protected String getTableName() {
		return "my150_yz";
	}
}
