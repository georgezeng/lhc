package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Tm12FdLrYz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Tm12FdLrYzDao extends BaseYzDao<Tm12FdLrYz> {

	@Override
	protected String getTableName() {
		return "tm12fd_lr_yz";
	}

}
