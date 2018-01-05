package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

@Repository
public class DsxMinJYJQBDao extends DsxJYBaseDao {

	@Override
	protected String getTableName() {
		return "dsx_min_jy_jqb";
	}

}
