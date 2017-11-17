package lhc.repository.jpa.dao;

import org.springframework.stereotype.Repository;

import lhc.domain.Zx2Yz;
import lhc.repository.jpa.BaseYzDao;

@Repository
public class Zx2YzDao extends BaseYzDao<Zx2Yz> {

	@Override
	protected String getTableName() {
		return "zx2_yz";
	}
}
