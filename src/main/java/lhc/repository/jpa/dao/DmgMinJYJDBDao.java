package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.repository.jpa.BaseDmgJYDao;

@Repository
public class DmgMinJYJDBDao extends BaseDmgJYDao {

	@Override
	protected String getTableName() {
		return "dmg_min_jy_jdb";
	}

}
