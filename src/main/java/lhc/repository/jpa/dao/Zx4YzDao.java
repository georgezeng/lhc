package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx4Yz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx4YzDao extends BaseYzDao<Zx4Yz> {

	@Override
	protected String getTableName() {
		return "zx4_yz";
	}
}
