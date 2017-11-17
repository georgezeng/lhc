package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx1Yz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx1YzDao extends BaseYzDao<Zx1Yz> {

	@Override
	protected String getTableName() {
		return "zx1_yz";
	}
}
