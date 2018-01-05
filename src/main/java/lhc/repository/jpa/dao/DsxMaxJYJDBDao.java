package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.repository.jpa.BaseDsxJYDao;

@Repository
public class DsxMaxJYJDBDao extends BaseDsxJYDao {

	@Override
	protected String getTableName() {
		return "dsx_max_jy_jdb";
	}

}
