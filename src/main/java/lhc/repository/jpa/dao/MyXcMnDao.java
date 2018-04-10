package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.repository.jpa.BaseDsxMnDao;

@Repository
public class MyXcMnDao extends BaseDsxMnDao {

	@Override
	protected String getTableName() {
		return "my_xc_mn_yz";
	}

}
