package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.My1Yz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class My1Dao extends BaseYzDao<My1Yz> {

	@Override
	protected String getTableName() {
		return "my1_yz";
	}
}
