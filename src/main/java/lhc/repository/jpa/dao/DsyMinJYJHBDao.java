package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.repository.jpa.BaseDsyJYDao;

@Repository
public class DsyMinJYJHBDao extends BaseDsyJYDao {

	@Override
	protected String getTableName() {
		return "dsy_min_jy_jhb";
	}

}
