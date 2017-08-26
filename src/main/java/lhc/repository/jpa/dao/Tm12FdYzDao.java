package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Tm12FdYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Tm12FdYzDao extends BaseYzDao<Tm12FdYz> {

	@Override
	protected String getTableName() {
		return "tm12fd_yz";
	}
}
