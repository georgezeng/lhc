package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx13Yz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx13YzDao extends BaseYzDao<Zx13Yz> {

	@Override
	protected String getTableName() {
		return "zx13_yz";
	}
}
