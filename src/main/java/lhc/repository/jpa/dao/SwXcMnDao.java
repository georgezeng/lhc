package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.repository.jpa.BaseDsxMnDao;

@Repository
public class SwXcMnDao extends BaseDsxMnDao {

	@Override
	protected String getTableName() {
		return "sw_xc_mn_yz";
	}

}
