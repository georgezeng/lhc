package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.repository.jpa.BaseDsxMnDao;

@Repository
public class My100MnDao extends BaseDsxMnDao {

	@Override
	protected String getTableName() {
		return "my100_mn_yz";
	}

}
