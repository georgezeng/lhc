package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.repository.jpa.BaseDmgJYDao;

@Repository
public class DmgMaxJYJQBDao extends BaseDmgJYDao {

	@Override
	protected String getTableName() {
		return "dmg_max_jy_jqb";
	}

}
