package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.repository.jpa.BaseDsxJYDao;

@Repository
public class DsxMinJYJHBDao extends BaseDsxJYDao {

	@Override
	protected String getTableName() {
		return "dsx_min_jy_jhb";
	}

}
