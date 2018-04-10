package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.repository.jpa.BaseDsxMnDao;

@Repository
public class My100XcMnDao extends BaseDsxMnDao {

	@Override
	protected String getTableName() {
		return "my100_xc_mn_yz";
	}

}
