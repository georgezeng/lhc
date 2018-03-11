package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.repository.jpa.BaseDsyJYDao;

@Repository
public class DsyMaxJYSJBDao extends BaseDsyJYDao {

	@Override
	protected String getTableName() {
		return "dsy_max_jy_sjb";
	}

}
