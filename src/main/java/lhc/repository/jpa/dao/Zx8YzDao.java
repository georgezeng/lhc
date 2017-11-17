package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx8Yz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx8YzDao extends BaseYzDao<Zx8Yz> {

	@Override
	protected String getTableName() {
		return "zx8_yz";
	}
}
