package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.TmFdYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class TmFdYzDao extends BaseYzDao<TmFdYz> {

	@Override
	protected String getTableName() {
		return "tm_fd_yz";
	}
}
