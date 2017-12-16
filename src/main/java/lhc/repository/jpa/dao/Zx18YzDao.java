package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx18Yz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx18YzDao extends BaseYzDao<Zx18Yz> {

	@Override
	protected String getTableName() {
		return "zx18_yz";
	}
}
