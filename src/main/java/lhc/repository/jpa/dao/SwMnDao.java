package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.repository.jpa.BaseDsxMnDao;

@Repository
public class SwMnDao extends BaseDsxMnDao {

	@Override
	protected String getTableName() {
		return "sw_mn_yz";
	}

}
