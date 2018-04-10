package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.repository.jpa.BaseDsxMnDao;

@Repository
public class MyMnDao extends BaseDsxMnDao {

	@Override
	protected String getTableName() {
		return "my_mn_yz";
	}

}
