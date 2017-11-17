package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx7Yz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx7YzDao extends BaseYzDao<Zx7Yz> {

	@Override
	protected String getTableName() {
		return "zx7_yz";
	}
}
